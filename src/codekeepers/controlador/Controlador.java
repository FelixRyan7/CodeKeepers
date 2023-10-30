package codekeepers.controlador;

import java.util.HashMap;
import java.util.List;

import codekeepers.modelo.Articulo;
import codekeepers.modelo.Datos;
import codekeepers.modelo.ListaArticulos;
import codekeepers.modelo.ListaClientes;

public class Controlador {
    private Datos datos;
    public Controlador() {
        datos = new Datos ();
    }

    public List<Articulo> showAllArticulos() {
        ListaArticulos listaArticulos = this.datos.getListaArticulos();
        return listaArticulos.getList();
    }

    public void addNewArticulo(String nombre, String descripcion, float precio, float gastoEnvio,int tiempoPreparacion) {
        ListaArticulos listaArticulos = datos.getListaArticulos();
        int id = listaArticulos.getNextKey();
        Articulo articuloNuevo = new Articulo(
                String.valueOf(id),
                nombre,
                descripcion,
                precio,
                gastoEnvio,
                tiempoPreparacion
                );
        listaArticulos.add(articuloNuevo);
        datos.setListaArticulos(listaArticulos);
    }

    public ListaClientes showAllClientes() {
        return this.datos.getListaClientes();
    }

    public ListaClientes showClientesEstandar() {
        // ListaClientes listaClientes = this.datos.getListaClientes();
        // ListaClientes listaClientesEstandar = new ListaClientes();
        return this.datos.getListaClientes();
    }

    public ListaClientes showClientesPremium() {
        return this.datos.getListaClientes();
    }
}
