package principal;

import juego.Helper;
import modelo.Jugador;
import modelo.Mazo;
import modelo.Naipe;
import tda.BinarySearchTree;
import tda.SimpleLinkedList;

import java.util.Scanner;

public class App {
    private static final Scanner sc = new Scanner(System.in);
    private static final SimpleLinkedList<Jugador> jugadores = new SimpleLinkedList<Jugador>();
    private static final int TOTAL_RONDAS = 4;

    static void main() {

        jugadores.addFirst(new Jugador("12763215","Jose","Marquez",25));
        jugadores.addFirst(new Jugador("12763215","Lucas","Martinelii",25));
        jugadores.addFirst(new Jugador("12763215","Franco","Sajama",25));
        jugadores.addFirst(new Jugador("12763215","Luciano","Pereira",25));
        jugadores.addFirst(new Jugador("12763215","Lautaro","Martines",25));
        jugadores.addFirst(new Jugador("12763215","Belen","Castro",25));

        Scanner sc = new Scanner(System.in);
        menu();
    }

    private static void jugar() {
        Mazo mazo = new Mazo();
        Jugador[] jugadores = seleccionarJug();
        Jugador ganador = null;

        for (int ronda = 1; ronda <= TOTAL_RONDAS; ronda++) {
            System.out.println("        Ronda " + ronda );
            double totalPuntosRondas = 0;
            // los jugadors muestran su mano y se guarda su puntaje de la ronda
            repartiAJugadores(jugadores, mazo);
            for (Jugador jugador : jugadores) {

                jugador.setPuntajeRonda(jugador.calcularPuntajeMano());
                totalPuntosRondas += jugador.calcularPuntajeMano();

            }
            mostrarManos(jugadores);
            ganador = verGanador(jugadores);
            ganador.guardarNaipes(cartasDeLaRonda(jugadores));
            ganador.setPuntajeRanking(totalPuntosRondas);

            System.out.println("       --------< gana la ronda " + ronda + " " + ganador.getNombre()
                    + " con " + ganador.getPuntajeRonda() + " puntos , se lleva "+ totalPuntosRondas +" puntos>--------");
        }

    }

    private static Jugador[] seleccionarJug() {

        Jugador[] seleccionados= {new Jugador(),new Jugador(),new Jugador(),new Jugador()};
        Jugador aux  = new Jugador();
        for (int i = 0; i < seleccionados.length ; i++) {
            aux  = jugadores.removeFirst();
            seleccionados[i] = aux;
            jugadores.addLast(aux);
        }
        //logica dinde agregas reemplazas los jugadore dels lista jugador
        return seleccionados;

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
            System.out.print(jugador.getNombre() + " :");
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

    private static BinarySearchTree<Jugador> ranking() {

        BinarySearchTree<Jugador> ranking = new BinarySearchTree<Jugador>();
        for (Jugador jugador : jugadores) {
            ranking.add(jugador);
        }
        return ranking;

    }

    private static void inscripcion() {

        System.out.println("Inscripcion");
        System.out.println("1-- inscribi\n" +
                "2--salir\n" );
        int input = Helper.leerCaracter(sc,"Ingrese la opcion deseada:  ");
        switch ( input){
            case '1':
                char bandera ;
                while (true){
                    System.out.println("    Datos jugador");
                    Jugador jugador;
                    String nombre = Helper.leerString(sc,"Nombre del jugador: ");
                    String apellido = Helper.leerString(sc,"Apellido del jugador: ");
                    System.out.println("Ingrese el dni");
                    String dni = Helper.validarDni(sc);
                    System.out.println("Ingrese la edad: ");
                    //sc.close();
                    int edad= Helper.validarEdad(sc);
                    sc.nextLine();
                    jugador =new Jugador(dni,nombre,apellido,edad);
                    jugadores.addLast(jugador);
                    bandera = Helper.leerCaracter(sc,"Ingrese n para dejar de agregar jugadores\n sino ingrese cualquier caracter o letra");
                    //sc.next();
                    if (bandera=='n'){
                        break;
                    }
                    inscripcion();
                }
                break;
            case 's':
                menu();
                break;
            default:
                System.out.println("Las opciones deben estra ente 1 y 2");
                inscripcion();
        }

    }


    private static void menu() {

        System.out.println("MENU");
        System.out.println("1-- inscripcion\n" +
                "2--ranking\n" +
                "3--jugar\n");
        char input = Helper.leerCaracter(sc,"Ingrese la opcion deseada");

        switch ( input){
            case '1':
                System.out.println("LISTA INSCRIPTOS");
                System.out.println(jugadores);
                inscripcion();

                break;
            case '2':
                System.out.println("RANKING");
                BinarySearchTree<Jugador> rnk = ranking();
                rnk.DescendingOrder();
                menu();
                break;
            case '3':
                System.out.println("JUEGO");
                jugar();
                menu();

                break;
            default:
                System.out.println("Las opciones deben estra ente 1 y 3");
                menu();
               //case default
        }

    }
}
