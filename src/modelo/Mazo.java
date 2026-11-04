package modelo;

import java.util.Random;

public class Mazo {
    private Naipe[] cartas;
    private int tope; // Indica cuántas cartas quedan disponibles en el mazo

    public Mazo() {
        cartas = new Naipe[54]; // 52 cartas francesas + 2 comodines
        inicializarMazo();
        mezclar();
    }

    // Metodo para crear las 54 cartas del mazo según las reglas de Naipé
    private void inicializarMazo() {
        int index = 0;

        // 1. Crear cartas normales para cada uno de los palos posibles
        for (String palo : Naipe.PALOS_POSIBLES) {
            for (int valor : Naipe.VALORES_POSIBLES) {
                cartas[index] = new Naipe(palo, valor, true); // true = disponible
                index++;
            }
        }

        // 2. Agregar los dos comodines usando el metodo estático de la clase Naipé
        cartas[index] = Naipe.comodin(true);
        index++;
        cartas[index] = Naipe.comodin(true);

        tope = cartas.length; // El mazo arranca con 54 cartas disponibles
    }

    // Metodo para mezclar las cartas al azar (Algoritmo de Fisher-Yates)
    public void mezclar() {
        Random rand = new Random();
        for (int i = cartas.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            // Intercambiar cartas[i] con cartas[j]
            Naipe temp = cartas[i];
            cartas[i] = cartas[j];
            cartas[j] = temp;
        }
        tope = cartas.length; // Resetea el tope al mezclar
        System.out.println("¡El mazo ha sido barajado exitosamente!");
    }

    // Metodo para extraer una carta del mazo disponible
    public Naipe extraerCarta() {
        if (tope > 0) {
            tope--;
            cartas[tope].setDisponible(false); // Cambia el estado a no disponible
            return cartas[tope];
        }
        return null; // Mazo vacío
    }

    public int getTope() {
        return tope;
    }

    public Naipe[] getCartas() {
        return cartas;
    }
}