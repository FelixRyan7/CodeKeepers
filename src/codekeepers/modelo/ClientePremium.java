package codekeepers.modelo;

public class ClientePremium extends Cliente{

    public ClientePremium(String id, String nombre, String nif, String domicilio, String email) {
        super(id, nombre, nif, domicilio, email);
    }


    @Override
    public String tipoCliente() {
        return "Premium";
    }

    @Override
    public float cuotaAnual() {
        return 30.0f;
    }

    @Override
    public float descuentoEnvio() {
        return 20.0f;
    }

    @Override
    public String toString() {
        return "Tipo de Cliente: " + tipoCliente() + "\n" +
                super.toString() +
                "\nCuota anual: " + cuotaAnual() + "€\n" +
                "Descuento de envio: " + descuentoEnvio() + "%\n"
                ;
    }
}
