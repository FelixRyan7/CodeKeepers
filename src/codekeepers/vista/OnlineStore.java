package codekeepers.vista;

public class OnlineStore {
    public static void main(String[] args) {
        // TEST DB CONNECTION
//        try{
//            Connection connection = DatabaseConection.getConnection();
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente");
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                System.out.println("Cliente: " + resultSet.getInt("id_cliente"));
//                System.out.println("Nombre: " + resultSet.getString("nombre"));
//                System.out.println("Email: " + resultSet.getString("email"));
//            }
//
//            connection.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        GestionOS gestion = new GestionOS();
        gestion.inicio();
    }
}
