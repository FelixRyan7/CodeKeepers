package codekeepers.vista;
import codekeepers.controlador.Controlador;
import codekeepers.modelo.*;

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
            String opciones = "(1,2,3 o 0): ";
            System.out.println("\n\n");
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Gestión Articulos");
            System.out.println("2. Gestión Clientes");
            System.out.println("3. Gestión Pedidos");
            System.out.println("0. Salir");
            opcio = pedirOpcion(opciones);
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
                    System.out.println("\n---------------");
                    System.out.println("Hasta pronto!");
                    System.out.println("\n");

                    salir = true;
                    break;
                default:
                    System.out.println("La elección no es valida. Por favor elija una de las siguientes opciones existentes: 1, 2, 3 o 0");
            }
        } while (!salir);
    }
    char pedirOpcion(String opciones) {
        String resp;
        System.out.println("Elija una opción " + opciones);
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
            String opciones = "(1,2 o 0): ";
            System.out.println("\n\n");
            System.out.println("MENU GESTION DE ARTICULOS");
            System.out.println("1. Listar Articulos");
            System.out.println("2. Añadir Articulo");
            System.out.println("0. Volver al menu principal");
            opcion = pedirOpcion(opciones);
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
        System.out.println("\n\n---- LISTA DE ARTICULOS ----\n");
        List<Articulo> allArticulos = controlador.showAllArticulos();

        if(allArticulos.isEmpty()) {
            System.out.println("No hay articulos en la lista\n");
        } else {
            for (Articulo articulo : allArticulos) {
                System.out.println("\n---- Nº"+ articulo.getId() + " ----");
                System.out.println("Nombre: " + articulo.getNombre());
                System.out.println("Descripción:" + articulo.getDescripcion());
                System.out.println("Precio: " + articulo.getPrecio() + " €");
                System.out.println("Gastos de envio: " + articulo.getGastoEnvio() + " €");
                System.out.println("Tiempo de preparación: " + articulo.getTiempoPreparacion() + " minuto/s");
                System.out.println("Stock disponible: " + articulo.getStock() + " unidad/es");
            }
            System.out.println("\n");

        }
    }

    public void addArticulo() {
        System.out.println("\n\n---- AÑADIR NUEVO ARTICULO ----\n");
        System.out.println("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("\n");
        while (nombre.isEmpty()) {
            if(volverAlMenu(nombre)) return;
            System.out.println("Por favor, introduzca un nombre valido: ");
            nombre = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Descripción: ");
        String descripcion = teclado.nextLine();
        System.out.println("\n");
        while (descripcion.isEmpty()) {
            if(volverAlMenu(descripcion)) return;
            System.out.println("Por favor, introduzca una descripcion valido: ");
            descripcion = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Precio: ");
        String precio = teclado.nextLine().replace(',', '.');
        System.out.println("\n");
        while (precio.isEmpty() || !isValidFloat(precio)) {
            if(volverAlMenu(precio)) return;
            System.out.println("Por favor, introduzca un precio valido: ");
            precio = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Gastos de envio: ");
        String gastosEnvio = teclado.nextLine().replace(',', '.');
        System.out.println("\n");
        while (gastosEnvio.isEmpty() || !isValidFloat(gastosEnvio)) {
            if(volverAlMenu(gastosEnvio)) return;
            System.out.println("Por favor, introduzca un gasto de envío valido: ");
            gastosEnvio = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Tiempo de preparación: ");
        String tiempoPreparacion = teclado.nextLine();
        System.out.println("\n");
        while (tiempoPreparacion.isEmpty() || !isValidInt(tiempoPreparacion)) {
            if(volverAlMenu(tiempoPreparacion)) return;
            System.out.println("Por favor, introduzca un tiempo de preparación valido: ");
            tiempoPreparacion = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Stock: ");
        String stock = teclado.nextLine();
        System.out.println("\n");
        while (stock.isEmpty() || !isValidInt(stock)) {
            if(volverAlMenu(stock)) return;
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

        System.out.println("\nArticulo añadido correctamente!\n --------------\n");

    }

    public void gestionClientes() {
        boolean salir = false;
        char opcion;
        do {
            String opciones = "(1,2,3,4 o 0): ";
            System.out.println("\n\n");
            System.out.println("MENU GESTION DE CLIENTES");
            System.out.println("1. Listar Clientes");
            System.out.println("2. Listar Clientes Estandar");
            System.out.println("3. Listar Clientes Premium");
            System.out.println("4. Añadir Cliente");
            System.out.println("0. Volver al menu principal");
            opcion = pedirOpcion(opciones);
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
        System.out.println("\n\n---- LISTA DE CLIENTES ----\n");
        List<Cliente> allClientes = controlador.showAllClientes();

        if(allClientes.isEmpty()) {
            System.out.println("No hay clientes en la lista\n");
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
            System.out.println("\n");
        }
    }

    public void listarClientesEstandar() {
        System.out.println("\n\n---- LISTA DE CLIENTES ESTANDAR ----\n");
        List<ClienteEstandard> clienteEstandards = controlador.showClientesEstandar();

        if(clienteEstandards.isEmpty()) {
            System.out.println("No hay clientes estandar en la lista\n");
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
        System.out.println("\n\n---- LISTA DE CLIENTES PREMIUM ----\n");
        List<ClientePremium> clientePremiums = controlador.showClientesPremium();

        if(clientePremiums.isEmpty()) {
            System.out.println("No hay clientes premium en la lista\n");
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
            System.out.println("\n");
        }
    }

    public void addCliente() {
        System.out.println("\n---- AÑADIR CLIENTE ----\n");
        System.out.println("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("\n");
        while (nombre.isEmpty()) {
            if(volverAlMenu(nombre)) return;
            System.out.println("Por favor, introduzca un nombre valido: ");
            nombre = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Correo electronico: ");
        String email = teclado.nextLine();
        System.out.println("\n");
        while (email.isEmpty()) {
            if(volverAlMenu(email)) return;
            System.out.println("Por favor, introduzca un correo electronico valido: ");
            email = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("NIF: ");
        String nif = teclado.nextLine();
        System.out.println("\n");
        while (nif.isEmpty()) {
            if(volverAlMenu(nif)) return;
            System.out.println("Por favor, introduzca un NIF valido: ");
            nif = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Domicilio: ");
        String domicilio = teclado.nextLine();
        System.out.println("\n");
        while (domicilio.isEmpty()) {
            if(volverAlMenu(domicilio)) return;
            System.out.println("Por favor, introduzca un domicilio valido: ");
            domicilio = teclado.nextLine();
            System.out.println("\n");
        }

        System.out.println("Este será un cliente (1) Premium o (2) Estandar?");
        System.out.println("Elija una opción: 1,2 o 0");
        String  tipoCliente = teclado.nextLine();
        System.out.println("\n");
        while (tipoCliente.isEmpty() || (!tipoCliente.equals("1") && !tipoCliente.equals("2"))) {
            if(volverAlMenu(tipoCliente)) return;
            System.out.println("Por favor, introduzca una opción válido: ");
            tipoCliente = teclado.nextLine();
            System.out.println("\n");
        }

        Cliente cliente = controlador.addCliente(email, nombre, nif, domicilio, tipoCliente.equals("1"));
        System.out.println("\nCliente añadido correctamente!\n --------------\n");
    }

    public void gestionPedidos() {
        boolean salir = false;
        char opcion;
        do {
            String opciones = "(1,2,3,4,5,6 o 0): ";
            System.out.println("\n\n");
            System.out.println("MENU GESTION DE PEDIDOS");
            System.out.println("1. Listar pedidos pendientes");
            System.out.println("2. Listar pedidos pendientes por cliente");
            System.out.println("3. Listar pedidos enviados");
            System.out.println("4. Listar pedidos enviados por cliente");
            System.out.println("5. Hacer pedido");
            System.out.println("6. Anular pedido");
            System.out.println("0. Volver al menu principal");
            opcion = pedirOpcion(opciones);
            switch (opcion) {
                case '1':
                    this.listarPedidosPendientes();
                    break;
                case '2':
                    this.listarPedidosPendientesCliente();
                    break;
                case '3':
                    this.listarPedidosEnviados();
                    break;
                case '4':
                    this.listarPedidosEnviadosCliente();
                    break;
                case '5':
                    this.addPedido();
                    break;
                case '6':
                    this.anularPedido();
                    break;
                case '0':
                    salir = true;
            }
        } while (!salir);
    }

    public void listarPedidosPendientes() {
        System.out.println("\n\n---- LISTA DE PEDIDOS PENDIENTES ----\n");
        List<Pedido> pedidosPendientes = controlador.showPedidosPendientes();

        if(pedidosPendientes.isEmpty()) {
            System.out.println("No hay pedidos pendientes en la lista\n");
        } else {
            for (Pedido pedido : pedidosPendientes) {
                printPedido(pedido);
            }
            System.out.println("\n");
        }
    }
    public void listarPedidosPendientesCliente() {
        System.out.println("\n\n---- LISTA DE PEDIDOS PENDIENTES POR CLIENTE ----\n");
        listarClientes();
        System.out.println("Correo electronico del cliente: ");
        String cliente = teclado.nextLine();
        System.out.println("\n");
        while (cliente.isEmpty()) {
            if(volverAlMenu(cliente)) return;
            System.out.println("Por favor, introduzca un correo electronico de cliente valido: ");
            cliente = teclado.nextLine();
            System.out.println("\n");
        }
        List<Pedido> pedidosPendientes = controlador.showPedidosPendientesCliente(cliente);

        if(pedidosPendientes.isEmpty()) {
            System.out.println("No hay pedidos pendientes de este cliente en la lista\n");
        } else {
            for (Pedido pedido : pedidosPendientes) {
                printPedido(pedido);
            }
            System.out.println("\n");
        }
    }
    public void listarPedidosEnviados() {
        System.out.println("\n\n---- LISTA DE PEDIDOS ENVIADOS ----\n");
        List<Pedido> pedidosPendientes = controlador.showPedidosEnviados();

        if(pedidosPendientes.isEmpty()) {
            System.out.println("No hay pedidos enviados en la lista\n");
        } else {
            for (Pedido pedido : pedidosPendientes) {
                printPedido(pedido);
            }
            System.out.println("\n");
        }
    }

    public void listarPedidosEnviadosCliente() {
        System.out.println("\n\n---- LISTA DE PEDIDOS ENVIADOS POR CLIENTE ----\n");
        listarClientes();
        System.out.println("Correo electronico del cliente: ");
        String cliente = teclado.nextLine();
        System.out.println("\n");
        while (cliente.isEmpty()) {
            if(volverAlMenu(cliente)) return;
            System.out.println("Por favor, introduzca un correo electronico de cliente valido: ");
            cliente = teclado.nextLine();
            System.out.println("\n");
        }

        List<Pedido> pedidosPendientes = controlador.showPedidosEnviadosCliente(cliente);
        if(pedidosPendientes.isEmpty()) {
            System.out.println("No hay pedidos enviador de este cliente en la lista\n");
        } else {
            for (Pedido pedido : pedidosPendientes) {
                printPedido(pedido);
            }
            System.out.println("\n");
        }
    }

    public void addPedido() {
        System.out.println("\n---- HACER PEDIDO ----\n");
        System.out.println("Ya es (1) cliente o (2) necesita registrarse?");
        System.out.println("Elija una opción: 1, 2 o 0");
        String esCliente = teclado.nextLine();
        System.out.println("\n");
        while (esCliente.isEmpty() || (!esCliente.equals("1") && !esCliente.equals("2"))) {
            if(volverAlMenu(esCliente)) return;
            System.out.println("Por favor, introduzca una opción válida: ");
            esCliente = teclado.nextLine();
            System.out.println("\n");
        }
        String cliente;
        if(esCliente.equals("2")) {
            this.addCliente();
        }

        listarClientes();
        System.out.println("Correo electronico del cliente: ");
        cliente = teclado.nextLine();
        System.out.println("\n");
        while (cliente.isEmpty()) {
            System.out.println("Por favor, introduzca un correo electronico de cliente valido: ");
            cliente = teclado.nextLine();
            System.out.println("\n");
        }

        listarArticulos();
        System.out.println("Numero del articulo: ");
        String articulo = teclado.nextLine();
        System.out.println("\n");
        while (articulo.isEmpty()) {
            if(volverAlMenu(cliente)) return;
            System.out.println("Por favor, introduzca un numero de articulo valido: ");
            articulo = teclado.nextLine();
            System.out.println("\n");
        }
        System.out.println("Cantidad: ");
        String cantidad = teclado.nextLine();
        System.out.println("\n");
        while (cantidad.isEmpty()) {
            if(volverAlMenu(cliente)) return;
            System.out.println("Por favor, introduzca una cantidad valida: ");
            cantidad = teclado.nextLine();
            System.out.println("\n");
        }

        Pedido nuevoPedido = controlador.addPedido(cliente, Integer.parseInt(articulo), Integer.parseInt(cantidad));
        System.out.println("\nPedido creado correctamente!\n"+ nuevoPedido.toString() +"--------------\n");
    }

    public void anularPedido() {
        System.out.println("\n---- ANULAR PEDIDO ----\n");
        listarPedidosPendientes();
        System.out.println("\nEsta es la lista de pedidos que pueden ser cancelados\n");

        System.out.println("Introduzca el numero de pedido que desea cancelar: ");
        String pedido = teclado.nextLine();
        System.out.println("\n");
        while (pedido.isEmpty() || !isValidInt(pedido)) {
            if(volverAlMenu(pedido)) return;
            System.out.println("Por favor, introduzca un numero de pedido valido: ");
            pedido = teclado.nextLine();
            System.out.println("\n");
        }

        boolean respuesta = controlador.deletePedido(Integer.parseInt(pedido));

        System.out.println(respuesta ?
                "\n\nEl pedido se ha anulado correctamente.\n" :
                "\nLo sentimos, el pedido ya está enviado y no se puede anular."
        );
        System.out.println("--------------\n");

    }

    private static boolean isValidFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void printPedido(Pedido pedido) {
        System.out.println("\n---- Nº"+ pedido.getNumPedido() + " ----");
        System.out.println("Cliente: " + pedido.getCliente().toString());
        System.out.println("Articulo:" + pedido.getArticulo().toString());
        System.out.println("Cantidad: " + pedido.getCantidadArticulo());
        System.out.println("Precio de articulos: " + pedido.getPrecioPedido());
        System.out.println("Gastos de envio: " + pedido.precioEnvio());
        System.out.println("Precio total: " + (pedido.precioEnvio() + pedido.getPrecioPedido()));
        System.out.println("Fecha y hora: " + pedido.getFechaHora());
    }

    private boolean volverAlMenu(String input) {
        if(Objects.equals(input, "0")) {
            return true;
        }
        return false;
    }
}

