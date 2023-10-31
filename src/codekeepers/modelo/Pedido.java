package codekeepers.modelo;
import java.time.LocalDateTime;
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
            boolean pedido_Enviado = false;
            //Si la fecha del pedido más los minutos de preaparación del articulo es menor al tiempo actual este será un pedido enviado
            pedido_Enviado = this.fechaHora.plusMinutes(this.articulo.getTiempo_preparacion()).isBefore(LocalDateTime.now());
            return pedido_Enviado;
    }

    public float precioEnvio() {
        float costoEnvioTotal = 0.0f;

        costoEnvioTotal = this.articulo.getPrecio()*this.cantidadArticulo;

        // Si el cliente no tiene descuento en envíos, simplemente retornamos el costo total.
        if (cliente instanceof ClienteEstandard) {
            return costoEnvioTotal;
        }

        // Si el cliente es premium, aplicamos el descuento del 20%.
        if (cliente instanceof ClientePremium) {
            float descuento = cliente.descuentoEnv() / 100.0f; // 20% como decimal
            return costoEnvioTotal * (1 - descuento);
        }
        return costoEnvioTotal;
    }

        @Override
        public String toString() {
            return "Pedido{" +
                    "num_pedido=" + numPedido +
                    ", fecha_hora=" + fechaHora +
                    ", cliente=" + cliente.getNif() + cliente.getNombre() +
                    ", articulo=" + articulo.getid() + articulo.getDescripcion() +
                    ", precio_articulo=" + articulo.getPrecio() +
                    ", cantidad_articulo=" + cantidadArticulo +
                    ", precio total del pedido=" + precioPedido +
                    ", Coste de envio=" + precioEnvio() +
                    ", precio total del pedido + envio=" + precioEnvio() + precioPedido +
                    ", Pedido enviado=" + pedidoEnviado() +
                    '}';
        }

}
