package Problema2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class PascalPolynomial {

    public static long[] generarCoeficientes(int n) {
        if (n < 0) throw new IllegalArgumentException("n debe ser no negativo");
        
        long[] coeficientes = new long[n + 1];
        
        if (n == 0) {
            coeficientes[0] = 1;
            return coeficientes;
        }

        coeficientes[0] = 1; 

        for (int i = 1; i <= n; i++) {
            for (int j = i; j >= 1; j--) {
                if (j == i) {
                    coeficientes[j] = 1;
                } else {
                    coeficientes[j] = coeficientes[j] + coeficientes[j-1];
                }
            }
        }
        return coeficientes;
    }

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
            
            if (i == 0) {
                sb.append(absC);
            } else {
                if (absC != 1) sb.append(absC);
                sb.append("x");
                if (i > 1) sb.append("^").append(i);
            }
        }
        return sb.toString();
    }
    
    public static long evaluarPolinomioPasos(long[] coeficientes, double x) {
        int n = coeficientes.length - 1;
        
        System.out.println("\n--- Cálculo f(" + x + ") por Regla de Horner (Pasos) ---");
        
        long resultado = coeficientes[n]; 
        System.out.println("Paso 0 (Inicial con a_n=" + coeficientes[n] + "): " + resultado);

        for (int i = n - 1; i >= 0; i--) {
            long coeficiente = coeficientes[i];
            long valorPrevio = resultado;
            
            resultado = (long)(resultado * x + coeficiente); 
            System.out.printf("Paso %d (a_%d=%d): (%d * %.1f) + %d = %d\n", n - i, i, coeficiente, valorPrevio, x, coeficiente, resultado);
        }
        return resultado;
    }

    private static void escribirResultado(int n, String lenguaje, long duracionNs) {
        try (FileWriter writer = new FileWriter("tiempos_ejecucion.txt", true)) {
            writer.write("--- Medicion de Tiempo ---\n");
            writer.write("N=" + n + "\n");
            writer.write("Lenguaje: " + lenguaje + "\n");
            writer.write("Tiempo (ns): " + duracionNs + "\n");
            writer.write("Tiempo (ms): " + duracionNs / 1_000_000.0 + "\n");
            writer.write("---\n");
            System.out.println("\nResultado de Java (n=" + n + ") escrito en 'tiempos_ejecucion.txt'.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int n_medicion = 100; 
        
        System.out.println("=== Ejecutando Java: Generación de (x+1)^" + n_medicion + " ===");
        
        long startTime = System.nanoTime();
        long[] coeficientes_100 = generarCoeficientes(n_medicion);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime); 

        System.out.println("\n--- Resultados de (x+1)^100 ---");
        System.out.println("Nº de Coeficientes generados: " + coeficientes_100.length);
        
        escribirResultado(n_medicion, "Java", duration);
        
        int n_ejemplo = 4;    
        double x_val = 2.0;   
        
        long[] coeficientes_ejemplo = generarCoeficientes(n_ejemplo);

        System.out.println("\n\n=== Ejemplo (n=" + n_ejemplo + ") para Ilustrar la Evaluación Paso a Paso (b) ===");
        System.out.println("    Polinomio: " + mostrarPolinomio(coeficientes_ejemplo));
        
        long resultado_final = evaluarPolinomioPasos(coeficientes_ejemplo, x_val);
        System.out.println("    Resultado Final f(" + x_val + ") = " + resultado_final + " (Verificación: (2+1)^4 = 81)");
    }
}