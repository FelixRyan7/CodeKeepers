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

    public Cliente addCliente(String id, String email, String nombre, String nif, String domicilio, boolean esPremium) {
        ListaClientes listaClientes = datos.getListaClientes();
        Cliente clienteNuevo = esPremium ?
                new ClientePremium(id, nombre, nif, domicilio,email) :
                new ClienteEstandard(id, nombre, nif, domicilio, email);
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



}
