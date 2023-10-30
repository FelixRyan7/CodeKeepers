package codekeepers.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
}
