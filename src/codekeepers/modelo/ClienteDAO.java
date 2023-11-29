package codekeepers.modelo;

import codekeepers.util.DatabaseConection;
import codekeepers.modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements GenericDAO<Cliente, String> {

    // Convertimos unas consultas SQL en constantes
    private static final String SELECT_ALL = "SELECT * FROM cliente";
    private static final String SELECT_PREMIUM = "SELECT * FROM cliente WHERE tipo = 'premium'";
    private static final String SELECT_ESTANDAR = "SELECT * FROM cliente WHERE tipo = 'estandar'";
    private static final String INSERT = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?)";


    // Método para obtener un cliente por su ID -------------------------------------------------
    @Override
    public Cliente obtenerPorId(String id_cliente) {
        Cliente cliente = null;

        // La consulta para obtener un cliente por su id
        String query = "SELECT * FROM cliente WHERE id_cliente = ?";

        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id_cliente);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = mapearDesdeResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el cliente por email", e);
        }

        return cliente;
    }


    // Método para obtener un cliente por su EMAIL -------------------------------------------------
    public Cliente obtenerPorEmail(String email) {
        Cliente cliente = null;

        // La consulta para obtener un cliente por su email
        String query2 = "SELECT * FROM cliente WHERE email = ?";

        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query2)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = mapearDesdeResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el cliente por email", e);
        }

        return cliente;
    }


    // Metodo para obtener una lista de todos los clientes -------------------------------------------------
    @Override
    public List<Cliente> obtenerTodos() {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                clientes.add(mapearDesdeResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener todos los clientes", e);
        }

        return clientes;
    }



    // Metodo para obtener una lista de los cliente premium -------------------------------------------------
    public List<Cliente> obtenerClientesPremium() {
        List<Cliente> clientesPremium = new ArrayList<>();

        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PREMIUM);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String tipoCliente = resultSet.getString("tipo");

                if ("premium".equalsIgnoreCase(tipoCliente)) {
                    clientesPremium.add(new ClientePremium(
                            resultSet.getString("id_cliente"),
                            resultSet.getString("nombre"),
                            resultSet.getString("nif"),
                            resultSet.getString("domicilio"),
                            resultSet.getString("email")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener clientes premium", e);
        }

        return clientesPremium;
    }


    // Metodo para obtener una lista de los cliente estandar -------------------------------------------------
    public List<Cliente> obtenerClientesEstandar() {
        List<Cliente> clientesEstandar = new ArrayList<>();

        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ESTANDAR);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String tipoCliente = resultSet.getString("tipo");

                if ("estandar".equalsIgnoreCase(tipoCliente)) {
                    clientesEstandar.add(new ClienteEstandard(
                            resultSet.getString("id_cliente"),
                            resultSet.getString("nombre"),
                            resultSet.getString("nif"),
                            resultSet.getString("domicilio"),
                            resultSet.getString("email")

                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener clientes estándar", e);
        }

        return clientesEstandar;
    }


    // Metodo para insertar un nuevo cliente en la base de datos -------------------------------------------------
    @Override
    public void insertar(Cliente cliente) {
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            mapearAStatement(statement, cliente);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar el cliente", e);
        }
    }

    @Override
    public void actualizar(Cliente entidad) {

    }

    @Override
    public void eliminar(String id) {

    }


    // Método para mapear una fila de ResultSet a un objeto Cliente
    private Cliente mapearDesdeResultSet(ResultSet resultSet) throws SQLException {
        // Se extrae el tipo de cliente de la columna "tipo" en el ResultSet
        String tipoCliente = resultSet.getString("tipo");

        // Se compara el tipo de cliente con un if y se crea el objeto Cliente correspondiente (premium o estandar)
        if ("premium".equalsIgnoreCase(tipoCliente)) {
            return new ClientePremium(
                    resultSet.getString("id_cliente"),
                    resultSet.getString("nombre"),
                    resultSet.getString("nif"),
                    resultSet.getString("domicilio"),
                    resultSet.getString("email")

            );
        } else {
            return new ClienteEstandard(
                    resultSet.getString("id_cliente"),
                    resultSet.getString("nombre"),
                    resultSet.getString("nif"),
                    resultSet.getString("domicilio"),
                    resultSet.getString("email")

            );
        }
    }

    // Método para mapear un objeto Cliente a una consulta SQL parametrizada
    private void mapearAStatement(PreparedStatement statement, Cliente cliente) throws SQLException {
        // Se asigna los valores del objeto Cliente a los parámetros de la consulta SQL
        statement.setString(1, cliente.getId());
        statement.setString(2, cliente.getNombre());
        statement.setString(3, cliente.getNif());
        statement.setString(4, cliente.getDomicilio());
        statement.setString(5, cliente.getEmail());
        statement.setString(6, cliente.tipoCliente());

    }
}
