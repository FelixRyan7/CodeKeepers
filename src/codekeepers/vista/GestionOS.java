package codekeepers.vista;
import codekeepers.controlador.Controlador;
import codekeepers.modelo.*; // Importar todas las clases del paquete "modelo"
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;









public class GestionOS {
        private Controlador controlador;

        //Cada una de estas variables es una instancia de la clase genérica Lista
        private Lista<Articulo> listaArticulos; // Usar una clase genérica
        private Lista<Cliente> listaClientes; // Usar una clase genérica
        private Map<String, Cliente> mapaClientes = new HashMap<>(); //
        private Lista<Pedido> listaPedidos; // Usar una clase genérica

        Scanner teclado = new Scanner(System.in);
        public GestionOS() {
            controlador = new Controlador();
            listaArticulos = new Lista<>(); // Inicializa una lista genérica para Articulos
            listaClientes = new Lista<>(); // Inicializa una lista genérica para clientes
            listaPedidos = new Lista<>();  // Inicializa una lista genérica para pedidos
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
                        gestionArticulos();
                        break;
                    case '2':
                        gestionClientes();
                        break;
                    case '3':
                        gestionPedidos();
                        break;

                    case '0':
                        salir = true;
                }
            } while (!salir);
        }


        char pedirOpcion() {
            String resp;
            System.out.println("Elige una opción (1,2,3 o 0): ");
                    resp = teclado.nextLine();
            if (resp.isEmpty()) {
                resp = " ";
            }
            return resp.charAt(0);
        }



        void gestionArticulos() {
            boolean salir = false;
            char opcio;
            do {
                System.out.println("Gestión de Artículos:");
                System.out.println("1. Añadir Artículo");
                System.out.println("2. Mostrar Artículos");
                System.out.println("0. Volver al menú principal");
                opcio = pedirOpcion();
                switch (opcio) {
                    case '1':
                        agregarArticulo();
                        break;
                    case '2':
                        mostrarListaArticulos();
                        break;
                    case '0':
                        salir = true;
                }
            } while (!salir);
        }

        void gestionClientes() {
            boolean salir = false;
            char opcio;
            do {
                System.out.println("Gestión de Clientes:");
                System.out.println("1. Añadir Cliente");
                System.out.println("2. Mostrar Clientes");
                System.out.println("3. Mostrar Clientes Estándar");
                System.out.println("4. Mostrar Clientes Premium");
                System.out.println("0. Volver al menú principal");
                opcio = pedirOpcion();
                switch (opcio) {
                    case '1':
                        agregarCliente();
                        break;
                    case '2':
                        mostrarClientes();
                        break;
                    case '3':
                        mostrarClientesEstandar();
                        break;
                    case '4':
                        mostrarClientesPremium();
                        break;
                    case '0':
                        salir = true;
                }
            } while (!salir);
        }

        void gestionPedidos() {
            boolean salir = false;
            char opcio;
            do {
                System.out.println("Gestión de Pedidos:");
                System.out.println("1. Añadir Pedido");
                System.out.println("2. Eliminar Pedido");
                System.out.println("3. Mostrar Pedidos Pendientes de Envío");
                System.out.println("4. Mostrar Pedidos Enviados");
                System.out.println("0. Volver al menú principal");
                opcio = pedirOpcion();
                switch (opcio) {
                    case '1':
                        agregarPedido();
                        break;
                    case '2':
                        eliminarPedido();
                        break;
                    case '3':
                        mostrarListaPedidos();
                        break;
                    case '4':
                        // TO-BE-DONE: Lógica para mostrar pedidos enviados con opción de filtrar por cliente
                        break;
                    case '0':
                        salir = true;
                }
            } while (!salir);
        }

        //FUNCIONES PARA LA GESTION DE ARTICULOS
        void agregarArticulo() {
            System.out.println("Añadir Artículo:");

            System.out.print("ID: ");
            String id = teclado.nextLine();

            System.out.print("Nombre: ");
            String nombre = teclado.nextLine();

            System.out.print("Descripción: ");
            String descripcion = teclado.nextLine();

            System.out.print("Precio: ");
            float precio = Float.parseFloat(teclado.nextLine());

            System.out.print("Gasto de Envío: ");
            float gastoEnvio = Float.parseFloat(teclado.nextLine());

            System.out.print("Tiempo de Preparación: ");
            int tiempoPreparacion = Integer.parseInt(teclado.nextLine());

            Articulo nuevoArticulo = new Articulo(id, nombre, descripcion, precio, gastoEnvio, tiempoPreparacion);
            listaArticulos.add(nuevoArticulo);
            System.out.println("------------------------------");
            System.out.println("Artículo añadido correctamente.");
            System.out.println("------------------------------");
        }

        void mostrarListaArticulos() {
            System.out.println("Lista de Artículos:");

            for (Articulo articulo : listaArticulos.getArrayList()) {
                System.out.println(articulo); // Usa el método toString() de la clase Articulo
                System.out.println("--------------------------------");
            }
        }


        //FUNCIONES PARA LA GESTION DE CLIENTES

        // Función para añadir un cliente
        void agregarCliente() {
            System.out.println("Añadir Cliente:");

            System.out.print("Nombre: ");
            String nombre = teclado.nextLine();

            System.out.print("NIF: ");
            String nif = teclado.nextLine();

            System.out.print("Domicilio: ");
            String domicilio = teclado.nextLine();

            System.out.print("Email: ");
            String email = teclado.nextLine();

            // Verificar si el cliente ya existe en el mapa
            if (mapaClientes.containsKey(email)) {
                System.out.println("El cliente con este email ya existe. No se puede añadir.");
            } else {
                System.out.print("Tipo de Cliente (Estandar o Premium): ");
                String tipoCliente = teclado.nextLine();

                Cliente nuevoCliente;
                if (tipoCliente.equalsIgnoreCase("Estandar")) {
                    nuevoCliente = new ClienteEstandard(email, nombre, nif, domicilio);
                } else if (tipoCliente.equalsIgnoreCase("Premium")) {
                    nuevoCliente = new ClientePremium(email, nombre, nif, domicilio);
                } else {
                    System.out.println("Tipo de cliente no válido. Cliente no añadido.");
                    return;
                }

                // Añadir el cliente al mapa utilizando el email como clave
                mapaClientes.put(email, nuevoCliente);
                System.out.println("Cliente añadido correctamente.");
            }
        }

        // Función para mostrar todos los clientes
        void mostrarClientes() {
            System.out.println("Lista de Clientes:");

            for (Cliente cliente : mapaClientes.values()) {
                System.out.println(cliente);
                System.out.println("--------------------------------");
            }
        }

        // Función para mostrar solo los clientes Estándar
        void mostrarClientesEstandar() {
            System.out.println("Lista de Clientes Estándar:");

            for (Cliente cliente : mapaClientes.values()) {
                if (cliente instanceof ClienteEstandard) {
                    System.out.println(cliente);
                    System.out.println("--------------------------------");
                }
            }
        }

        // Función para mostrar solo los clientes Premium
        void mostrarClientesPremium() {
            System.out.println("Lista de Clientes Premium:");

            for (Cliente cliente : mapaClientes.values()) {
                if (cliente instanceof ClientePremium) {
                    System.out.println(cliente);
                    System.out.println("--------------------------------");
                }
            }
        }

        //FUNCIONES PARA LA GESTION DE PEDIDOS

        // Función para añadir un pedido
        int numPedido = 1;
        void agregarPedido() {
           Pedido nuevoPedido = new Pedido();
            System.out.println("Añadir Pedido:");

            // Solicitar número del pedido



            nuevoPedido.setNumPedido(numPedido);


            System.out.print("Email del Cliente: ");
            String emailCliente = teclado.nextLine();

            Cliente cliente = mapaClientes.get(emailCliente);

            if (cliente != null) {
                nuevoPedido.setCliente(cliente);
                // Solicitar información del artículo
                System.out.print("ID del Artículo: ");
                String idArticulo = teclado.nextLine();

                Articulo articulo = null;
                for (int i = 0; i < listaArticulos.getSize(); i++) {
                    Articulo a = listaArticulos.getAt(i);
                    if (a.getid().equals(idArticulo)) {
                        articulo = a;
                        nuevoPedido.setArticulo(articulo);
                        break;
                    }
                }

                if (articulo == null) {
                    System.out.println("El artículo no existe. Pedido no agregado.");
                    return;
                }

                // Solicitar la cantidad del artículo
                System.out.print("Cantidad del Artículo: ");
                int cantidadArticulo = Integer.parseInt(teclado.nextLine());
                nuevoPedido.setCantidadArticulo(cantidadArticulo);

                // Solicitar el precio del pedido
                float costoEnvio = nuevoPedido.precioEnvio();

                float precioPedido = (articulo.getPrecio() * cantidadArticulo) + costoEnvio;

                nuevoPedido.setPrecioPedido(precioPedido);


                // Obtener la fecha y hora actual
                LocalDateTime fechaHoraPedido = LocalDateTime.now();
                nuevoPedido.setFechaHora(fechaHoraPedido);

                // Crear una instancia de Pedido y agregarlo a la lista de pedidos

                listaPedidos.add(nuevoPedido);
                numPedido++;
                System.out.println("Pedido agregado correctamente.");

            } else {
                // El cliente no existe, puedes mostrar un mensaje de error o permitir al usuario
                // ingresar más detalles para crear un nuevo cliente si es necesario.
                System.out.println("Cliente no encontrado. ¿Desea registrar un nuevo cliente? (s/n)");
                String respuesta = teclado.nextLine();

                if (respuesta.equalsIgnoreCase("s")) {
                    System.out.print("Nombre: ");
                    String nombre = teclado.nextLine();
                    System.out.print("NIF: ");
                    String nif = teclado.nextLine();
                    System.out.print("Domicilio: ");
                    String domicilio = teclado.nextLine();

                    // Crear un nuevo cliente
                    System.out.print("Tipo de Cliente (Estandar o Premium): ");
                    String tipoCliente = teclado.nextLine();

                    if (tipoCliente.equalsIgnoreCase("Estandar")) {
                        cliente = new ClienteEstandard(emailCliente, nombre, nif, domicilio);
                    } else if (tipoCliente.equalsIgnoreCase("Premium")) {
                        cliente = new ClientePremium(emailCliente, nombre, nif, domicilio);
                    } else {
                        System.out.println("Tipo de cliente no válido. Pedido no agregado.");
                        return;
                    }

                    // Agregar el cliente al mapa utilizando el email como clave
                    mapaClientes.put(emailCliente, cliente);
                    System.out.println("Cliente añadido correctamente.");
                    nuevoPedido.setCliente(cliente);
                    // Solicitar información del artículo
                    System.out.print("ID del Artículo: ");
                    String idArticulo = teclado.nextLine();
                    nuevoPedido.setNumPedido(numPedido);

                    Articulo articulo = null;
                    for (int i = 0; i < listaArticulos.getSize(); i++) {
                        Articulo a = listaArticulos.getAt(i);
                        if (a.getid().equals(idArticulo)) {
                            articulo = a;
                            nuevoPedido.setArticulo(articulo);
                            break;
                        }
                    }

                    if (articulo == null) {
                        System.out.println("El artículo no existe. Pedido no agregado.");
                        return;
                    }

                    // Solicitar la cantidad del artículo
                    System.out.print("Cantidad del Artículo: ");
                    int cantidadArticulo = Integer.parseInt(teclado.nextLine());
                    nuevoPedido.setCantidadArticulo(cantidadArticulo);

                    // Solicitar el precio del pedido
                    float costoEnvio = nuevoPedido.precioEnvio();

                    float precioPedido = (articulo.getPrecio() * cantidadArticulo) + costoEnvio;

                    nuevoPedido.setPrecioPedido(precioPedido);


                    // Obtener la fecha y hora actual
                    LocalDateTime fechaHoraPedido = LocalDateTime.now();
                    nuevoPedido.setFechaHora(fechaHoraPedido);


                    // Crear una instancia de Pedido y agregarlo a la lista de pedidos

                    listaPedidos.add(nuevoPedido);
                    numPedido++;
                    System.out.println("Pedido agregado correctamente.");

                } else {
                    return;
                }
            }


        }
    void mostrarListaPedidos() {
        System.out.println("Lista de Pedidos:");

        for (Pedido pedido : listaPedidos.getArrayList()) {
            System.out.println(pedido); // Usa el método toString() de la clase Pedido
            System.out.println("--------------------------------");
        }
    }

    public void eliminarPedido() {

        System.out.print("Ingrese el número de pedido que desea eliminar: ");
        int numeroPedido = teclado.nextInt();

        Pedido pedidoAEliminar = null;

        // Busca el pedido con el número de pedido ingresado.
        for (Pedido pedido : listaPedidos.getArrayList()) {
            if (pedido.getNumPedido() == numeroPedido) {
                pedidoAEliminar = pedido;
                break;
            }
        }

        if (pedidoAEliminar != null) {
            if (!pedidoAEliminar.pedidoEnviado()) {
                // El pedido no ha sido enviado y puede ser eliminado.
                listaPedidos.borrar(pedidoAEliminar);
                System.out.println("---------------------------");
                System.out.println("Pedido eliminado con éxito.");
                System.out.println("---------------------------");

            } else {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("El pedido ha sido enviado y no puede ser eliminado.");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        } else {
            System.out.println("No se encontró un pedido con el número ingresado.");
        }
    }




    }


