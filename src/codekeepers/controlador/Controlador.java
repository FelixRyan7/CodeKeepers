package codekeepers.controlador;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import codekeepers.modelo.*;

public class Controlador {
    private Datos datos;
    public Controlador() {
        datos = new Datos ();
        this.initData();
    }

    public List<Articulo> showAllArticulos() {
        ListaArticulos listaArticulos = this.datos.getListaArticulos();
        return listaArticulos.getList();
    }

    public Articulo addNewArticulo(String nombre, String descripcion, float precio, float gastoEnvio,int tiempoPreparacion, int stock) {
        ListaArticulos listaArticulos = datos.getListaArticulos();
        Object id = listaArticulos.getLastKey();
        if(id == null) {
            id = 1;
        } else {
            id = Integer.parseInt(id.toString()) + 1 ;
        }
        Articulo articuloNuevo = new Articulo(
                String.valueOf(id),
                nombre,
                descripcion,
                precio,
                gastoEnvio,
                tiempoPreparacion,
                stock
                );
        listaArticulos.add(id, articuloNuevo);
        datos.setListaArticulos(listaArticulos);
        return articuloNuevo;
    }

    public Cliente addCliente(String email, String nombre, String nif, String domicilio, boolean esPremium) {
        ListaClientes listaClientes = datos.getListaClientes();
        Cliente clienteNuevo = esPremium ?
                new ClientePremium(email, nombre, nif, domicilio) :
                new ClienteEstandard(email, nombre, nif, domicilio);
        listaClientes.add(email, clienteNuevo);
        datos.setListaClientes(listaClientes);
        return clienteNuevo;
    }

    public List<Cliente> showAllClientes() {
        ListaClientes listaClientes = this.datos.getListaClientes();
        return listaClientes.getList();
    }

    public List<ClienteEstandard> showClientesEstandar() {
        ListaClientes listaClientes = this.datos.getListaClientes();
        List<ClienteEstandard> clientesEstandar = new ArrayList<>();

        for (Cliente cliente : listaClientes.getList()) {
            if (cliente instanceof ClienteEstandard) {
                clientesEstandar.add((ClienteEstandard) cliente);
            }
        }
        return clientesEstandar;
    }

    public List<ClientePremium> showClientesPremium() {
        ListaClientes listaClientes = this.datos.getListaClientes();
        List<ClientePremium> clientesPremium = new ArrayList<>();

        for (Cliente cliente : listaClientes.getList()) {
            if (cliente instanceof ClientePremium) {
                clientesPremium.add((ClientePremium) cliente);
            }
        }
        return clientesPremium;
    }

    public List<Pedido> showPedidosPendientes() {
        ListaPedidos listaPedidos = this.datos.getListaPedidos();
        List<Pedido> pedidosPendientes = new ArrayList<>();
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        for (Pedido pedido : listaPedidos.getList()) {
            LocalDateTime fechaHoraPedido = pedido.getFechaHora();
            int tiempoPreparacion = pedido.getArticulo().getTiempoPreparacion();
            long diferenciaMinutos = ChronoUnit.MINUTES.between(fechaHoraPedido, fechaHoraActual);
            if(diferenciaMinutos < tiempoPreparacion) {
                pedidosPendientes.add(pedido);
            }
        }
        return pedidosPendientes;
    }

    public List<Pedido> showPedidosPendientesCliente(String cliente) {
        ListaPedidos listaPedidos = this.datos.getListaPedidos();
        List<Pedido> pedidosPendientes = new ArrayList<>();
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        for (Pedido pedido : listaPedidos.getList()) {
            LocalDateTime fechaHoraPedido = pedido.getFechaHora();
            int tiempoPreparacion = pedido.getArticulo().getTiempoPreparacion();
            long diferenciaMinutos = ChronoUnit.MINUTES.between(fechaHoraPedido, fechaHoraActual);
            if(diferenciaMinutos < tiempoPreparacion && Objects.equals(pedido.getCliente().getEmail(), cliente)) {
                pedidosPendientes.add(pedido);
            }
        }
        return pedidosPendientes;
    }

