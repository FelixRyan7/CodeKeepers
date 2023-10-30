package codekeepers.vista;
import codekeepers.controlador.Controlador;
import codekeepers.modelo.Articulo;
import codekeepers.modelo.Cliente;
import codekeepers.modelo.ClienteEstandard;
import codekeepers.modelo.ClientePremium;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class GestionOS {
    private Controlador controlador;
    Scanner teclado = new Scanner(System.in);
    public GestionOS() {
        controlador = new Controlador();
    }
    public void inicio() {
        boolean salir = false;
        char opcio;
        do {
            System.out.println("1. Gestión Articulos");
            System.out.println("2. Gestión Clientes");
            System.out.println("3. Gestión Pedidos");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio) {
                case '1':
                    this.gestionArticulos();
                    break;
                case '2':
                    this.gestionClientes();
                    break;
                case '3':
                    this.gestionPedidos();
                    break;
                case '0':
                    salir = true;
                    break;
                default:
                    System.out.println("La elección no es valida. Por favor elija una de las siguientes opciones existentes: 1, 2, 3 o 0");
            }
        } while (!salir);
    }
    char pedirOpcion() {
        String resp;
        System.out.println("Elija una opción (1,2,3 o 0): ");
                resp = teclado.nextLine();
        if (resp.isEmpty()) {
            resp = " ";
        }
        return resp.charAt(0);
    }

    public void gestionArticulos() {
        boolean salir = false;
        char opcion;
        do {
            System.out.println("1. Listar Articulos");
            System.out.println("2. Añadir Articulo");
            System.out.println("0. Volver al menu principal");
            opcion = pedirOpcion();
            switch (opcion) {
                case '1':
                    this.listarArticulos();
                    break;
                case '2':
                    this.addArticulo();
                    break;
                case '0':
                    salir = true;
            }
        } while (!salir);
    }
    public void listarArticulos() {
        System.out.println("\n\n---- LISTA DE ARTICULOS ----\n\n");
        List<Articulo> allArticulos = controlador.showAllArticulos();

        if(allArticulos.isEmpty()) {
            System.out.println("No hay articulos en la lista\n\n");
        } else {
            for (Articulo articulo : allArticulos) {
                System.out.println("\n---- Nº"+ articulo.getId() + " ----");
                System.out.println("Nombre: " + articulo.getNombre());
                System.out.println("Descripción:" + articulo.getDescripcion());
                System.out.println("Precio: " + articulo.getPrecio() + " €");
                System.out.println("Gastos de envio: " + articulo.getGastoEnvio() + " €");
                System.out.println("Tiempo de preparación: " + articulo.getTiempoPreparacion() + " minutos");
                System.out.println("Stock disponible: " + articulo.getTiempoPreparacion() + " unidades");
            }
            System.out.println("\n\n");

        }
    }

    public void addArticulo() {
        System.out.println("\n\n---- AÑADIR NUEVO ARTICULO ----\n\n");
        System.out.println("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("\n");
        while (nombre.isEmpty()) {
            System.out.println("Por favor, introduzca un nombre valido: ");
            nombre = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Descripción: ");
        String descripcion = teclado.nextLine();
        System.out.println("\n");
        while (descripcion.isEmpty()) {
            System.out.println("Por favor, introduzca una descripcion valido: ");
            descripcion = teclado.nextLine();
            System.out.println("\n");

        }
        System.out.println("Precio: ");
        String precio = teclado.nextLine().replace(',', '.');
        System.out.println("\n");
        while (precio.isEmpty() || !isValidFloat(precio)) {
            System.out.println("Por favor, introduzca un precio valido: ");
            precio = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Gastos de envio: ");
        String gastosEnvio = teclado.nextLine().replace(',', '.');
        System.out.println("\n");
        while (gastosEnvio.isEmpty() || !isValidFloat(gastosEnvio)) {
            System.out.println("Por favor, introduzca un gasto de envío valido: ");
            gastosEnvio = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Tiempo de preparación: ");
        String tiempoPreparacion = teclado.nextLine();
        System.out.println("\n");
        while (tiempoPreparacion.isEmpty() || !isValidInt(tiempoPreparacion)) {
            System.out.println("Por favor, introduzca un tiempo de preparación valido: ");
            tiempoPreparacion = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Stock: ");
        String stock = teclado.nextLine();
        System.out.println("\n");
        while (stock.isEmpty() || !isValidInt(stock)) {
            System.out.println("Por favor, introduzca un stock valido: ");
            stock = teclado.nextLine();
            System.out.println("\n");
        }

        controlador.addNewArticulo(
                nombre,
                descripcion,
                Float.parseFloat(precio),
                Float.parseFloat(gastosEnvio),
                Integer.parseInt(tiempoPreparacion),
                Integer.parseInt(stock)
        );

        System.out.println("\nArticulo añadido correctamente!\n --------------\n\n");

    }

    public void gestionClientes() {
        boolean salir = false;
        char opcion;
        do {
            System.out.println("1. Listar Clientes");
            System.out.println("2. Listar Clientes Estandar");
            System.out.println("3. Listar Clientes Premium");
            System.out.println("4. Añadir Cliente");
            System.out.println("0. Volver al menu principal");
            opcion = pedirOpcion();
            switch (opcion) {
                case '1':
                    this.listarClientes();
                    break;
                case '2':
                    this.listarClientesEstandar();
                    break;
                case '3':
                    this.listarClientesPremium();
                    break;
                case '4':
                    this.addCliente();
                    break;
                case '0':
                    salir = true;
            }
        } while (!salir);
    }

    public void listarClientes() {
        System.out.println("\n\n---- LISTA DE CLIENTES ----\n\n");
        List<Cliente> allClientes = controlador.showAllClientes();

        if(allClientes.isEmpty()) {
            System.out.println("No hay clientes en la lista\n\n");
        } else {
            for (Cliente cliente : allClientes) {
                System.out.println("\n-----------");
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Correo electronico:" + cliente.getEmail());
                System.out.println("NIF: " + cliente.getNif());
                System.out.println("Domicilio: " + cliente.getDomicilio());
                System.out.println("Tipo de cliente: " + cliente.tipoCliente());
                if(Objects.equals(cliente.tipoCliente(), "Premium")) {
                    System.out.println("Cuota anual: " + cliente.calcAnual() + " €");
                    System.out.println("Descuento de envio: " + cliente.descuentoEnv() + " %");

                }
            }
            System.out.println("\n\n");
        }
    }

    public void listarClientesEstandar() {
        System.out.println("\n\n---- LISTA DE CLIENTES ESTANDAR ----\n\n");
        List<ClienteEstandard> clienteEstandards = controlador.showClientesEstandar();

        if(clienteEstandards.isEmpty()) {
            System.out.println("No hay clientes estandar en la lista\n\n");
        } else {
            for (Cliente cliente : clienteEstandards) {
                System.out.println("\n-----------");
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Correo electronico:" + cliente.getEmail());
                System.out.println("NIF: " + cliente.getNif());
                System.out.println("Domicilio: " + cliente.getDomicilio());
            }
            System.out.println("\n\n");
        }
    }

    public void listarClientesPremium() {
        System.out.println("\n\n---- LISTA DE CLIENTES PREMIUM ----\n\n");
        List<ClientePremium> clientePremiums = controlador.showClientesPremium();

        if(clientePremiums.isEmpty()) {
            System.out.println("No hay clientes premium en la lista\n\n");
        } else {
            for (Cliente cliente : clientePremiums) {
                System.out.println("\n-----------");
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Correo electronico:" + cliente.getEmail());
                System.out.println("NIF: " + cliente.getNif());
                System.out.println("Domicilio: " + cliente.getDomicilio());
                System.out.println("Tipo de cliente: " + cliente.tipoCliente());
                System.out.println("Cuota anual: " + cliente.calcAnual() + " €");
                System.out.println("Descuento de envio: " + cliente.descuentoEnv() + " %");
            }
            System.out.println("\n\n");
        }
    }

    public void addCliente() {
        System.out.println("\n---- AÑADIR CLIENTE ----\n");
        System.out.println("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("\n");
        while (nombre.isEmpty()) {
            System.out.println("Por favor, introduzca un nombre valido: ");
            nombre = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Correo electronico: ");
        String email = teclado.nextLine();
        System.out.println("\n");
        while (email.isEmpty()) {
            System.out.println("Por favor, introduzca un correo electronico valido: ");
            email = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("NIF: ");
        String nif = teclado.nextLine();
        System.out.println("\n");
        while (nif.isEmpty()) {
            System.out.println("Por favor, introduzca un NIF valido: ");
            nif = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Domicilio: ");
        String domicilio = teclado.nextLine();
        System.out.println("\n");
        while (domicilio.isEmpty()) {
            System.out.println("Por favor, introduzca un domicilio valido: ");
            domicilio = teclado.nextLine();
            System.out.println("\n");
        }

        System.out.println("Este será un cliente (1) Premium o (2) Estandar?");
        System.out.println("Elija una opción: (1 o 2)");
        String  tipoCliente = teclado.nextLine();
        System.out.println("\n");
        while (tipoCliente.isEmpty() || (!tipoCliente.equals("1") && !tipoCliente.equals("2"))) {
            System.out.println("Por favor, introduzca una opción válido: ");
            tipoCliente = teclado.nextLine();
            System.out.println("\n");
        }

        controlador.addCliente(email, nombre, nif, domicilio, tipoCliente.equals("1"));
        System.out.println("\nCliente añadido correctamente!\n --------------\n\n");

    }

    public void gestionPedidos() {
        boolean salir = false;
        char opcion;
        do {
            System.out.println("1. Listar pedidos pendientes");
            System.out.println("2. Listar pedidos enviados");
            System.out.println("3. Hacer pedido");
            System.out.println("4. Anular pedido");
            System.out.println("0. Volver al menu principal");
            opcion = pedirOpcion();
            switch (opcion) {
                case '1':
                    this.listarPedidosPendientes();
                    break;
                case '2':
                    this.listarPedidosEnviados();
                    break;
                case '3':
                    this.addPedido();
                    break;
                case '4':
                    this.anularPedido();
                    break;
                case '0':
                    salir = true;
            }
        } while (!salir);
    }

    public void listarPedidosPendientes() {
        System.out.println("LISTA DE PEDIDOS PENDIENTES");
    }
    public void listarPedidosEnviados() {
        System.out.println("LISTA DE PEDIDOS ENVIADOS");
    }

    public void addPedido() {
        System.out.println("HACER PEDIDO");
    }

    public void anularPedido() {
        System.out.println("ANULAR PEDIDO");
    }

    public static boolean isValidFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

