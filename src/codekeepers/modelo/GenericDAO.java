package codekeepers.modelo;



import java.util.List;

public interface GenericDAO<T, K> {

    T obtenerPorId(K id);

    List<T> obtenerTodos();

    void insertar(T entidad);

    void actualizar(T entidad);

    void eliminar(K id);
}

