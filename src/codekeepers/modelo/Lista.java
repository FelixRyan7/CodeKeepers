package codekeepers.modelo;
import java.util.*;

public class Lista<T> {
    protected HashMap<Integer, T> lista;
    private int nextKey;

    public Lista() {
        lista = new HashMap<Integer, T>();
        nextKey = 1;
    }

    public int getNextKey() {
        return nextKey;
    }

    public int getSize() {
        return lista.size();
    }

    public void add(T t) {
        lista.put(nextKey, t);
        nextKey++;
    }

    public void delete(int key) {
        if (lista.containsKey(key)) {
            lista.remove(key);
        } else {
            throw new IndexOutOfBoundsException("El elemento no se puede borrar porque la clave no se encuentra en la lista.");
        }
    }

    public T getAt(int key) {
        if (!lista.containsKey(key)) {
            throw new IndexOutOfBoundsException("Clave no encontrada en la lista.");
        }
        return lista.get(key);
    }

    public void clear() {
        lista.clear();
        nextKey = 1;
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public List<T> getList() {
        return new ArrayList<>(lista.values());
    }

    public HashMap<Integer,T> getItems() {
        return lista;
    }
}