package codekeepers.modelo;

public abstract class Cliente {

    private String id;
    private String email;

    private String nombre;

    private String nif;

    private String domicilio;


    public Cliente(String id, String nombre, String nif, String domicilio, String email) {
        this.id = id;
        this.nombre = nombre;
        this.nif = nif;
        this.domicilio = domicilio;
        this.email = email;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public abstract float cuotaAnual();

    public abstract float descuentoEnvio();

    @Override
    public String toString() {
        return "Cliente {" +
                "Email='" + email + '\'' +
                ", Nombre='" + nombre + '\'' +
                ", Nif='" + nif + '\'' +
                ", Domicilio='" + domicilio + '\'' +

                '}';
    }


}
