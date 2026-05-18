package es.gastoscompartidos.persistencia;

import es.gastoscompartidos.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lector {

    private String ruta;

    public Lector(String ruta) {
        this.ruta = ruta;
    }

    public List<Usuario> leerUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        File f = new File(ruta);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("#") || linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                if (p.length >= 4) {
                    Usuario u = new Usuario();
                    u.setId(Integer.parseInt(p[0].trim()));
                    u.setNombre(p[1].trim());
                    u.setEmail(p[2].trim());
                    u.setMetodoPago(p[3].trim());
                    lista.add(u);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer fichero: " + e.getMessage());
        }

        return lista;
    }
}
