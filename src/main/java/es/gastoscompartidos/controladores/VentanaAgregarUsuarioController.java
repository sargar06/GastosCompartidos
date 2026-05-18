package es.gastoscompartidos.controladores;

import es.gastoscompartidos.modelo.GestorInfo;
import es.gastoscompartidos.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaAgregarUsuarioController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtEmail;
    @FXML private TextField txtMetodoPago;
    @FXML private Label lblMensaje;

    private GestorInfo gestor = GestorInfo.getInstancia();

    @FXML
    private void onClicGuardar() {
        String nombre     = txtNombre.getText().trim();
        String email      = txtEmail.getText().trim();
        String metodoPago = txtMetodoPago.getText().trim();

        if (nombre.isEmpty() || email.isEmpty()) {
            lblMensaje.setText("Nombre y email son obligatorios.");
            return;
        }

        if (gestor.buscarPorEmail(email) != null) {
            lblMensaje.setText("Ya existe un usuario con ese email.");
            return;
        }

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setEmail(email);
        u.setMetodoPago(metodoPago.isEmpty() ? "Efectivo" : metodoPago);
        gestor.agregarUsuario(u);

        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onClicCancelar() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}
