package es.gastoscompartidos.controladores;

import es.gastoscompartidos.modelo.GestorInfo;
import es.gastoscompartidos.modelo.Pago;
import es.gastoscompartidos.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class VentanaAgregarDeudaController {

    @FXML private TextField txtCantidad;
    @FXML private TextField txtDescripcion;
    @FXML private ListView<String> listDeudores;
    @FXML private Label lblMensaje;

    private GestorInfo gestor = GestorInfo.getInstancia();

    @FXML
    public void initialize() {
        listDeudores.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (Usuario u : gestor.getUsuarios()) {
            listDeudores.getItems().add(u.toString());
        }
    }

    @FXML
    private void onClicAnadirDeuda() {
        String cantidadStr = txtCantidad.getText().trim();
        String descripcion = txtDescripcion.getText().trim();

        if (cantidadStr.isEmpty() || descripcion.isEmpty()) {
            lblMensaje.setText("Rellena todos los campos.");
            return;
        }

        double cantidad;
        try {
            cantidad = Double.parseDouble(cantidadStr.replace(",", "."));
        } catch (NumberFormatException e) {
            lblMensaje.setText("La cantidad no es válida.");
            return;
        }

        List<Integer> indices = listDeudores.getSelectionModel().getSelectedIndices();
        if (indices.isEmpty()) {
            lblMensaje.setText("Selecciona al menos un deudor.");
            return;
        }

        List<Usuario> deudores = new ArrayList<>();
        for (int i : indices) {
            deudores.add(gestor.getUsuarios().get(i));
        }

        Pago pago = new Pago(0, cantidad, descripcion, gestor.getUsuarioActual(), deudores);
        gestor.agregarPago(pago);

        Stage stage = (Stage) txtCantidad.getScene().getWindow();
        stage.close();
    }
}
