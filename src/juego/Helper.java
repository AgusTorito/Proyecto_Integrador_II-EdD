package juego;

import modelo.Naipe;

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
            //sc.next();
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
    public static int validarEdad(Scanner sc) {
        int edad;
        while (true){
            edad = sc.nextInt();
            if (edad < 18) {
                System.out.println ("La edad debe ser mayor a " + 18);

            }else return edad;
        }
    }
    public static double puntajeMano(Naipe[] cartas) {
        if (cartas == null) {
            throw new IllegalArgumentException("El arreglo de cartas no puede ser nulo");
        }
        double suma = 0;
        boolean hayAs = false;
        for (Naipe carta : cartas) {
            if (carta == null) {
                throw new IllegalArgumentException("Ninguna carta puede ser nula");
            }
            suma += carta.getValor();
            if (carta.esAs()) {
                hayAs = true;
            }
        }
        return hayAs ? suma * 2 : suma;
    }

    public static String validarDni(Scanner sc) {
        String dniValue ;
        while (true) {
            try {
                dniValue = sc.nextLine();
                if (dniValue == null || dniValue.isEmpty()) {
                    throw new IllegalArgumentException("El DNI no puede estar vacio");
                }
                for (int i = 0; i < dniValue.length(); i++) {
                    if (!Character.isDigit(dniValue.charAt(i))) {
                        throw new IllegalArgumentException("El DNI debe contener solo numeros");
                    }
                }
                return dniValue;
            } catch (Exception exception) {
                System.out.println("ErrV-Dni");
            }
        }
    }



}