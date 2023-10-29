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
        System.out.println("LISTA DE ARTICULOS");
    }

    public void addArticulo() {
        System.out.println("AÑADIR ARTICULO");
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
        System.out.println("LISTA DE CLIENTES");
    }

    public void listarClientesEstandar() {
        System.out.println("LISTA DE CLIENTES ESTANDAR");
    }

    public void listarClientesPremium() {
        System.out.println("LISTA DE CLIENTES PREMIUM");
    }

    public void addCliente() {
        System.out.println("AÑADIR CLIENTE");
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

}
