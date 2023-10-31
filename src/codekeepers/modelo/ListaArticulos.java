package codekeepers.modelo;

import java.util.ArrayList;

public class ListaArticulos extends Lista<Articulo>{
    public ListaArticulos(){
        this.nombre = "Lista_Articulos";
        this.lista = new ArrayList<Articulo>();
    }

}
