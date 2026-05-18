package es.gastoscompartidos.controladores;

import es.gastoscompartidos.modelo.GestorInfo;
import es.gastoscompartidos.modelo.Pago;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class VentanaHistorialController {

    @FXML private TextArea txtHistorial;

    private GestorInfo gestor = GestorInfo.getInstancia();

    @FXML
    public void initialize() {
        if (gestor.getPagos().isEmpty()) {
            txtHistorial.setText("No hay pagos registrados todavía.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Pago p : gestor.getPagos()) {
            sb.append("El usuario ").append(p.getPagador().getNombre())
              .append(" ha realizado un pago de: ").append(String.format("%.0f", p.getCantidad())).append("€\n");
        }
        txtHistorial.setText(sb.toString());
    }

    @FXML
    private void onClicSalir() {
        Stage stage = (Stage) txtHistorial.getScene().getWindow();
        stage.close();
    }
}
