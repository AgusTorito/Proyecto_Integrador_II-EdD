package modelo;

public class Naipe {

    public static final String[] PALOS_POSIBLES = {"diamante", "corazon", "pica", "trebol"," comodin "};

    public static final int[] VALORES_POSIBLES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,100};


    private String tipo;
    private int valor;
    private boolean disponible;

    public Naipe(String tipo, int valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.disponible = true;
    }
    public Naipe() {
        this.tipo = "none";
        this.valor = 0;
        this.disponible = false;
    }


    public String getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /** true si la carta es el comodin. */
    public boolean esComodin() {
        return PALOS_POSIBLES[4].equals(tipo);
    }

    /** true si la carta es un As (valor 1, no comodin). */
    public boolean esAs() {
        return !esComodin() && valor == 1;
    }

    @Override
    public String toString() {
        return valor + " de " + tipo;
    }

}