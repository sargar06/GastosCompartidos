package es.gastoscompartidos.persistencia;

import es.gastoscompartidos.modelo.Usuario;

import java.io.*;
import java.util.List;

public class Escritor {

    private String ruta;

    public Escritor(String ruta) {
        this.ruta = ruta;
    }

    public void escribirUsuarios(List<Usuario> usuarios) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            pw.println("# id;nombre;email;metodoPago");
            for (Usuario u : usuarios) {
                pw.println(u.getId() + ";" + u.getNombre() + ";" +
                           u.getEmail() + ";" + u.getMetodoPago());
            }
        } catch (IOException e) {
            System.out.println("Error al escribir fichero: " + e.getMessage());
        }
    }
}
