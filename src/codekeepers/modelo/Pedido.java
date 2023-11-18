package codekeepers.modelo;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class Pedido {


    private int numPedido;

    private Cliente cliente;

    private Articulo articulo;

    private LocalDateTime fechaHora;

    private int cantidadArticulo;

    private float precioPedido;


    public Pedido(int numPedido, Cliente cliente, Articulo articulo, int cantidadArticulo, float precioPedido) {
        this.numPedido = numPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidadArticulo = cantidadArticulo;
        this.precioPedido = precioPedido;
        this.fechaHora = LocalDateTime.now();
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getCantidadArticulo() {
        return cantidadArticulo;
    }

    public void setCantidadArticulo(int cantidadArticulo) {
        this.cantidadArticulo = cantidadArticulo;
    }

    public float getPrecioPedido() {
        return precioPedido;
    }

    public void setPrecioPedido(float precioPedido) {
        this.precioPedido = precioPedido;
    }

    public boolean pedidoEnviado(){
        int tiempoPreparacion = articulo.getTiempoPreparacion();
        long diferenciaMinutos = ChronoUnit.MINUTES.between(fechaHora, LocalDateTime.now());

        return diferenciaMinutos >= tiempoPreparacion;
    }

    public double precioEnvio() {
        float costoEnvioTotal = articulo.getGastoEnvio();

        // Si el cliente no tiene descuento en envíos, simplemente retornamos el costo total.
        if (cliente instanceof ClienteEstandard) {
            return costoEnvioTotal;
        }

        // Si el cliente es premium, aplicamos el descuento del 20%.
        if (cliente instanceof ClientePremium) {
            float descuento = (cliente.descuentoEnvio() * costoEnvioTotal) / 100;

            return Math.round((costoEnvioTotal - descuento)* Math.pow(10, 2)) / Math.pow(10, 2);
        }
        return costoEnvioTotal;
    }

        @Override
        public String toString() {
            return "Pedido{" +
                    "num_pedido=" + numPedido +
                    ", fecha_hora=" + fechaHora +
                    ", cliente=" + cliente.getNif() + cliente.getNombre() +
                    ", articulo=" + articulo.getId() + articulo.getDescripcion() +
                    ", precio_articulo=" + articulo.getPrecio() +
                    ", cantidad_articulo=" + cantidadArticulo +
                    ", precio total del pedido=" + precioPedido +
                    ", Coste de envio=" + precioEnvio() +
                    ", precio total del pedido + envio=" + (precioEnvio() + precioPedido) +
                    ", Pedido enviado=" + pedidoEnviado() +
                    '}';
        }

}
