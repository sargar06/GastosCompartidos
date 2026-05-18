package es.gastoscompartidos.modelo;

import java.util.ArrayList;
import java.util.List;

public class Grupo {

    private int id;
    private String nombre;
    private String descripcion;
    private List<Usuario> usuarios;

    public Grupo() {
        this.usuarios = new ArrayList<>();
    }

    public Grupo(int id, String nombre, String descripcion, List<Usuario> usuarios) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarios = usuarios;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }

    @Override
    public String toString() {
        return nombre;
    }
}
