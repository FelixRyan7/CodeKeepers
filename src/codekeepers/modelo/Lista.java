package codekeepers.modelo;
import java.util.*;

public class Lista<T> {
    protected HashMap<Object, T> lista;
    private Object lastKey;

    public Lista() {
        lista = new HashMap<Object, T>();
        lastKey = null;
    }

    public Object getLastKey() {
        return lastKey;
    }

    public int getSize() {
        return lista.size();
    }

    public void add(Object key, T t) {
        lista.put(key, t);
        lastKey = key;
    }

    public void delete(int key) {
        if (lista.containsKey(key)) {
            lista.remove(key);
        } else {
            throw new IndexOutOfBoundsException("El elemento no se puede borrar porque la clave no se encuentra en la lista.");
        }
    }

    public T get(int key) {
        if (!lista.containsKey(key)) {
            throw new IndexOutOfBoundsException("Clave no encontrada en la lista.");
        }
        return lista.get(key);
    }

    public void clear() {
        lista.clear();
        lastKey = null;
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public List<T> getList() {
        return new ArrayList<>(lista.values());
    }

    public HashMap<Object,T> getItems() {
        return lista;
    }
}