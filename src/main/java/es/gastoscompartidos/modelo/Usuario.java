package es.gastoscompartidos.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private List<String> grupos;
    private String metodoPago;
    private String historial;
    private List<String> deudas;

    public Usuario() {
        this.grupos = new ArrayList<>();
        this.deudas = new ArrayList<>();
    }

    public Usuario(int id, String nombre, String email,
                   List<String> grupos, String metodoPago,
                   String historial, List<String> deudas) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.grupos = grupos;
        this.metodoPago = metodoPago;
        this.historial = historial;
        this.deudas = deudas;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getGrupos() { return grupos; }
    public void setGrupos(List<String> grupos) { this.grupos = grupos; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public String getHistorial() { return historial; }
    public void setHistorial(String historial) { this.historial = historial; }

    public List<String> getDeudas() { return deudas; }
    public void setDeudas(List<String> deudas) { this.deudas = deudas; }

    @Override
    public String toString() {
        return nombre + " (" + email + ")";
    }
}
