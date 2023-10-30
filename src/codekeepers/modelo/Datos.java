package codekeepers.modelo;

public class Datos {
    private ListaArticulos listaArticulos;
    private ListaClientes listaClientes;
    private ListaPedidos listaPedidos;
    public Datos (){
        this.listaArticulos = new ListaArticulos();
        this.listaClientes = new ListaClientes();
        this.listaPedidos = new ListaPedidos();
    }

    public ListaArticulos getListaArticulos() {
        return listaArticulos;
    }

    public void setListaArticulos(ListaArticulos listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

    public ListaClientes getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ListaPedidos getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(ListaPedidos listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
}