    public List<Pedido> showPedidosEnviados() {
        ListaPedidos listaPedidos = this.datos.getListaPedidos();
        List<Pedido> pedidosEnviados = new ArrayList<>();
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        for (Pedido pedido : listaPedidos.getList()) {
            LocalDateTime fechaHoraPedido = pedido.getFechaHora();
            int tiempoPreparacion = pedido.getArticulo().getTiempoPreparacion();
            long diferenciaMinutos = ChronoUnit.MINUTES.between(fechaHoraPedido, fechaHoraActual);

            if(diferenciaMinutos >= tiempoPreparacion) {
                pedidosEnviados.add(pedido);
            }

        }
        return pedidosEnviados;
    }

    public List<Pedido> showPedidosEnviadosCliente(String cliente) {
        ListaPedidos listaPedidos = this.datos.getListaPedidos();
        List<Pedido> pedidosEnviados = new ArrayList<>();
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        for (Pedido pedido : listaPedidos.getList()) {
            LocalDateTime fechaHoraPedido = pedido.getFechaHora();
            int tiempoPreparacion = pedido.getArticulo().getTiempoPreparacion();
            long diferenciaMinutos = ChronoUnit.MINUTES.between(fechaHoraPedido, fechaHoraActual);

            if(diferenciaMinutos >= tiempoPreparacion && Objects.equals(pedido.getCliente().getEmail(), cliente)) {
                pedidosEnviados.add(pedido);
            }

        }
        return pedidosEnviados;
    }

    public Pedido addPedido(String emailCliente, int numeroArticulo, int cantidad) {
        ListaPedidos listaPedidos = datos.getListaPedidos();
        ListaArticulos listaArticulos = datos.getListaArticulos();
        Cliente cliente = datos.getListaClientes().get(emailCliente);
        Articulo articulo = datos.getListaArticulos().get(numeroArticulo);
        Object id = listaPedidos.getLastKey();
        if(id == null) {
            id = 1;
        } else {
            id = Integer.parseInt(id.toString()) + 1 ;
        }
        float precioTodosArticulos = articulo.getPrecio() * cantidad;
        Pedido pedidoNuevo = new Pedido(
                Integer.parseInt(id.toString()),
                cliente,
                articulo,
                cantidad,
                precioTodosArticulos
        );
        listaPedidos.add(id, pedidoNuevo);
        datos.setListaPedidos(listaPedidos);
        listaArticulos.update(numeroArticulo, articulo.setStock(articulo.getStock() - cantidad));
        datos.setListaArticulos(listaArticulos);
        return pedidoNuevo;
    }

    public boolean deletePedido(int numeroPedido) {
        ListaPedidos listaPedidos = datos.getListaPedidos();
        Pedido pedido = listaPedidos.get(numeroPedido);
        if(pedido.pedidoEnviado()) {
            return false;
        }
        listaPedidos.delete(numeroPedido);
        datos.setListaPedidos(listaPedidos);
        return true;
    }

    public void initData() {
        Cliente cliente1 = addCliente("luffy@mail.com", "Luffy", "222222222P", "C/ Rubi 22", true);
        Cliente cliente2 = addCliente("zoro@mail.com", "Zoro", "333333333K", "C/ Diamant 22", false);
        Cliente cliente3 = addCliente("robin@mail.com", "Robin", "444444444M", "C/ Or 22", true);
        Articulo articulo1 = addNewArticulo("Mesa", "Mesa de mandera", 22.89f, 3.55f, 1, 100);
        Articulo articulo2 = addNewArticulo("Silla", "Silla de mandera", 16.89f, 3.35f, 10, 400);
        Articulo articulo3 = addNewArticulo("Estanteria", "Estanteria de mandera", 12.89f, 2.75f, 5, 300);
        addPedido("luffy@mail.com", 1, 10);
        addPedido("luffy@mail.com", 2, 40);
        addPedido("luffy@mail.com", 3, 4);
        addPedido("robin@mail.com", 1, 40);
        addPedido("robin@mail.com", 2, 12);
        addPedido("zoro@mail.com", 3, 10);
    }
}
