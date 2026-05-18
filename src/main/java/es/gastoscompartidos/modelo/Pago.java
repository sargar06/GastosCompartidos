package es.gastoscompartidos.modelo;

import java.util.List;

public class Pago {

    private int id;
    private double cantidad;
    private String descripcion;
    private Usuario pagador;
    private List<Usuario> deudores;

    public Pago() {}

    public Pago(int id, double cantidad, String descripcion,
                Usuario pagador, List<Usuario> deudores) {
        this.id = id;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.pagador = pagador;
        this.deudores = deudores;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getCantidad() { return cantidad; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Usuario getPagador() { return pagador; }
    public void setPagador(Usuario pagador) { this.pagador = pagador; }

    public List<Usuario> getDeudores() { return deudores; }
    public void setDeudores(List<Usuario> deudores) { this.deudores = deudores; }

    @Override
    public String toString() {
        return pagador.getNombre() + " pagó " + cantidad + "€ - " + descripcion;
    }
}
