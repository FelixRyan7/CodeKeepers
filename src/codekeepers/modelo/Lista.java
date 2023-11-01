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

    public void delete(Object key) {
        if (lista.containsKey(key)) {
            lista.remove(key);
        } else {
            throw new IndexOutOfBoundsException("El elemento no se puede borrar porque la clave no se encuentra en la lista.");
        }
    }

    public boolean exists(Object key) {
        return lista.containsKey(key);
    }

    public T get(Object key) {
        if (!lista.containsKey(key)) {
            throw new IndexOutOfBoundsException("Clave no encontrada en la lista.");
        }
        return lista.get(key);
    }

    public void update(Object key, T item) {
        if (!lista.containsKey(key)) {
            throw new IndexOutOfBoundsException("Clave no encontrada en la lista.");
        }
        lista.put(key, item);
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