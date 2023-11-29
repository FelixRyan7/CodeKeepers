package codekeepers.modelo;

import codekeepers.util.DatabaseConection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDAO implements GenericDAO<Articulo, String>  {

    // Convertimos unas consultas SQL en constantes
    private static final String SELECT_ALL = "SELECT * FROM articulo";
    private static final String INSERT = "INSERT INTO articulo VALUES (?, ?, ?, ?, ?, ?, ?)";
    String OBTENER_TIEMPO_PREPARACION = "SELECT tiempo_preparacion FROM articulo WHERE id_articulo = ?";


    // Método para obtener un artículo por su ID ----------------------------------------------
    @Override
    public Articulo obtenerPorId(String id) {
        Articulo articulo = null;

        // consulta para obtener un artículo por su ID
        String query = "SELECT * FROM articulo WHERE id_articulo = ?";

        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);
            // Ejecuta la consulta y mapea el resultado a un objeto Articulo
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    articulo = mapearDesdeResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el artículo por ID", e);
        }

        return articulo;
    }

    // Método para obtener todos los artículos -------------------------------------------------
    @Override
    public List<Articulo> obtenerTodos() {
        List<Articulo> articulos = new ArrayList<>();

        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            // Aqui se itera sobre los resultados y mapea cada fila a un objeto Articulo
            while (resultSet.next()) {
                articulos.add(mapearDesdeResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener todos los artículos", e);
        }

        return articulos;
    }

    // Método para insertar un articulo en la bbdd -------------------------------------------------
    @Override
    public void insertar(Articulo articulo) {
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            // Aqui se mapea los valores del artículo a la consulta SQL
            mapearAStatement(statement, articulo);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar el artículo", e);
        }
    }

    // Método para obtener el tiempo de preparación de un artículo por su ID (Se usara para validar si un pedido que contenga un determinado articulo se puede eliminar o no)
    public int obtenerTiempoPreparacion(String idArticulo) {


        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(OBTENER_TIEMPO_PREPARACION)) {

            preparedStatement.setString(1, idArticulo);
            // Ejecuta la consulta OBTENER_TIEMPO_PREPARACION y devuelve el tiempo de preparación
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("tiempo_preparacion");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el tiempo de preparación del artículo", e);
        }

        // Se devuelve un valor por defecto en caso de no encontrar el artículo
        return 0;
    }

    //Metodo heredado de clase generica no implementado de momento
    @Override
    public void actualizar(Articulo entidad) {

    }
    //Metodo heredado de clase generica no implementado de momento
    @Override
    public void eliminar(String id) {

    }

    // Método para mapear una fila de ResultSet a un objeto Articulo
    // Sirve para convertir la información obtenida de la base de datos en un objeto de Java de tipo Articulo.
    private Articulo mapearDesdeResultSet(ResultSet resultSet) throws SQLException {
        return new Articulo(
                resultSet.getString("id_articulo"),
                resultSet.getString("nombre"),
                resultSet.getString("descripcion"),
                resultSet.getFloat("precio"),
                resultSet.getFloat("gasto_envio"),
                resultSet.getInt("tiempo_preparacion"),
                resultSet.getInt("stock")
        );
    }

    // Método para mapear un objeto Articulo a una consulta SQL parametrizada
    // Sirve para preparar y asignar los valores de un objeto Articulo a una consulta SQL parametrizada
    private void mapearAStatement(PreparedStatement statement, Articulo articulo) throws SQLException {
        statement.setString(1, articulo.getId());
        statement.setString(2, articulo.getNombre());
        statement.setString(3, articulo.getDescripcion());
        statement.setFloat(4, articulo.getPrecio());
        statement.setFloat(5, articulo.getGastoEnvio());
        statement.setInt(6, articulo.getTiempoPreparacion());
        statement.setInt(7, articulo.getStock());
    }

}
