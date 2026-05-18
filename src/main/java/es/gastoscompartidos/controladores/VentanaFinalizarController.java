package es.gastoscompartidos.controladores;

import es.gastoscompartidos.modelo.GestorInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class VentanaFinalizarController {

    @FXML private TextArea txtResumen;

    private GestorInfo gestor = GestorInfo.getInstancia();

    @FXML
    public void initialize() {
        txtResumen.setText(gestor.calcularResumen());
    }

    @FXML
    private void onClicCerrar() {
        Stage stage = (Stage) txtResumen.getScene().getWindow();
        stage.close();
    }
}
