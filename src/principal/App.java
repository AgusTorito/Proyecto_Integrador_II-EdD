package principal;

import juego.Helper;
import modelo.Jugador;
import modelo.Mazo;
import modelo.Naipe;

import java.util.Scanner;

public class App {
    private static final int TOTAL_RONDAS = 4;

    static void main() {
        Scanner sc = new Scanner(System.in);
        menu();
    }

    private static void jugar() {
        Mazo mazo = new Mazo();
        Jugador[] jugadores = {
                new Jugador("123", "Ibarra", "perez", 25),
                new Jugador("123", "Jose", "perez", 25),
                new Jugador("123", "Lauty", "Aima", 25),
                new Jugador("123", "Rafa", "Fascio", 25)};

        for (int ronda = 1; ronda <= TOTAL_RONDAS; ronda++) {
            System.out.println("        Ronda " + ronda );

            repartiAJugadores(jugadores, mazo);

            // los jugadors muestran su mano y se guarda su puntaje de la ronda
            for (Jugador jugador : jugadores) {
                jugador.setPuntajeRonda(jugador.calcularPuntajeMano());
            }
            mostrarManos(jugadores);

            // el ganador de la ronda se lleva las cartas de todos a su bolsa
            Jugador ganador = verGanador(jugadores);
            ganador.guardarNaipesActualizarRanking(cartasDeLaRonda(jugadores));

            System.out.println("        gana la ronda " + ronda + " " + ganador.getNombre()
                    + " con " + ganador.getPuntajeRonda() + " puntos");
        }

        anunciarGanadorJuego(jugadores);
    }
    public static Jugador verGanador(Jugador[] jugadores) {
        Jugador ganador = jugadores[0];
        for (Jugador jugador : jugadores) {
            if (jugador.getPuntajeRonda() > ganador.getPuntajeRonda()) {
                ganador = jugador;
            }
        }
        return ganador;
    }
    public static void repartiAJugadores(Jugador[] jugadores, Mazo mazo) {
        for (Jugador jugador : jugadores) {
            Naipe[] cartas = new Naipe[Jugador.CARTAS_POR_RONDA];
            for (int i = 0; i < cartas.length; i++) {
                cartas[i] = mazo.repartir();
            }
            jugador.recibirMano(cartas);
        }
    }
    private static void mostrarManos(Jugador[] jugadores) {
        for (Jugador jugador : jugadores) {
            System.out.print(jugador.getNombre() + " 3:");
            for (Naipe carta : jugador.getMano()) {
                System.out.print("[" + carta+"]");
            }
            System.out.println("   Total: " + jugador.getPuntajeRonda());
        }
    }
    private static Naipe[] cartasDeLaRonda(Jugador[] jugadores) {
        Naipe[] todas = new Naipe[jugadores.length * Jugador.CARTAS_POR_RONDA];
        int k = 0;
        for (Jugador jugador : jugadores) {
            for (Naipe carta : jugador.getMano()) {
                todas[k++] = carta;
            }
        }
        return todas;
    }
    private static void anunciarGanadorJuego(Jugador[] jugadores) {
        double max = 0;
        for (Jugador jugador : jugadores) {
            double puntos = jugador.calcularPuntajeBolsa();
            System.out.println(jugador.getNombre() + ": " + puntos + " puntos");
            jugador.setPuntajeRanking(puntos);
            if (puntos > max) {
                max= puntos;
            }
        }
        System.out.print("      GANADOR:");
        for (Jugador jugador : jugadores) {
            if (jugador.calcularPuntajeBolsa() == max) {
                System.out.println(" -----------" + jugador + " con " + max + " puntos-----------");
            }
        }
    }

    private static void ranking() {
    }

    private static void inscripcion() {
    }

    private static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("1-- inscripcion\n" +
                "2--ranking\n" +
                "3--jugar\n");
        int input = Helper.leerEnteroPositivo(sc,"Ingrese la opcion deseada");

        switch ( input){
            case 1:
                inscripcion();
                break;
            case 2:
                ranking();
                break;
            case 3:
                jugar();
                break;
            default:
                System.out.println("Las opciones deben estra ente 1 y 3");
                menu();
               //case default
        }

    }
}
