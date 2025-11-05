package Problema3;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
  3) Implemente reconocimiento de cadenas, expresiones notación científica, ip, correo electrónico.
 */
public class ValidadorDeCadenas {

    private static final String REGEX_NOTACION_CIENTIFICA = 
        "^[+-]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][+-]?\\d+)?$";

    private static final String REGEX_DIRECCION_IPV4 = 
        "^((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})$";
        
    private static final String REGEX_CORREO_ELECTRONICO = 
        "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";


    public static boolean esNotacionCientificaValida(String entrada) {
        Pattern patron = Pattern.compile(REGEX_NOTACION_CIENTIFICA);
        Matcher comparador = patron.matcher(entrada);
        return comparador.matches();
    }
    
    public static boolean esDireccionIpv4Valida(String entrada) {
        Pattern patron = Pattern.compile(REGEX_DIRECCION_IPV4);
        Matcher comparador = patron.matcher(entrada);
        return comparador.matches();
    }
    
    public static boolean esCorreoElectronicoValido(String entrada) {
        Pattern patron = Pattern.compile(REGEX_CORREO_ELECTRONICO);
        Matcher comparador = patron.matcher(entrada);
        return comparador.matches();
    }
    
    // --- Método Principal de Ejemplo ---
    public static void main(String[] args) {
        System.out.println("Reconocimiento de Notación Científica:");
        probarNotacionCientifica("1.23e-4");
        probarNotacionCientifica("-5E+10");
        probarNotacionCientifica("123.45");
        probarNotacionCientifica("1.2.3e4"); // Inválido
        probarNotacionCientifica("e10");    // Inválido

        System.out.println("\nReconocimiento de Dirección IP v4:");
        probarDireccionIpv4("192.168.1.1");
        probarDireccionIpv4("255.255.255.0");
        probarDireccionIpv4("256.0.0.1"); // Inválido (octeto > 255)
        probarDireccionIpv4("1.2.3");   // Inválido (falta un octeto)

        System.out.println("\nReconocimiento de Correo Electrónico:");
        probarCorreoElectronico("usuario.prueba@dominio.com");
        probarCorreoElectronico("nombre-completo123@sub.dominio.net");
        probarCorreoElectronico("usuario@@dominio.com"); // Inválido
        probarCorreoElectronico("dominio.com");       // Inválido
    }
    
    // Métodos auxiliares para imprimir los resultados en español
    private static void probarNotacionCientifica(String s) {
        System.out.println("  \"" + s + "\": " + (esNotacionCientificaValida(s) ? "Válido" : "Inválido"));
    }
    private static void probarDireccionIpv4(String s) {
        System.out.println("  \"" + s + "\": " + (esDireccionIpv4Valida(s) ? "Válido" : "Inválido"));
    }
    private static void probarCorreoElectronico(String s) {
        System.out.println("  \"" + s + "\": " + (esCorreoElectronicoValido(s) ? "Válido" : "Inválido"));
    }
}