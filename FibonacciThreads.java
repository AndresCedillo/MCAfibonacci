import java.util.Hashtable;

// NOTA: La clase pública DEBE llamarse FibonacciThreadsMemoized
public class FibonacciThreadsMemoized implements Runnable {

    // Hashtable STATIC para memoizar: (Posición n: Long, Valor F(n): Long)
    // Permite que todos los hilos compartan la memoria de los cálculos.
    private static final Hashtable<Long, Long> memo = new Hashtable<>();
    
    // Inicializar casos base para F(0)=0, F(1)=1
    static {
        memo.put(0L, 0L);
        memo.put(1L, 1L);
    }

    long fi; // La posición a calcular (tipo long)
    int num;

    public FibonacciThreadsMemoized(int n, long f) {
        num = n;
        fi = f;
    }

    @Override
    public void run() {
        System.out.println("Starte #" + num + " para F(" + fi + ")");
        // Nota: Si fi > 92, 'res' sufrirá desbordamiento (overflow) de 'long'.
        long res = fibonacci(fi); 
        System.out.println("Abschlussverfahren: " + num + 
                           " - " + "fibonacci(" + fi + ") =" + res);
    }

    /**
     * Función Fibonacci recursiva con Memoización (O(n)).
     */
    long fibonacci(long n) {
        
        // 1. Revisar Memoria
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        // 2. Calcular Recursivamente y Memoizar
        long result = fibonacci(n - 1) + fibonacci(n - 2);

        // 3. Guardar resultado en la tabla
        memo.put(n, result);

        return result;
    }

    /**
     * FIRMA DE MAIN CORREGIDA: public static void main(String[] args)
     * Este es el punto de entrada para la ejecución por consola.
     */
    public static void main(String[] args) {
        Thread[] threads = new Thread[10];

        // Se usa un límite seguro para 'long' (ej. hasta 50)
        for (int i = 0; i < 10; i++) {
            long algo = (long) (Math.random() * 50) + 1; 
            threads[i] = new Thread(new FibonacciThreadsMemoized(i, algo));
        }

        for (int i = 0; i < 10; i++) threads[i].start();
    }
}
