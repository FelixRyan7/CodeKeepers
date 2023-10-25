package codekeepers.modelo;

public abstract class ClienteEstandard extends Cliente{

    public ClienteEstandard(String email, String nombre, String nif, String domicilio) {
        super(email, nombre, nif, domicilio);
    }

    @Override
    public String tipoCliente() {
        return "Estandard";
    }

    @Override
    public float calcAnual() {
        // Implementación específica para calcular la cuota anual de ClienteEstandard
        return 0.0f;
    }

    @Override
    public float descuentoEnv() {
        // Implementación específica para calcular el descuento de gastos de envío para ClienteEstandard
        return 0.0f;
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
