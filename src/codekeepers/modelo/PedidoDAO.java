package codekeepers.modelo;
import codekeepers.util.DatabaseConection;
import codekeepers.modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
public class PedidoDAO implements GenericDAO<Pedido, Integer> {

    // Convertimos unas consultas SQL en constantes
    private static final String SELECT_PEDIDOS_PENDIENTES =
            "SELECT * FROM pedido p " +
                    "JOIN articulo a ON p.id_articulo = a.id_articulo " +
                    "WHERE TIMESTAMPADD(MINUTE, a.tiempo_preparacion, p.fecha_hora_pedido) > NOW()";

    private static final String SELECT_PEDIDOS_ENVIADOS =
            "SELECT * FROM pedido p " +
                    "JOIN articulo a ON p.id_articulo = a.id_articulo " +
                    "WHERE TIMESTAMPADD(MINUTE, a.tiempo_preparacion, p.fecha_hora_pedido) <= NOW()";


    private static final String SELECT_PEDIDOS_ENVIADOS_POR_CLIENTE =
            "SELECT p.*, a.*, c.* " +
                    "FROM pedido p " +
                    "JOIN articulo a ON p.id_articulo = a.id_articulo " +
                    "JOIN cliente c ON p.id_cliente = c.id_cliente " +
                    "WHERE TIMESTAMPADD(MINUTE, a.tiempo_preparacion, p.fecha_hora_pedido) <= NOW() " +
                    "AND c.email = ?";

    private static final String SELECT_PEDIDOS_PENDIENTES_POR_CLIENTE =
            "SELECT p.*, a.*, c.* " +
                    "FROM pedido p " +
                    "JOIN articulo a ON p.id_articulo = a.id_articulo " +
                    "JOIN cliente c ON p.id_cliente = c.id_cliente " +
                    "WHERE TIMESTAMPADD(MINUTE, a.tiempo_preparacion, p.fecha_hora_pedido) > NOW() " +
                    "AND c.email = ?";

    private static final String INSERTAR_PEDIDO =
            "INSERT INTO pedido (id_pedido, id_articulo, id_cliente, cantidad, precio, fecha_hora_pedido) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String ELIMINAR_PEDIDO = "DELETE FROM pedido WHERE id_pedido = ?";

    // Método para obtener un pedido por su ID -------------------------------------------------
    @Override
    public Pedido obtenerPorId(Integer id) {
        String query = "SELECT * FROM pedido WHERE id_pedido = ?";

        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearDesdeResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el pedido por ID", e);
        }

        // Si no se encuentra un pedido con el ID proporcionado, se devuelve null
        return null;
    }


    // Método para obtener solamente los pedidos pendientes de envio (los que poseen un articulo cuyo tiempo de preparacion no ha finalizado) -------------------------------------------------
    public List<Pedido> obtenerPedidosPendientesEnvio() {
        List<Pedido> pedidosPendientes = new ArrayList<>();

        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PEDIDOS_PENDIENTES);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                pedidosPendientes.add(mapearDesdeResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener pedidos pendientes de envío", e);
        }

        return pedidosPendientes;
    }

    // Método para obtener solamente los pedidos pendientes de envio de un determinado cliente -------------------------------------------------
    public List<Pedido> obtenerPedidosPendientesPorCliente(String emailCliente) {
        List<Pedido> pedidosPendientes = new ArrayList<>();

        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PEDIDOS_PENDIENTES_POR_CLIENTE)) {

            statement.setString(1, emailCliente);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    pedidosPendientes.add(mapearDesdeResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener pedidos pendientes de envío por cliente", e);
        }

        return pedidosPendientes;
    }

    // Método para obtener solamente los pedidos enviados (los que poseen un articulo cuyo tiempo de preparacion ha finalizado) -------------------------------------------------
    public List<Pedido> obtenerPedidosEnviados() {
        List<Pedido> pedidosEnviados = new ArrayList<>();

        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PEDIDOS_ENVIADOS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                pedidosEnviados.add(mapearDesdeResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener pedidos enviados", e);
        }

        return pedidosEnviados;
    }


    // Método para obtener solamente los pedidos enviados de un determinado cliente -------------------------------------------------
    public List<Pedido> obtenerPedidosEnviadosPorCliente(String emailCliente) {
        List<Pedido> pedidosEnviados = new ArrayList<>();

        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PEDIDOS_ENVIADOS_POR_CLIENTE)) {

            statement.setString(1, emailCliente);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    pedidosEnviados.add(mapearDesdeResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener pedidos enviados por cliente", e);
        }

        return pedidosEnviados;
    }

    @Override
    public List<Pedido> obtenerTodos() {
        return null;
    }

    //Método para insertar un nuevo pedido en la base de datos --------------------------------------------
    @Override
    public void insertar(Pedido pedido) {


        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERTAR_PEDIDO)) {

            mapearAStatement(statement, pedido);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar pedido en la base de datos", e);
        }
    }


    @Override
    public void actualizar(Pedido entidad) {

    }

    //Método para eliminar un pedido de la base de datos ------------------------------------------------
    @Override
    public void eliminar(Integer id) {
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ELIMINAR_PEDIDO)) {

            statement.setInt(1, id);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas == 0) {
                System.out.println("No se encontró un pedido con el ID: " + id);

            } else {
                System.out.println("Pedido eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar el pedido de la base de datos", e);
        }
    }

    // Método para mapear una fila de ResultSet a un objeto Pedido
    private Pedido mapearDesdeResultSet(ResultSet resultSet) throws SQLException {
        String idPedido = resultSet.getString("id_pedido");
        String idArticulo = resultSet.getString("id_articulo");
        String idCliente = resultSet.getString("id_cliente");
        int cantidad = resultSet.getInt("cantidad");
        float precio = resultSet.getFloat("precio");
        LocalDateTime fechaHora = resultSet.getTimestamp("fecha_hora_pedido").toLocalDateTime();

        //Hacemos uso de los dos metodos siguiente para obtener tanto el cliente como el articulo por su id y asi pasarlos como parametros para crear el nuevo pedido
        Cliente cliente = obtenerClientePorId(idCliente);
        Articulo articulo = obtenerArticuloPorId(idArticulo);

        return new Pedido(idPedido, cliente, articulo, cantidad, precio, fechaHora);
    }

    // Métodos usados anteriormente para obtener Cliente y Articulo existentes por su id
    private Cliente obtenerClientePorId(String idCliente) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.obtenerPorId(idCliente);
    }

    private Articulo obtenerArticuloPorId(String idArticulo) {
        ArticuloDAO articuloDAO = new ArticuloDAO();
        return articuloDAO.obtenerPorId(idArticulo);
    }

    // Método para mapear un objeto Articulo a una consulta SQL parametrizada
    private void mapearAStatement(PreparedStatement statement, Pedido pedido) throws SQLException {

        //como en la tabla pedido debemos insertar el id del cliente y el id del articulo asociado, sacamos el id de cada uno y lo guardamos en una variable
        String idCliente = pedido.getCliente().getId();
        String idArticulo = pedido.getArticulo().getId();


        statement.setString(1, pedido.getId());
        statement.setInt(2, Integer.parseInt(idArticulo));
        statement.setInt(3, Integer.parseInt(idCliente));
        statement.setInt(4, pedido.getCantidadArticulo());
        statement.setFloat(5, pedido.getPrecioPedido());
        statement.setTimestamp(6, Timestamp.valueOf(pedido.getFechaHora()));


    }
}
