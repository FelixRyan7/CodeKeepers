package codekeepers.modelo;

import java.util.ArrayList;

public class ListaClientes extends Lista<Cliente> {
    public ListaClientes(){
        this.nombre = "Lista_Clientes";
        this.lista = new ArrayList<Cliente>();
    }
}
