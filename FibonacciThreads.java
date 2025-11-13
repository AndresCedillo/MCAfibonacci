import java.util.concurrent.ConcurrentHashMap;

public class FibonacciThreads implements Runnable {

    // Caché estática para memoización, segura para hilos.
    private static final ConcurrentHashMap<Long, Long> memo = new ConcurrentHashMap<>();

    // Inicializar los casos base F(0) y F(1) en el caché.
    static {
        memo.put(0L, 0L); // F(0) = 0
        memo.put(1L, 1L); // F(1) = 1
    }

    long fi;
    int num;

    public FibonacciThreads(int n, long f) {
        num = n;
        fi = f;
    }

    @Override
    public void run() {
        System.out.println("Starte #" + num);
        long res = fibonacci(fi);
        System.out.println("Abschlussverfahren: " + num +
                           " - " + "fibonacci(" + fi + ") =" + res);
    }

    // Método fibonacci modificado con memoización
    long fibonacci(long f) {
        // 1. Verificar si el resultado ya está en el caché
        if (memo.containsKey(f)) {
            return memo.get(f);
        }

        // Si f es menor que 0, puede ser un error, pero el cálculo ya estaría en memo si fuera 0 o 1.
        // Si el valor no está en el caché (lo cual es esperado para f > 1):
        if (f < 2) {
             // Este caso solo debería ocurrir si 'f' es 0 o 1, pero ya están en el caché.
             // Si se llama con un 'f' no positivo, retornamos 1 para ser consistentes con la lógica original
             // o 0/1 para ser consistentes con la secuencia. Mantendré la secuencia estándar (F(0)=0, F(1)=1).
             return (f == 0) ? 0 : 1;
        }

        // 2. Si no está, calcularlo de forma recursiva (con memoización para las subllamadas)
        long result = fibonacci(f - 1) + fibonacci(f - 2);

        // 3. Almacenar el resultado en el caché antes de retornarlo
        memo.put(f, result);
        return result;
    }

    static void main() {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            // Los números de Fibonacci crecen rápido. Para 'long', el máximo índice es F(92).
            // Usar un límite superior de 90 es seguro.
            long algo = (long) (Math.random() * 90) + 1;
            threads[i] = new Thread(new FibonacciThreads(i, algo));
        }

        for (int i = 0; i < 10; i++) threads[i].start();
    }
}
