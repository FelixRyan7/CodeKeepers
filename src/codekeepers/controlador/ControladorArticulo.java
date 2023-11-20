package codekeepers.controlador;


import codekeepers.modelo.*;

import java.util.List;

public class ControladorArticulo {

    private final ArticuloDAO articuloDAO;

    public ControladorArticulo(ArticuloDAO articuloDAO) {
        this.articuloDAO = articuloDAO;
    }

    public List<Articulo> showAllArticulos() {
        //metodo para obtener los datos desde la base de datos usando el metodo ObtenerTodos de la clase generica GenericDAO definida despues en ArticuloDAO
        return articuloDAO.obtenerTodos();
    }



    public Articulo addNewArticulo(String nombre, String descripcion, float precio, float gastoEnvio, int tiempoPreparacion, int stock) {
        // Crear un nuevo objeto Articulo con los parámetros proporcionados
        Articulo nuevoArticulo = new Articulo(
                null,  // Dejar que la base de datos genere el ID
                nombre,
                descripcion,
                precio,
                gastoEnvio,
                tiempoPreparacion,
                stock
        );

        // Llamar al método insertar en ArticuloDAO para almacenar el nuevo artículo en la base de datos
        articuloDAO.insertar(nuevoArticulo);

        return nuevoArticulo;

}
}