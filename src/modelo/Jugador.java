package modelo;

import juego.Helper;
import tda.SimpleLinkedList;

public class Jugador implements Comparable<modelo.Jugador> {
    //public class Jugador implements Comparable<modelo.Jugador> {
    //quite la implementacion de comparable si la necesitan la vuelven a descomentar

    public static final int CARTAS_POR_RONDA = 3;
    //Uso esta variable para autoincrementar el id del jugador ahora ya no se pasa por parametr
    private static int contadorID = 0;

    private final String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private final int numeroJugador;

    private double puntajeRanking;
    private double puntajeRonda;

    private SimpleLinkedList<Naipe> miBolsa;
    private Naipe[] mano;

    public Jugador(String dni, String nombre, String apellido, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.numeroJugador = ++contadorID;
        this.puntajeRanking =  0;
        this.puntajeRonda= 0;

        this.miBolsa = new SimpleLinkedList<>();
        this.mano = new Naipe[CARTAS_POR_RONDA];
    }
    //jugadr sin paraetros
    public Jugador() {
        this.dni = "none";
        this.nombre = "none";
        this.apellido = "none";
        this.edad = 0;
        this.numeroJugador = 0;
        this.puntajeRanking =  0;
        this.puntajeRonda= 0;
        this.miBolsa = null;
        this.mano = null;
    }

    public String getNombre() { return nombre + " " + apellido; }

    public double getPuntajeRonda(int i) {
        return puntajeRonda;
    }

    public void setPuntajeRonda(double puntajeRonda) {
        this.puntajeRonda = puntajeRonda;
    }



    public Naipe[] getMano() {
        return mano.clone();
    }

    public void setPuntajeRanking(double nuevoPuntaje) {
        if (nuevoPuntaje > this.puntajeRanking) {
            this.puntajeRanking = nuevoPuntaje ;
        }
    }

    //Creo como sugerencia quepodrian usar este metodo par ordenar en un arbol para el ranking
    public double getPuntajeRanking() {return puntajeRanking;}

    public int cantidadCartasGanadas() {
        return miBolsa.size();
    }
    //esto nose que onda pero calculo quese puede hacer una especie de historial de las ultimas cartas con las que gano el jugador X
    public SimpleLinkedList<Naipe> getMiBolsa() {
        return miBolsa;
    }

//Recine los 3 naipes de la ronda y los copia en el atributo mano
    public void recibirMano(Naipe[] cartas) {
        if (cartas == null || cartas.length != CARTAS_POR_RONDA) {
            throw new IllegalArgumentException("Cada jugador recibe " + CARTAS_POR_RONDA + " cartas por ronda");
        }
        for (Naipe carta : cartas) {
            if (carta == null) {
                throw new IllegalArgumentException("Ninguna carta de la mano puede ser nula");
            }
        }
        this.mano = cartas.clone();
    }
    public double calcularPuntajeBolsa() {
        double total = 0;
        for (Naipe carta : miBolsa) {      // SimpleLinkedList es Iterable
            total += carta.getValor();
        }
        return total;
    }
    public double calcularPuntajeMano() {
        return Helper.puntajeMano(mano);
    }
    //Registra el resultado de una ronda Ganada

    public void guardarNaipesActualizarRanking(Naipe [] naipesGanados) {
        double puntajeGanado =0 ;
        for (Naipe naipe : naipesGanados) {
            puntajeGanado +=naipe.getValor();
            miBolsa.addLast(naipe);
        }
        this.puntajeRanking += puntajeGanado;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "Nro=" + numeroJugador +
                ", DNI='" + dni + '\'' +
                ", Nombre='" + nombre + '\'' +
                ", Apellido='" + apellido + '\'' +
                ", Edad=" + edad +
                ", Puntaje=" + puntajeRanking +
                ", Cartas ganadas=" + miBolsa.size() +
                '}';
    }

    /** Vacia miBolsa, la mano y el puntaje del juego (el puntaje del ranking no cambia). */
//    public void reiniciarJuego() {
//        while (miBolsa.size() > 0) {
//            miBolsa.removeFirst();
//        }
//        this.puntajeJuego = 0;
//        this.mano = new Naipe[0];
//    }
    //esto nose que onda como se usa calculoques sirve para ver si hay otro jugador con el mismo numero pero creo que esto no es ncecearoo
    //Ordena el ABB del ranking de menor a mayor puntaje;//   @Override
    public int compareTo(Jugador otro) {
        int cmp = Double.compare(this.puntajeRanking, otro.puntajeRanking);
        if (cmp != 0) {
            return cmp;
        }
        return Integer.compare(this.numeroJugador, otro.numeroJugador);
    }

    // Auxiliares, colocarlos en el helper o eliminarlos si no hace falta.

}