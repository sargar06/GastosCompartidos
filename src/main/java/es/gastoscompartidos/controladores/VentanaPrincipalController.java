package es.gastoscompartidos.controladores;

import es.gastoscompartidos.modelo.GestorInfo;
import es.gastoscompartidos.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaPrincipalController {

    @FXML private Label lblEstado;
    @FXML private ListView<String> listUsuarios;

    private GestorInfo gestor = GestorInfo.getInstancia();

    @FXML
    public void initialize() {
        actualizarLista();
    }

    public void actualizarLista() {
        listUsuarios.getItems().clear();
        for (Usuario u : gestor.getUsuarios()) {
            listUsuarios.getItems().add(u.toString());
        }
    }

    // ---- Menú Sesión ----

    @FXML
    private void onClicLogin() throws IOException {
        abrirModal("VentanaLogin.fxml", "Login", 350, 220);
        // Actualizar estado tras login
        if (gestor.getUsuarioActual() != null) {
            lblEstado.setText("Sesión iniciada: " + gestor.getUsuarioActual().getNombre());
        }
    }

    @FXML
    private void onClicSalir() {
        System.exit(0);
    }

    // ---- Menú Usuarios ----

    @FXML
    private void onClicAgregarUsuario() throws IOException {
        abrirModal("VentanaAgregarUsuario.fxml", "Agregar Usuario", 380, 260);
        actualizarLista();
    }

    @FXML
    private void onClicEliminarUsuario() {
        int idx = listUsuarios.getSelectionModel().getSelectedIndex();
        if (idx < 0) {
            alerta("Selecciona un usuario de la lista.");
            return;
        }
        gestor.eliminarUsuario(gestor.getUsuarios().get(idx));
        actualizarLista();
    }

    // ---- Menú Grupos ----

    @FXML
    private void onClicCrearGrupo() throws IOException {
        abrirModal("VentanaCrearGrupo.fxml", "Crear Grupo", 480, 360);
    }

    // ---- Menú Gastos ----

    @FXML
    private void onClicAgregarPago() throws IOException {
        if (gestor.getUsuarioActual() == null) {
            alerta("Debes hacer Login primero.");
            return;
        }
        abrirModal("VentanaAgregarDeuda.fxml", "Añadir Deuda", 460, 320);
    }

    @FXML
    private void onClicHistorial() throws IOException {
        abrirModal("VentanaHistorial.fxml", "Historial de Pagos", 440, 360);
    }

    @FXML
    private void onClicFinalizar() throws IOException {
        abrirModal("VentanaFinalizar.fxml", "Resultado Final", 440, 380);
    }

    // ---- Utilidades ----

    private void abrirModal(String fxml, String titulo, int w, int h) throws IOException {
        Parent root = FXMLLoader.load(
            getClass().getResource("/es/gastoscompartidos/vistas/" + fxml)
        );
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(new Scene(root, w, h));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void alerta(String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
