package principal;

import java.util.Scanner;

public class Helper {

    //Validación de String (No vacío ni nulo)
    public static String leerString(Scanner sc, String mensaje) {
        String entrada = "";
        while (true) {
            System.out.print(mensaje);
            entrada = sc.nextLine().trim();
            if (!entrada.isEmpty()) {
                break;
            }
            System.out.println("Error: El campo no puede estar vacío. Intente nuevamente.");
        }
        return entrada;
    }

    //Validación de Carácter (Acepta solo una letra o carácter válido)
    public static char leerCaracter(Scanner sc, String mensaje) {
        char caracter = ' ';
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            if (entrada.length() == 1) {
                caracter = entrada.charAt(0);
                break;
            }
            System.out.println("Error: Debe ingresar un único carácter. Intente nuevamente.");
        }
        return caracter;
    }

    //Validación de Números Enteros Positivos (> 0)
    public static int leerEnteroPositivo(Scanner sc, String mensaje) {
        int numero = -1;
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            try {
                numero = Integer.parseInt(entrada);
                if (numero > 0) {
                    break;
                }
                System.out.println("Error: El número debe ser mayor a cero.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        }
        return numero;
    }

    //Validación de Edad (Rango: 12 a 100 años)
    public static int leerEdadReal(Scanner sc, String mensaje) {
        int edad = -1;
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            try {
                edad = Integer.parseInt(entrada);
                // Rango coherente para estudiantes de secundario/universidad y participantes
                if (edad >= 12 && edad <= 100) {
                    break;
                }
                System.out.println("Error: La edad debe ser un valor realista (entre 12 y 100 años).");
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero para la edad.");
            }
        }
        return edad;
    }
}