public class Stack<ELEMENT> {

    private final static Integer defaulDimension = 10;

    private ELEMENT [] data;
    private Integer count;

    public Stack() {
        this(Stack.defaulDimension);
    }
    public Stack(Integer dimension) {
        if (dimension <= 0) {
            throw new RuntimeException("La cantidad de elementos en la  pila debe ser positiva");
        }
        this.data = (ELEMENT []) new Object[dimension];
        this.count = 0;
    }

    public boolean empty() {
        return this.count <= 0;
    }
    public ELEMENT peek() {
        if (this.empty()) {
            throw new RuntimeException("La pila está vacía...");
        }
        return this.data[this.count - 1];
    }
    public ELEMENT pop() {
        if (this.empty()) {
            throw new RuntimeException("La pila está vacía...");
        }
        --this.count;
        return this.data[this.count];
    }
    public ELEMENT push(ELEMENT element) {
        if (this.size() >= this.data.length) {
//            throw new RuntimeException("La pila está llena...");

            ELEMENT [] temp = (ELEMENT []) new Object[this.data.length * 2];
            for (int i = 0; i < this.data.length; ++i) {
                temp[i] = this.data[i];
            }
            this.data = temp;
        }
        this.data[this.count] = element;
        ++this.count;
        return element;
    }
    public int search(Object object) {
        for (int pos = this.count - 1; pos >= 0; --pos) {
            if (this.data[pos].equals(object)) {
                return this.count - pos;
            }
        }
        return -1;
    }
    public int size() {
        return this.count;
    }

    public String toString() {

        if (this.size() <=0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[" + this.data[0].toString());
        for (int i = 1; i < this.size(); ++i) {
            sb.append(", " + this.data[i].toString());
        }
        sb.append("]");
        return sb.toString();
    }
}