package codekeepers.modelo;

public class ClientePremium extends Cliente{

    public ClientePremium(String email, String nombre, String nif, String domicilio) {
        super(email, nombre, nif, domicilio);
    }


    @Override
    public String tipoCliente() {
        return "Premium";
    }

    @Override
    public float calcAnual() {
        return 30.0f;
    }

    @Override
    public float descuentoEnv() {
        return 20.0f;
    }

    @Override
    public String toString() {
        return "Tipo de Cliente: " + tipoCliente() + "\n" +
                super.toString() +
                "Cuota anual: " + calcAnual() + "\n" +
                "Descuento de envio: " + descuentoEnv() + "\n"
                ;
    }
}
