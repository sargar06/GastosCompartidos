module es.gastoscompartidos {
    requires javafx.controls;
    requires javafx.fxml;

    opens es.gastoscompartidos to javafx.fxml;
    opens es.gastoscompartidos.controladores to javafx.fxml;

    exports es.gastoscompartidos;
    exports es.gastoscompartidos.modelo;
    exports es.gastoscompartidos.persistencia;
    exports es.gastoscompartidos.controladores;
}
