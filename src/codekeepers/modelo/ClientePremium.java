package codekeepers.modelo;

public abstract class ClientePremium extends Cliente{

    private float cuota;
    private float descuento;
    private boolean premium;

    public ClientePremium(String email, String nombre, String nif, String domicilio) {
        super(email, nombre, nif, domicilio);
        this.descuento = 30.0f;
        this.cuota = 20.0f;
        this.premium = true;
    }


    @Override
    public String tipoCliente() {
        return this.premium?"Premium":"Estandard";
    }

    @Override
    public float calcAnual() {
        return this.cuota;
    }

    @Override
    public float descuentoEnv() {
        return this.descuento;
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
