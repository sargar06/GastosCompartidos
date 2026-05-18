package es.gastoscompartidos.controladores;

import es.gastoscompartidos.modelo.GestorInfo;
import es.gastoscompartidos.modelo.Grupo;
import es.gastoscompartidos.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class VentanaCrearGrupoController {

    @FXML private ListView<String> listUsuarios;
    @FXML private TextField txtNombreGrupo;
    @FXML private TextField txtDescripcion;
    @FXML private Label lblMensaje;

    private GestorInfo gestor = GestorInfo.getInstancia();

    @FXML
    public void initialize() {
        listUsuarios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (Usuario u : gestor.getUsuarios()) {
            listUsuarios.getItems().add(u.toString());
        }
    }

    @FXML
    private void onClicSeleccionarTodos() {
        listUsuarios.getSelectionModel().selectAll();
    }

    @FXML
    private void onClicCrearGrupo() {
        String nombre = txtNombreGrupo.getText().trim();

        if (nombre.isEmpty()) {
            lblMensaje.setText("Escribe el nombre del grupo.");
            return;
        }

        List<Integer> indices = listUsuarios.getSelectionModel().getSelectedIndices();
        if (indices.isEmpty()) {
            lblMensaje.setText("Selecciona al menos un usuario.");
            return;
        }

        List<Usuario> miembros = new ArrayList<>();
        for (int i : indices) {
            miembros.add(gestor.getUsuarios().get(i));
        }

        Grupo g = new Grupo(0, nombre, txtDescripcion.getText().trim(), miembros);
        gestor.agregarGrupo(g);

        Stage stage = (Stage) txtNombreGrupo.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onClicCancelar() {
        Stage stage = (Stage) txtNombreGrupo.getScene().getWindow();
        stage.close();
    }
}
