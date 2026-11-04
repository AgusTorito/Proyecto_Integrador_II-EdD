package modelo;


public class Naipe {

    public static final String[] PALOS_POSIBLES = {"diamante", "corazon", "pica", "trebol"};

    public static final String PALO_COMODIN = "comodin";

    public static final int[] VALORES_POSIBLES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    public static final int VALOR_AS = 1;

    public static final int VALOR_COMODIN = 100;

    private static final int VALOR_MAXIMO_NORMAL = 13;

    private String tipo;
    private int valor;
    private boolean disponible;

    public Naipe(String tipo, int valor, boolean disponible) {
        validarCombinacion(tipo, valor);
        this.tipo = tipo;
        this.valor = valor;
        this.disponible = disponible;
    }

    public static Naipe comodin(boolean disponible) {
        return new Naipe(PALO_COMODIN, VALOR_COMODIN, disponible);
    }

    public static double puntajeDeCartas(Naipe[] cartas) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        validarCombinacion(tipo, this.valor);
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        validarCombinacion(this.tipo, valor);
        this.valor = valor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /** true si la carta es el comodin. */
    public boolean esComodin() {
        return PALO_COMODIN.equals(tipo);
    }

    /** true si la carta es un As (valor 1, no comodin). */
    public boolean esAs() {
        return !esComodin() && valor == VALOR_AS;
    }

    @Override
    public String toString() {
        if (esComodin()) {
            return "Comodin (" + VALOR_COMODIN + " puntos)";
        }
        return valor + " de " + tipo;
    }

    // Validaciones, si no son necesarias, eliminarlas o colocarlas en el helper
    private static void validarCombinacion(String tipo, int valor) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El palo de la carta no puede estar vacio");
        }
        if (PALO_COMODIN.equals(tipo)) {
            if (valor != VALOR_COMODIN) {
                throw new IllegalArgumentException("El comodin debe valer " + VALOR_COMODIN);
            }
            return;
        }
        if (!esPaloNormal(tipo)) {
            throw new IllegalArgumentException("Palo invalido: " + tipo);
        }
        if (valor < VALOR_AS || valor > VALOR_MAXIMO_NORMAL) {
            throw new IllegalArgumentException(
                    "El valor de una carta normal debe estar entre " + VALOR_AS + " y " + VALOR_MAXIMO_NORMAL);
        }
    }

    private static boolean esPaloNormal(String tipo) {
        for (String palo : PALOS_POSIBLES) {
            if (palo.equals(tipo)) {
                return true;
            }
        }
        return false;
    }
}