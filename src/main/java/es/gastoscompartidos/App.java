package es.gastoscompartidos;

import es.gastoscompartidos.modelo.GestorInfo;
import es.gastoscompartidos.persistencia.Escritor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(
            getClass().getResource("/es/gastoscompartidos/vistas/VentanaPrincipal.fxml")
        );
        stage.setTitle("Gestor de Gastos Compartidos");
        stage.setScene(new Scene(root, 640, 480));
        stage.show();
    }

    @Override
    public void stop() {
        // Al cerrar guardamos los usuarios en fichero
        Escritor escritor = new Escritor("usuarios.txt");
        escritor.escribirUsuarios(GestorInfo.getInstancia().getUsuarios());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
