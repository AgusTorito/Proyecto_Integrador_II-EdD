package modelo;

import java.util.Random;
import java.util.Stack;

public class Mazo {
    private Stack<Naipe> naipes;

    public Mazo() {
        this.naipes = new Stack<>();
        Naipe[] cartasOrdenadas =inicializarMazo();
        mezclarYAgregar(cartasOrdenadas);
    }

    // Metodo para crear las 54 cartas del mazo según las reglas de Naipé
    private Naipe[] inicializarMazo() {
        Naipe[] cartas = new Naipe[54];
        int index = 0;

        // 4 palos x 13 valores = 52 cartas
        for (int p = 0; p < 4; p++) {
            for (int v = 0; v < 13; v++) {
                cartas[index++] = new Naipe(Naipe.PALOS_POSIBLES[p], Naipe.VALORES_POSIBLES[v]);
            }
        }
        // 2 comodines (valor 100)
        cartas[index++] = new Naipe(Naipe.PALOS_POSIBLES[4], Naipe.VALORES_POSIBLES[13]);
        cartas[index++] = new Naipe(Naipe.PALOS_POSIBLES[4], Naipe.VALORES_POSIBLES[13]);
        return cartas;
    }

    // Metodo para mezclar las cartas al azar (Algoritmo de Fisher-Yates) luego las agrega al stack de cartas
    public void mezclarYAgregar(Naipe[] cartasOrdenadas) {
        Random rand = new Random();
        for (int i = cartasOrdenadas.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            // Intercambiar cartas[i] con cartas[j]
            Naipe temp = cartasOrdenadas[i];
            cartasOrdenadas[i] = cartasOrdenadas[j];
            cartasOrdenadas[j] = temp;
        }
        for(Naipe carta : cartasOrdenadas){
            this.naipes.push(carta);
        }
    }
    public Naipe repartir(){
        return naipes.pop();
    }
}