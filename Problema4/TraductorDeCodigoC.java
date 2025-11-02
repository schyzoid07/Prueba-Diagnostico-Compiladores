package Problema4;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
  4) Para un programa escrito en lenguaje C, cargado en una memoria de forma dinámica verifique si 
existen palabras reservadas en C y tradúzcalas a su equivalente en español.
 */
public class TraductorDeCodigoC {
    private static final Map<String, String> DICCIONARIO_RESERVADAS_C = new HashMap<>();

    static {
        DICCIONARIO_RESERVADAS_C.put("auto", "automatico");
        DICCIONARIO_RESERVADAS_C.put("break", "romper");
        DICCIONARIO_RESERVADAS_C.put("case", "caso");
        DICCIONARIO_RESERVADAS_C.put("char", "caracter");
        DICCIONARIO_RESERVADAS_C.put("const", "constante");
        DICCIONARIO_RESERVADAS_C.put("continue", "continuar");
        DICCIONARIO_RESERVADAS_C.put("default", "defecto");
        DICCIONARIO_RESERVADAS_C.put("do", "hacer");
        DICCIONARIO_RESERVADAS_C.put("double", "doble");
        DICCIONARIO_RESERVADAS_C.put("else", "sino");
        DICCIONARIO_RESERVADAS_C.put("enum", "enumeracion");
        DICCIONARIO_RESERVADAS_C.put("extern", "externo");
        DICCIONARIO_RESERVADAS_C.put("float", "flotante");
        DICCIONARIO_RESERVADAS_C.put("for", "para");
        DICCIONARIO_RESERVADAS_C.put("goto", "ir_a"); // Traducción literal, aunque su uso es obsoleto
        DICCIONARIO_RESERVADAS_C.put("if", "si");
        DICCIONARIO_RESERVADAS_C.put("int", "entero");
        DICCIONARIO_RESERVADAS_C.put("long", "largo");
        DICCIONARIO_RESERVADAS_C.put("register", "registro");
        DICCIONARIO_RESERVADAS_C.put("return", "retornar");
        DICCIONARIO_RESERVADAS_C.put("short", "corto");
        DICCIONARIO_RESERVADAS_C.put("signed", "firmado"); // o "con_signo"
        DICCIONARIO_RESERVADAS_C.put("sizeof", "tamano_de");
        DICCIONARIO_RESERVADAS_C.put("static", "estatico");
        DICCIONARIO_RESERVADAS_C.put("struct", "estructura");
        DICCIONARIO_RESERVADAS_C.put("switch", "alternar");
        DICCIONARIO_RESERVADAS_C.put("typedef", "definir_tipo");
        DICCIONARIO_RESERVADAS_C.put("union", "union");
        DICCIONARIO_RESERVADAS_C.put("unsigned", "sin_firmar"); // o "sin_signo"
        DICCIONARIO_RESERVADAS_C.put("void", "vacio");
        DICCIONARIO_RESERVADAS_C.put("volatile", "volatil");
        DICCIONARIO_RESERVADAS_C.put("while", "mientras");

    }

    public static String traducirPalabrasReservadasC(String codigoFuente) {
        String codigoTraducido = codigoFuente;

        for (Map.Entry<String, String> entrada : DICCIONARIO_RESERVADAS_C.entrySet()) {
            String palabraReservada = entrada.getKey();
            String traduccion = entrada.getValue();

            String regex = "\\b" + Pattern.quote(palabraReservada) + "\\b";
            
            codigoTraducido = codigoTraducido.replaceAll(regex, traduccion);
        }

        return codigoTraducido;
    }

    public static void main(String[] args) {

        String codigoC = 
            "#include <stdio.h>\n\n" +
            "int main() {\n" +
            "    const int MAX_COUNT = 100;\n" +
            "    for (int i = 0; i < MAX_COUNT; i++) {\n" +
            "        if (i % 2 == 0) {\n" +
            "            continue;\n" +
            "        } else if (i == 5) {\n" +
            "            break;\n" +
            "        }\n" +
            "        printf(\"Valor: %d\\n\", i);\n" +
            "    }\n" +
            "    return 0;\n" +
            "}\n\n" + 
            "void ejemplo_tipo(long valor_largo, short valor_corto) {\n" +
            "    struct T_Datos datos;\n" +
            "    union U_Tipos tipos;\n" +
            "    while(1) {\n" +
            "        // ...\n" +
            "    }\n" +
            "}\n";

        System.out.println("--- Código Fuente Original (Lenguaje C) ---");
        System.out.println(codigoC);

        String codigoTraducido = traducirPalabrasReservadasC(codigoC);

        System.out.println("\n--- Código Traducido (Simulación en Español) ---");
        System.out.println(codigoTraducido);
    }
}