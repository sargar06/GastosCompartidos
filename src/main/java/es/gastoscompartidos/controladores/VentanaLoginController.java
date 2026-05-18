package es.gastoscompartidos.controladores;

import es.gastoscompartidos.modelo.GestorInfo;
import es.gastoscompartidos.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaLoginController {

    @FXML private TextField txtEmail;
    @FXML private Label lblMensaje;

    private GestorInfo gestor = GestorInfo.getInstancia();

    @FXML
    private void onClicLogin() {
        String email = txtEmail.getText().trim();

        if (email.isEmpty()) {
            lblMensaje.setText("Escribe tu email.");
            return;
        }

        Usuario u = gestor.buscarPorEmail(email);

        if (u == null) {
            // Si no existe lo creamos
            u = new Usuario();
            u.setNombre(email.split("@")[0]);
            u.setEmail(email);
            u.setMetodoPago("Efectivo");
            gestor.agregarUsuario(u);
        }

        gestor.setUsuarioActual(u);

        Stage stage = (Stage) txtEmail.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onClicCancelar() {
        Stage stage = (Stage) txtEmail.getScene().getWindow();
        stage.close();
    }
}
