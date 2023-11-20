package codekeepers.modelo;

import codekeepers.util.DatabaseConection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDAO implements GenericDAO<Articulo, String>  {

    private static final String SELECT_ALL = "SELECT * FROM articulo";
    private static final String INSERT = "INSERT INTO articulo VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public Articulo obtenerPorId(String id) {
        return null;
    }

    @Override
    public List<Articulo> obtenerTodos() {
        List<Articulo> articulos = new ArrayList<>();

        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                articulos.add(mapearDesdeResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener todos los artículos", e);
        }

        return articulos;
    }

    @Override
    public void insertar(Articulo articulo) {
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            mapearAStatement(statement, articulo);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar el artículo", e);
        }
    }

    @Override
    public void actualizar(Articulo entidad) {

    }

    @Override
    public void eliminar(String id) {

    }

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
