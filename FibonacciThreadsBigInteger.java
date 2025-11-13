import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;

public class FibonacciThreadsBigInteger implements Runnable {

    // Caché estática para memoización, segura para hilos.
    private static final ConcurrentHashMap<BigInteger, BigInteger> memo = new ConcurrentHashMap<>();

    // Inicializar los casos base F(0) y F(1) en el caché.
    static {
        memo.put(BigInteger.ZERO, BigInteger.ZERO); // F(0) = 0
        memo.put(BigInteger.ONE, BigInteger.ONE);   // F(1) = 1
    }

    BigInteger fi;
    int num;

    public FibonacciThreadsBigInteger(int n, BigInteger f) {
        num = n;
        fi = f;
    }

    @Override
    public void run() {
        // En una implementación real, 'IO.println' requeriría una clase 'IO' que no está definida.
        // Asumiendo que es un reemplazo de 'System.out.println' o similar.
        System.out.println("Starte #" + num);

        BigInteger res = fibonacci(fi);

        System.out.println("Abschlussverfahren: " + num +
                           " - " + "fibonacci(" + fi + ") =" + res);
    }

    // Método fibonacci modificado con memoización
    public BigInteger fibonacci(BigInteger f) {
        // 1. Verificar si el resultado ya está en el caché
        if (memo.containsKey(f)) {
            return memo.get(f);
        }

        // 2. Si no está, calcularlo de forma recursiva (con memoización para las subllamadas)
        // La condición de parada original: f < 2 retorna 1. La secuencia estándar es F(0)=0, F(1)=1.
        // Asumiendo que el usuario busca la secuencia donde F(1)=1, F(2)=1, F(3)=2, ...
        // El caso base ya está manejado por el caché (F(0)=0, F(1)=1).

        // Calcular F(n) = F(n-1) + F(n-2)
        BigInteger f_minus_1 = f.subtract(BigInteger.ONE);
        BigInteger f_minus_2 = f.subtract(BigInteger.TWO);

        BigInteger result = fibonacci(f_minus_1).add(fibonacci(f_minus_2));

        // 3. Almacenar el resultado en el caché antes de retornarlo
        memo.put(f, result);
        return result;
    }

    static void main() {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            // Genera números entre 1 y 50. Para BigInteger, podemos subir el límite
            // para mostrar el poder de esta clase, por ejemplo, hasta 1000,
            // pero lo mantendré en 50 para evitar cálculos excesivamente largos aquí.
            long algo = (long) (Math.random() * 50) + 1;
            threads[i] = new Thread(
                    new FibonacciThreadsBigInteger(i, BigInteger.valueOf(algo)));
        }
        for (int i = 0; i < 10; i++) threads[i].start();
    }
}
