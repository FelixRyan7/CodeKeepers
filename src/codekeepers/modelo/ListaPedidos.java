package codekeepers.modelo;

import java.util.ArrayList;

public class ListaPedidos extends Lista<Pedido> {
    public ListaPedidos(){
        this.nombre = "Lista_Pedidos";
        this.lista = new ArrayList<Pedido>();
    }
    public ArrayList<Pedido> getPedido(Cliente cliente){
        ArrayList<Pedido> ar_list = new ArrayList<Pedido>();
        for(Pedido pedido: this.lista){
            if(pedido.getCliente() == cliente) ar_list.add(pedido);
        }
        return ar_list;
    }
    public ArrayList<Pedido> getPedido(Cliente cliente, boolean sent){
        ArrayList<Pedido> ar_list = new ArrayList<Pedido>();
        for(Pedido pedido: this.lista){
            if(pedido.getCliente() == cliente){
                if(pedido.pedidoEnviado()==sent){
                    ar_list.add(pedido);

                }
            }
        }
        return ar_list;
    }
}
