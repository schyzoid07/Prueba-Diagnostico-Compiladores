package Problema2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class PascalPolynomial {

    // a) Generar Coeficientes
    public static long[] generarCoeficientes(int n) {
        if (n < 0) throw new IllegalArgumentException("n debe ser no negativo");
        
        // Memoria Dinámica: array de tipo long para evitar desbordamiento con n=100
        long[] coeficientes = new long[n + 1];
        
        // n=0: (x+1)^0 = 1
        if (n == 0) {
            coeficientes[0] = 1;
            return coeficientes;
        }

        coeficientes[0] = 1; // Coeficiente de x^0 (que es 1)

        // Aplicación de la Programación Dinámica (Triángulo de Pascal)
        // Se calcula la fila i basada en la fila anterior (almacenada en coeficientes)
        for (int i = 1; i <= n; i++) {
            // Recorrer de derecha a izquierda para usar los valores de la fila anterior antes de modificarlos
            for (int j = i; j >= 1; j--) {
                if (j == i) {
                    coeficientes[j] = 1; // El primer/último coeficiente de cada fila es 1
                } else {
                    // C(i, j) = C(i-1, j-1) + C(i-1, j)
                    coeficientes[j] = coeficientes[j] + coeficientes[j-1];
                }
            }
        }
        return coeficientes;
    }

    // Mostrar el Polinomio (x+1)^n
    public static String mostrarPolinomio(long[] coeficientes) {
        int n = coeficientes.length - 1;
        if (n == 0) return "1";

        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 0; i--) {
            long c = coeficientes[i];
            if (c == 0) continue;

            if (sb.length() > 0 && c > 0) sb.append(" + ");
            if (c < 0) sb.append(" - ");
            
            long absC = Math.abs(c);
            
            if (i == 0) { // Término constante
                sb.append(absC);
            } else if (i == 1) { // Término lineal
                if (absC != 1) sb.append(absC);
                sb.append("x");
            } else { // Término de grado > 1
                if (absC != 1) sb.append(absC);
                sb.append("x^").append(i);
            }
        }
        return sb.toString();
    }
    
    // b) Evaluar el Polinomio por Pasos (Regla de Horner)
    public static long evaluarPolinomioPasos(long[] coeficientes, double x) {
        int n = coeficientes.length - 1;
        
        // Para simplificar la Regla de Horner, invertimos los coeficientes para empezar con el de mayor grado a_n
        long[] coeficientesOrdenados = new long[n + 1];
        for(int i = 0; i <= n; i++) {
            coeficientesOrdenados[i] = coeficientes[n - i];
        }

        System.out.println("\n--- Cálculo f(" + x + ") por Regla de Horner (Pasos) ---");
        long resultado = 0;

        for (int i = 0; i <= n; i++) {
            long coeficiente = coeficientesOrdenados[i];
            
            if (i == 0) {
                resultado = coeficiente; // Inicio con a_n
                System.out.println("Paso 0 (Inicial): " + resultado);
            } else {
                long valorPrevio = resultado;
                resultado = (long)(resultado * x + coeficiente); // Nuevo resultado = (Resultado anterior * x) + a_k
                System.out.printf("Paso %d: (%d * %.1f) + %d = %d\n", i, valorPrevio, x, coeficiente, resultado);
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        int n = 100; // Valor de n para la medición de tiempo
        double x = 2.0; // Valor dado para x

        // --- Medición de Tiempo y Generación de Coeficientes (n=100) ---
        long startTime = System.nanoTime();
        long[] coeficientes = generarCoeficientes(n);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime); // en nanosegundos

        // a) Mostrar el resultado del polinomio
        System.out.println("--- Polinomio (x+1)^" + n + " ---");
        // Nota: Mostrar el polinomio de grado 100 completo es inviable, mostramos los primeros términos
        System.out.println("Coeficientes (Primeros 10): " + Arrays.toString(Arrays.copyOf(coeficientes, 10)));
        System.out.println("Coeficientes (Últimos 10): " + Arrays.toString(Arrays.copyOfRange(coeficientes, n - 9, n + 1)));

        // b) Mostrar el cálculo paso a paso (usando un n pequeño para visualización)
        int n_ejemplo = 4;
        long[] coeficientes_ejemplo = generarCoeficientes(n_ejemplo);
        System.out.println("\n--- Ejemplo: n=" + n_ejemplo + " ---");
        System.out.println("Polinomio: " + mostrarPolinomio(coeficientes_ejemplo));
        long resultado_final = evaluarPolinomioPasos(coeficientes_ejemplo, x);
        System.out.println("Resultado Final f(" + x + ") = " + resultado_final);
        
        // --- Escribir el resultado de la medición en archivo TXT ---
        escribirResultado(n, "Java", duration);
    }

    private static void escribirResultado(int n, String lenguaje, long duracionNs) {
        try (FileWriter writer = new FileWriter("tiempos_ejecucion.txt", true)) {
            writer.write("N=" + n + "\n");
            writer.write("Lenguaje: " + lenguaje + "\n");
            writer.write("Tiempo (ns): " + duracionNs + "\n");
            writer.write("Tiempo (ms): " + duracionNs / 1_000_000.0 + "\n");
            writer.write("---\n");
            System.out.println("\nResultado de Java (n=100) escrito en 'tiempos_ejecucion.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
