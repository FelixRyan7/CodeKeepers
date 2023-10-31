package codekeepers.modelo;
import java.time.LocalDateTime;
import java.util.List;
import codekeepers.vista.*;
import java.time.temporal.ChronoUnit;


public class Pedido {

        private int numPedido;

        private Cliente cliente;

        private Articulo articulo;

        private LocalDateTime fechaHora;

        private int cantidadArticulo;

        private float precioPedido;



    public Pedido(){

    }

    public Pedido(int numPedido, Cliente cliente, Articulo articulo, int cantidadArticulo, float precioPedido, LocalDateTime fechaHora) {
        this.numPedido = numPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidadArticulo = cantidadArticulo;
        this.precioPedido = articulo.getPrecio() * cantidadArticulo;
        this.fechaHora = fechaHora; // Asigna la fecha y hora proporcionada
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
        LocalDateTime fechaHoraPedido = getFechaHora();
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        // Calcula el tiempo transcurrido en minutos desde el pedido.
        long minutosTranscurridos = ChronoUnit.MINUTES.between(fechaHoraPedido, fechaHoraActual);

        if (minutosTranscurridos <= articulo.getTiempo_preparacion()) {
            // El pedido puede ser eliminado, ya que no ha sido enviado.
            // Agregar aquí la lógica para eliminar el pedido.

            return false;
        } else {
            // El pedido no puede ser eliminado, ya que ha superado el tiempo de preparación para el envío.
            // Puedes mostrar un mensaje de error o realizar cualquier otra acción necesaria.
            return true;
        }
    }

    public float precioEnvio() {


        float costoEnvioTotal = this.articulo.getGastoEnvio();

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
                    ", cliente= ( NIF: " + cliente.getNif() + " / Nombre: "+ cliente.getNombre() + ")" +
                    ", articulo= (Id articulo: " + articulo.getid() + " / Descripcion: "+ articulo.getDescripcion() + ")" +
                    ", precio_articulo=" + articulo.getPrecio() + "€" +
                    ", cantidad_articulo=" + cantidadArticulo +
                    ", precio total del pedido=" + articulo.getPrecio() * cantidadArticulo + "€" +
                    ", Coste de envio=" + articulo.getGastoEnvio() + "€" +
                    ", Coste de envio por ser cliente " + cliente.tipoCliente() + ": " + precioEnvio() + "€" +
                    ", precio total del pedido + envio=" + precioPedido + "€" +
                    ", Pedido enviado=" + pedidoEnviado() +
                    '}';
        }

}
