package codekeepers.vista;
import codekeepers.controlador.Controlador;
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
            char opcion_aux;
            do {
                System.out.println("1. Gestión Articulos");
                System.out.println("2. Gestión Clientes");
                System.out.println("3. Gestión Pedidos");
                System.out.println("0. Salir");
                opcio = pedirOpcion();
                switch (opcio) {
                    case '1':
                        mostrarOpcion1();
                        opcion_aux = pedirOpcion1();
                        procesar_opcion1(opcion_aux);
                        break;
                    case '2':
                        mostrarOpcion2();
                        opcion_aux = pedirOpcion2_3();
                        procesar_opcion2(opcion_aux);
                        break;
                    case '3':
                        mostrarOpcion3();
                        opcion_aux = pedirOpcion2_3();
                        procesar_opcion3(opcion_aux);
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
        void mostrarOpcion1(){
            System.out.println("--------------------------------------------------");
            System.out.println("1. Añadir Artículo");
            System.out.println("2. Mostrar Artículos");
            System.out.println("0. Volver al menú principal");
        }
        char pedirOpcion1(){
            String resp;
            System.out.println("Elige una opción (1,2 o 0): ");
            resp = teclado.nextLine();
            if (resp.isEmpty()) {
                resp = " ";
            }
            return resp.charAt(0);
        }
        void procesar_opcion1(char opcion){
            System.out.println("--------------------------------------------------");
            switch (opcion){
                case '1':
                    String nombre;
                    String descripcion;
                    int precio;
                    float gasto_envio;
                    int tiempo_preparacion;
                    boolean control;
                    System.out.println("Introduce el nombre del articulo:");
                    nombre = teclado.nextLine();
                    System.out.println("Introduce la descripción del articulo:");
                    descripcion = teclado.nextLine();
                    System.out.println("Introduce el precio del articulo:");
                    precio = teclado.nextInt();
                    System.out.println("Introduce los gastos de envio del articulo:");
                    gasto_envio = teclado.nextFloat();
                    System.out.println("Introduce el tiempo de preparación del articulo en minutos:");
                    tiempo_preparacion = teclado.nextInt();
                    control = addArticulo(nombre,descripcion,precio,gasto_envio,tiempo_preparacion);
                    if (control) {
                        System.out.println("Se ha añadido el articulo correctamente.");
                    } else {
                        System.out.println("No se ha podido añadir el articulo.");
                    }
                    break;
                case '2':
                    mostrarArticulos();
                    break;
                case'0':
                    System.out.println("Volviendo al menú principal");
                    break;
                default:
                    break;
            }
        }
        boolean addArticulo(String nombre, String descripcion, int precio, float gasto_envio, int tiempo_preparacion){
            //TODO
            return true;
        }
        void mostrarArticulos(){
            //TODO
        }
        void mostrarOpcion2(){
            System.out.println("--------------------------------------------------");
            System.out.println("1. Añadir Cliente");
            System.out.println("2. Mostrar Clientes");
            System.out.println("3. Mostrar Clientes Estándar");
            System.out.println("4. Mostrar Clientes Premium");
            System.out.println("0. Volver al menú principal");
        }
        char pedirOpcion2_3(){
            String resp;
            System.out.println("Elige una opción (1,2,3,4 o 0): ");
            resp = teclado.nextLine();
            if (resp.isEmpty()) {
                resp = " ";
            }
            return resp.charAt(0);
        }
        void procesar_opcion2(char opcion){
            System.out.println("--------------------------------------------------");
            switch (opcion){
                case '1':
                    boolean control;
                    do{
                        control = add_cliente();

                    }while(control);
                    break;
                case '2':
                    mostrarClientes();
                    break;
                case '3':
                    mostrarClientes(true);
                    break;
                case '4':
                    mostrarClientes(false);
                    break;
                case'0':
                    System.out.println("Volviendo al menú principal");
                    break;
                default:
                    break;
            }
        }
        boolean add_cliente(){
            String nombre;
            String nif;
            String domicilio;
            String email;
            boolean control;
            String resp;
            System.out.println("Introduce el nombre del cliente:");
            nombre = teclado.nextLine();
            System.out.println("Introduce el nif del cliente:");
            nif = teclado.nextLine();
            System.out.println("Introduce el domicilio del cliente:");
            domicilio = teclado.nextLine();
            System.out.println("Introduce el email del cliente:");
            email = teclado.nextLine();
            control = addCliente(nombre,nif,domicilio,email);
            if (control) {
                System.out.println("Se ha añadido el cliente correctamente.");
            } else {
                System.out.println("No se ha podido añadir el cliente.");
            }
            System.out.println("¿Quieres añadir otro cliente?(Y/N):");
            resp =teclado.nextLine();
            return resp.toLowerCase().contains("y");
        }
        void mostrarClientes(){
            //TODO
        }
        void mostrarClientes(boolean isEstandar){
            //TODO
        }

        boolean addCliente(String nombre, String nif, String domicilio, String email){
            //TODO
            return true;
        }
        void mostrarOpcion3(){
            System.out.println("--------------------------------------------------");
            System.out.println("1. Añadir Pedido");
            System.out.println("2. Eliminar Pedido");
            System.out.println("3. Mostrar Pedidos pendientes de envío");
            System.out.println("4. Mostrar Pedidos enviados");
            System.out.println("0. Volver al menú principal");
        }
        void procesar_opcion3(char opcion){
            System.out.println("--------------------------------------------------");
            switch (opcion){
                case '1':
                    add_pedido();
                    break;
                case '2':
                    eliminarPedido();
                    break;
                case '3':
                    mostrarPedidoPendiente();
                    break;
                case '4':
                    mostrarPedidoEnviado();
                    break;
                case'0':
                    System.out.println("Volviendo al menú principal");
                    break;
                default:
                    break;
            }
        }
        void add_pedido(){
            //TODO
        }
        void mostrarPedidoPendiente(){
            //TODO
        }
        void mostrarPedidoEnviado(){
            //TODO
        }
        void eliminarPedido(){
            //TODO
        }
    }
