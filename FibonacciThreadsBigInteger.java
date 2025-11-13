import java.math.BigInteger;
import java.util.Hashtable;

// NOTA: La clase pública DEBE llamarse FibonacciThreadsMemoizedBigInteger
public class FibonacciThreadsMemoizedBigInteger implements Runnable {

    // Hashtable STATIC para la Memoización: (Posición BigInteger, Valor BigInteger)
    private static final Hashtable<BigInteger, BigInteger> memo = new Hashtable<>();
    
    // Inicialización estática de los casos base: F(0)=0, F(1)=1
    static {
        memo.put(BigInteger.ZERO, BigInteger.ZERO); 
        memo.put(BigInteger.ONE, BigInteger.ONE);   
    }

    BigInteger fi;
    int num;

    public FibonacciThreadsMemoizedBigInteger(int n, BigInteger f) {
        num = n;
        fi = f;
    }

    @Override
    public void run() {
        System.out.println("Starte #" + num + " para F(" + fi + ")");
        BigInteger res = fibonacci(fi);
        System.out.println("Abschlussverfahren: " + num +
                           " - " + "fibonacci(" + fi + ") =" + res);
    }

    /**
     * Función Fibonacci recursiva con Memoización (O(n) en tiempo).
     * Usa BigInteger para manejar valores gigantes.
     */
    public BigInteger fibonacci(BigInteger n) {
        
        // 1. Revisar Memoria
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        // 2. Caso Base
        if (n.compareTo(BigInteger.TWO) < 0) {
            return memo.getOrDefault(n, n); 
        }

        // 3. Calcular y Memoizar
        BigInteger nMinusOne = n.subtract(BigInteger.ONE);
        BigInteger nMinusTwo = n.subtract(BigInteger.TWO);
        
        // Las llamadas recursivas se benefician de la memoización compartida
        BigInteger fibNMinusOne = fibonacci(nMinusOne);
        BigInteger fibNMinusTwo = fibonacci(nMinusTwo);
        
        BigInteger result = fibNMinusOne.add(fibNMinusTwo);

        // 4. Guardar resultado
        memo.put(n, result);

        return result;
    }

    /**
     * FIRMA DE MAIN CORREGIDA: public static void main(String[] args)
     */
    public static void main(String[] args) {
        Thread[] threads = new Thread[10];

        // Se prueban posiciones grandes (ej. n entre 100 y 1000) para demostrar la eficiencia
        for (int i = 0; i < 10; i++) {
            long algo = (long) (Math.random() * 900) + 100; 
            threads[i] = new Thread(
                    new FibonacciThreadsMemoizedBigInteger(i, BigInteger.valueOf(algo)));
        }
        
        for (int i = 0; i < 10; i++) threads[i].start();
    }
}
