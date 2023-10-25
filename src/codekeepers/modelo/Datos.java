package codekeepers.modelo;

public class Datos {
    private ListaArticulos listaArticulos;
    private ListaClientes listaClientes;
    private ListaPedidos listaPedidos;
    public Datos (){
        listaArticulos = new ListaArticulos ();
        listaClientes = new ListaClientes();
        listaPedidos = new ListaPedidos ();
    }

}
