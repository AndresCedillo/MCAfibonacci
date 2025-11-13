//Actividad 3. Clase Hashtable en Java.
// En Python usamos la estructura de datos “Dict” (Diccionario) para mantener la memoria. Java
 //tiene su estructura de datos analoga que es la clase Hashtable.
 //Para que veas su manejo muy basico, implementa el siguiente codigo
 //MainMiFiboHash.java
 //Nota: Se uso Java 25
import java.util.Hashtable;
import java.util.Set;
import java.util.Map.Entry;

public class MainMiFiboHash {

    public static void main(String[] args) {

        System.out.println(String.format("Uso de la clase Hashtable<k, v>"));

        Hashtable<Integer, Integer> fiboSuc
                = new Hashtable<Integer, Integer>();
        fiboSuc.put(0, 0);
        fiboSuc.put(1, 1);
        fiboSuc.put(2, 1);

        System.out.println("\n--- Forma 1: Usando keySet() ---");
        for (int key : fiboSuc.keySet()) {
            int val = fiboSuc.get(key);
            System.out.printf("El valor de fibonacci en la posición %d es %d %n",
                              key, val);
        }

        System.out.println("\n--- Forma 2: Usando forEach (Lambda) ---");
        fiboSuc.forEach((key, value) ->
                System.out.println("Key: " + key + ", Value: " + value));

        System.out.println("\n--- Forma 3: Recorrer y alterar (fibonacci + 100) ---");
        System.out.println("Soy fibonacci + 100");

            fiboSuc.forEach((k, v) -> {
            int nuevoValor = v + 100;

            fiboSuc.replace(k, nuevoValor);

            System.out.println("Key: " + k + ", Value: " + nuevoValor);
        });

        System.out.println("\n--- Verificación de valores actualizados ---");
        System.out.println(fiboSuc);
    }
}

