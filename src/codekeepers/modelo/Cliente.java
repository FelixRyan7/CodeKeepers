package codekeepers.modelo;

public abstract class Cliente {

    private String email;

    private String nombre;

    private String nif;

    private String domicilio;


    public Cliente(String email, String nombre, String nif, String domicilio) {
        this.email = email;
        this.nombre = nombre;
        this.nif = nif;
        this.domicilio = domicilio;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public abstract String tipoCliente();

    public abstract float calcAnual();

    public abstract float descuentoEnv();

    @Override
    public String toString() {
        return "Cliente {" +
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nif='" + nif + '\'' +
                ", domicilio='" + domicilio + '\'' +

                '}';
    }
}
