package codekeepers.modelo;
import java.util.ArrayList;

public class Lista<T> {
    protected ArrayList<T> lista;
    public Lista() {
        lista = new ArrayList<>();
    }
    public int getSize() {
        return lista.size();
    }
    public void add(T t) {
        lista.add(t);
    }
    public void borrar(T t) {
        lista.remove(t);
    }
    public T getAt(int position) {
        if (position < 0 || position >= lista.size()) {
            throw new IndexOutOfBoundsException("Posición fuera de los límites.");
        }
        return lista.get(position);
    }
    public void clear() {
        lista.clear();
    }
    public boolean isEmpty() {
        if (lista.isEmpty()) {
            System.out.println("La lista está vacía");
            return true;
        } else {
            System.out.println("La lista no está vacía");
            return false;
        }
    }
    public ArrayList<T> getArrayList() {
        ArrayList<T> arrlist = new ArrayList<>(lista);
        return arrlist;
    }
}
