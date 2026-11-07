package tda;

public class Arreglo<T> {
    private T[] vector;
    private int ce;          // Cantidad  de elementos cargados
    private int capacidad;   // Capacidad máxima del arreglo

    public Arreglo(int capacidadMax) {
        this.capacidad = capacidadMax;
        this.vector = (T[]) new Object[capacidadMax];
        this.ce = 0;
    }

    // Método para verificar si el arreglo está lleno
    public boolean estaLleno() {
        return ce == capacidad;
    }

    // Método para verificar si el arreglo está vacío
    public boolean estaVacio() {
        return ce == 0;
    }

    // Retorna la cantidad efectiva de elementos
    public int getCantidadEfectiva() {
        return ce;
    }

    // Insertar al final del arreglo
    public boolean insertar(T elemento) {
        if (estaLleno()) {
            System.out.println("Error: El arreglo está lleno.");
            return false;
        }
        vector[ce] = elemento;
        ce++;
        return true;
    }

    // Insertar en una posición específica (desplazando elementos)
    public boolean insertarEn(int indice, T elemento) {
        if (estaLleno() || indice < 0 || indice > ce) {
            System.out.println("Error: Índice inválido o arreglo lleno.");
            return false;
        }
        // Desplazar elementos a la derecha
        for (int i = ce; i > indice; i--) {
            vector[i] = vector[i - 1];
        }
        vector[indice] = elemento;
        ce++;
        return true;
    }

    // Buscar un elemento (retorna el índice o -1 si no se encuentra)
    public int buscar(T elemento) {
        for (int i = 0; i < ce; i++) {
            if (vector[i].equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    // Eliminar por posición (desplazando elementos hacia la izquierda)
    public boolean eliminarEn(int indice) {
        if (indice < 0 || indice >= ce) {
            System.out.println("Error: Índice fuera de rango.");
            return false;
        }
        for (int i = indice; i < ce - 1; i++) {
            vector[i] = vector[i + 1];
        }
        vector[ce - 1] = null; // Limpiar la referencia para el recolector de basura
        ce--;
        return true;
    }

    // Obtener un elemento en una posición dada
    public T obtener(int indice) {
        if (indice < 0 || indice >= ce) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        return vector[indice];
    }

    // Mostrar los elementos por consola
    public void mostrar() {
        if (estaVacio()) {
            System.out.println("El arreglo está vacío.");
            return;
        }
        System.out.print("[ ");
        for (int i = 0; i < ce; i++) {
            System.out.print(vector[i] + (i < ce - 1 ? ", " : " "));
        }
        System.out.println("]");
    }
}