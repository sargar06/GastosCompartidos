package es.gastoscompartidos.modelo;

import java.util.ArrayList;
import java.util.List;

public class GestorInfo {

    private List<Usuario> usuarios;
    private List<Grupo> grupos;
    private List<Pago> pagos;
    private Usuario usuarioActual;
    private int nextId = 1;

    private static GestorInfo instancia;

    private GestorInfo() {
        usuarios = new ArrayList<>();
        grupos   = new ArrayList<>();
        pagos    = new ArrayList<>();

        // Usuarios de prueba para que no arranque vacío
        usuarios.add(new Usuario(nextId++, "Ana García",    "ana@email.com",    new ArrayList<>(), "Bizum",    "", new ArrayList<>()));
        usuarios.add(new Usuario(nextId++, "Carlos López",  "carlos@email.com", new ArrayList<>(), "Efectivo", "", new ArrayList<>()));
        usuarios.add(new Usuario(nextId++, "María Sanz",    "maria@email.com",  new ArrayList<>(), "Tarjeta",  "", new ArrayList<>()));
    }

    public static GestorInfo getInstancia() {
        if (instancia == null) {
            instancia = new GestorInfo();
        }
        return instancia;
    }

    // ---------- Usuarios ----------

    public List<Usuario> getUsuarios() { return usuarios; }

    public void agregarUsuario(Usuario u) {
        u.setId(nextId++);
        usuarios.add(u);
    }

    public void eliminarUsuario(Usuario u) {
        usuarios.remove(u);
    }

    public Usuario buscarPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    // ---------- Grupos ----------

    public List<Grupo> getGrupos() { return grupos; }

    public void agregarGrupo(Grupo g) {
        g.setId(nextId++);
        grupos.add(g);
    }

    // ---------- Pagos ----------

    public List<Pago> getPagos() { return pagos; }

    public void agregarPago(Pago p) {
        p.setId(nextId++);
        pagos.add(p);

        // Guardar en historial del pagador
        String linea = "Pagó " + p.getCantidad() + "€ por " + p.getDescripcion();
        String histActual = p.getPagador().getHistorial();
        if (histActual == null || histActual.isEmpty()) {
            p.getPagador().setHistorial(linea);
        } else {
            p.getPagador().setHistorial(histActual + "\n" + linea);
        }
    }

    // ---------- Sesión ----------

    public Usuario getUsuarioActual() { return usuarioActual; }
    public void setUsuarioActual(Usuario u) { this.usuarioActual = u; }

    // ---------- Cálculo final ----------

    public String calcularResumen() {
        if (pagos.isEmpty()) {
            return "No hay pagos registrados.";
        }

        double total = 0;
        for (Pago p : pagos) {
            total += p.getCantidad();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("TOTAL GASTOS: ").append(String.format("%.2f", total)).append("€\n\n");
        sb.append("LO QUE DEBE PAGAR CADA UNO:\n");

        for (Usuario u : usuarios) {
            double pagado = 0;
            double debe   = 0;

            for (Pago p : pagos) {
                if (p.getPagador().getId() == u.getId()) {
                    pagado += p.getCantidad();
                }
                if (p.getDeudores() != null) {
                    for (Usuario d : p.getDeudores()) {
                        if (d.getId() == u.getId()) {
                            debe += p.getCantidad() / p.getDeudores().size();
                        }
                    }
                }
            }

            double balance = pagado - debe;
            if (balance > 0.01) {
                sb.append("  ").append(u.getNombre())
                  .append(": le deben ").append(String.format("%.2f", balance)).append("€\n");
            } else if (balance < -0.01) {
                sb.append("  ").append(u.getNombre())
                  .append(": debe pagar ").append(String.format("%.2f", Math.abs(balance))).append("€\n");
            } else {
                sb.append("  ").append(u.getNombre()).append(": está al día\n");
            }
        }

        return sb.toString();
    }
}
