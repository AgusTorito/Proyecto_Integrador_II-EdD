package modelo;

import java.util.Iterator;

import tda.SimpleLinkedList;

public class Jugador implements Comparable<Jugador> {

    public static final int CARTAS_POR_RONDA = 3;

    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 120;

    private final String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private final int numeroJugador;
    private double puntaje;
    private double puntajeJuego;
    private SimpleLinkedList<Naipe> miBolsa;
    private Naipe[] mano;

    public Jugador(String dni, String nombre, String apellido, int edad, int numeroJugador) {
        validarDni(dni);
        validarTextoNoVacio(nombre, "El nombre no puede estar vacio");
        validarTextoNoVacio(apellido, "El apellido no puede estar vacio");
        validarEdad(edad);
        if (numeroJugador <= 0) {
            throw new IllegalArgumentException("El numero de jugador debe ser positivo");
        }

        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.numeroJugador = numeroJugador;
        this.puntaje = 0;
        this.puntajeJuego = 0;
        this.miBolsa = new SimpleLinkedList<>();
        this.mano = new Naipe[0];
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        validarTextoNoVacio(nombre, "El nombre no puede estar vacio");
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        validarTextoNoVacio(apellido, "El apellido no puede estar vacio");
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        validarEdad(edad);
        this.edad = edad;
    }

    public int getNumeroJugador() {
        return numeroJugador;
    }

    /** Recibe las 3 cartas de la ronda actual (reemplaza la mano anterior). */
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

    /** Devuelve una copia de la mano actual. */
    public Naipe[] getMano() {
        return mano.clone();
    }

    /** Puntaje de la mano actual, con la regla del As (para comparar en la ronda). */
    public double calcularPuntajeRonda() {
        return Naipe.puntajeDeCartas(mano);
    }

    /** Descarta la mano al terminar la ronda. */
    public void limpiarMano() {
        this.mano = new Naipe[0];
    }

    //Registra el resultado de una ronda

    public void registrarRonda(Naipe[] cartasDeLaRonda) {
        double puntajeRonda = Naipe.puntajeDeCartas(cartasDeLaRonda);
        for (Naipe carta : cartasDeLaRonda) {
            miBolsa.addLast(carta);
        }
        this.puntajeJuego += puntajeRonda;
    }

    // Juego y ranking

    public double calcularPuntajeJuego() {
        return puntajeJuego;
    }

    public SimpleLinkedList<Naipe> getMiBolsa() {
        return miBolsa;
    }

    public int cantidadCartasGanadas() {
        return miBolsa.size();
    }

    /**
     * guarda el puntaje del juego como puntaje del ranking si es mayor
     * que el anterior, y prepara al jugador para un nuevo juego.
     */
    public void finalizarJuego() {
        if (puntajeJuego > this.puntaje) {
            this.puntaje = puntajeJuego;
        }
        reiniciarJuego();
    }

    /** Vacia miBolsa, la mano y el puntaje del juego (el puntaje del ranking no cambia). */
    public void reiniciarJuego() {
        while (miBolsa.size() > 0) {
            miBolsa.removeFirst();
        }
        this.puntajeJuego = 0;
        this.mano = new Naipe[0];
    }

    // Mayor puntaje obtenido en los juegos jugados 
    public double getPuntaje() {
        return puntaje;
    }

    //Ordena el ABB del ranking de menor a mayor puntaje;
    @Override
    public int compareTo(Jugador otro) {
        int cmp = Double.compare(this.puntaje, otro.puntaje);
        if (cmp != 0) {
            return cmp;
        }
        return Integer.compare(this.numeroJugador, otro.numeroJugador);
    }

    // Auxiliares, colocarlos en el helper o eliminarlos si no hace falta.
    @Override
    public String toString() {
        return "Jugador{" +
                "Nro=" + numeroJugador +
                ", DNI='" + dni + '\'' +
                ", Nombre='" + nombre + '\'' +
                ", Apellido='" + apellido + '\'' +
                ", Edad=" + edad +
                ", Puntaje=" + puntaje +
                ", Cartas ganadas=" + miBolsa.size() +
                '}';
    }

    private static void validarDni(String dni) {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede estar vacio");
        }
        for (int i = 0; i < dni.length(); i++) {
            if (!Character.isDigit(dni.charAt(i))) {
                throw new IllegalArgumentException("El DNI debe contener solo numeros");
            }
        }
    }

    private static void validarEdad(int edad) {
        if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            throw new IllegalArgumentException(
                    "La edad debe estar entre " + EDAD_MINIMA + " y " + EDAD_MAXIMA);
        }
    }

    private static void validarTextoNoVacio(String texto, String mensaje) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }
}