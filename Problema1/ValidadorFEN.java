package Problema1;

import java.util.regex.Pattern;

public class ValidadorFEN {

    
    public static boolean esFENValida(String fenCadena) {
        if (fenCadena == null || fenCadena.trim().isEmpty()) {
            return false;
        }

        String[] campos = fenCadena.split(" ");
        if (campos.length != 6) {
            System.err.println("Error: El FEN debe tener exactamente 6 campos separados por espacios. Campos encontrados: " + campos.length);
            return false;
        }

        String colocacionPiezas = campos[0];
        String colorActivo = campos[1];
        String enroque = campos[2];
        String alPaso = campos[3];
        String relojMediaJugada = campos[4];
        String numeroJugadaCompleta = campos[5];

        if (!validarColocacionPiezas(colocacionPiezas)) {
            System.err.println("Error en el campo 1 (Posición de las Piezas): " + colocacionPiezas);
            return false;
        }

        if (!colorActivo.matches("[wb]")) {
            System.err.println("Error en el campo 2 (Turno): Debe ser 'w' o 'b'. Valor: " + colorActivo);
            return false;
        }

        if (!enroque.matches("-|[KQkq]{1,4}")) {
            System.err.println("Error en el campo 3 (Enroque): Valor inválido. Valor: " + enroque);
            return false;
        }

        if (!alPaso.matches("-|[a-h][36]")) {
            System.err.println("Error en el campo 4 (Al paso): Valor inválido. Valor: " + alPaso);
            return false;
        }

        try {
            int mediasJugadas = Integer.parseInt(relojMediaJugada);
            if (mediasJugadas < 0) {
                 System.err.println("Error en el campo 5 (Medias Jugadas): Debe ser un número no negativo. Valor: " + relojMediaJugada);
                 return false;
            }
        } catch (NumberFormatException e) {
             System.err.println("Error en el campo 5 (Medias Jugadas): Debe ser un número entero. Valor: " + relojMediaJugada);
             return false;
        }

        try {
            int jugadasCompletas = Integer.parseInt(numeroJugadaCompleta);
            if (jugadasCompletas < 1) {
                System.err.println("Error en el campo 6 (Movimientos Completos): Debe ser un número entero >= 1. Valor: " + numeroJugadaCompleta);
                return false;
            }
        } catch (NumberFormatException e) {
             System.err.println("Error en el campo 6 (Movimientos Completos): Debe ser un número entero. Valor: " + numeroJugadaCompleta);
             return false;
        }

        return true;
    }

    private static boolean validarColocacionPiezas(String colocacionPiezas) {
        String[] filas = colocacionPiezas.split("/");


        if (filas.length != 8) {
            System.err.println("El campo 1 debe tener 8 filas separadas por '/'. Filas encontradas: " + filas.length);
            return false;
        }

        for (String fila : filas) {
            int contadorCasillas = 0;
            boolean laUltimaFueNumero = false;

            for (char c : fila.toCharArray()) {
                if (Character.isDigit(c)) {

                    if (laUltimaFueNumero) {
                        System.err.println("Dos números consecutivos en una fila no son válidos.");
                        return false;
                    }

                    int casillasVacias = Character.getNumericValue(c);

                    if (casillasVacias < 1 || casillasVacias > 8) {
                        System.err.println("El número de casillas vacías debe estar entre 1 y 8.");
                        return false;
                    }

                    contadorCasillas += casillasVacias;
                    laUltimaFueNumero = true;
                } else if ("rnbqkpRNBQKP".indexOf(c) != -1) {
                    contadorCasillas++;
                    laUltimaFueNumero = false;
                } else {
                    System.err.println("Carácter inválido en la colocación de piezas: " + c);
                    return false;
                }
            }

            if (contadorCasillas != 8) {
                System.err.println("La fila " + fila + " suma " + contadorCasillas + " casillas, debe sumar 8.");
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // FEN Válido (Posición Inicial)
        String fenValida1 = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        System.out.println("FEN '" + fenValida1 + "' es válida: " + esFENValida(fenValida1)); // true

        // FEN Válido (Posición Intermedia)
        String fenValida2 = "8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 b - - 0 39";
        System.out.println("FEN '" + fenValida2 + "' es válida: " + esFENValida(fenValida2)); // true

        // FEN Inválida: Demasiados campos
        String fenInvalida1 = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 2";
        System.out.println("\nFEN '" + fenInvalida1 + "' es válida: " + esFENValida(fenInvalida1)); // false

        // FEN Inválida: Fila que no suma 8 casillas (8 + p = 9)
        String fenInvalida2 = "rnbqkbnr/pppppppp/8/8/8/8p/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        System.out.println("\nFEN '" + fenInvalida2 + "' es válida: " + esFENValida(fenInvalida2)); // false

        // FEN Inválida: Turno inválido
        String fenInvalida3 = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR x KQkq - 0 1";
        System.out.println("\nFEN '" + fenInvalida3 + "' es válida: " + esFENValida(fenInvalida3)); // false
    }
}