package codekeepers.controlador;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public void addNewArticulo(String nombre, String descripcion, float precio, float gastoEnvio,int tiempoPreparacion, int stock) {
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
    }

    public void addCliente(String email, String nombre, String nif, String domicilio, boolean esPremium) {
        ListaClientes listaClientes = datos.getListaClientes();
        Cliente clienteNuevo = esPremium ?
                new ClientePremium(email, nombre, nif, domicilio) :
                new ClienteEstandard(email, nombre, nif, domicilio);
        listaClientes.add(email, clienteNuevo);
        datos.setListaClientes(listaClientes);
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

    public Pedido addPedido(String emailCliente, int numeroArticulo, int cantidad) {
        ListaPedidos listaPedidos = datos.getListaPedidos();
        Cliente cliente = datos.getListaClientes().get(emailCliente);
        Articulo articulo = datos.getListaArticulos().get(numeroArticulo);
        Object id = listaPedidos.getLastKey();
        if(id == null) {
            id = 1;
        } else {
            id = Integer.parseInt(id.toString()) + 1 ;
        }
        float precioArticulo = articulo.getPrecio();
        float gastoEnvio = articulo.getGastoEnvio();
        float precioTodosArticulos = precioArticulo * cantidad;
        float precioFinal = precioTodosArticulos + gastoEnvio;
        Pedido pedidoNuevo = new Pedido(
                Integer.parseInt(id.toString()),
                cliente,
                articulo,
                cantidad,
                precioFinal
        );
        listaPedidos.add(id, pedidoNuevo);
        datos.setListaPedidos(listaPedidos);
        return pedidoNuevo;
    }

    public void deletePedido(int numeroPedido) {
        ListaPedidos listaPedidos = datos.getListaPedidos();
        listaPedidos.delete(numeroPedido);
        datos.setListaPedidos(listaPedidos);
    }

    public void initData() {
        addCliente("luffy@mail.com", "Luffy", "222222222P", "C/ Rubi 22", true);
        addCliente("zoro@mail.com", "Zoro", "333333333K", "C/ Diamant 22", false);
        addCliente("robin@mail.com", "Robin", "444444444M", "C/ Or 22", true);
        addNewArticulo("Mesa", "Mesa de mandera", 22.89f, 3.55f, 1, 100);
        addNewArticulo("Silla", "Silla de mandera", 16.89f, 3.35f, 10, 400);
        addNewArticulo("Estanteria", "Estanteria de mandera", 12.89f, 2.75f, 5, 300);
    }
}
