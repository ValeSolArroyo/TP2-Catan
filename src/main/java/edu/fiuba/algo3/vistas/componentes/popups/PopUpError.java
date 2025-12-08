package edu.fiuba.algo3.vistas.componentes.popups;

import javafx.scene.control.Alert;

public class PopUpError {
    public static void mostrar(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(mensaje);

        alert.getDialogPane().getStylesheets().add(PopUpAcercaDe.class.getResource("/styles/styles.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("error");
        alert.getDialogPane().setMinHeight(200);
        alert.getDialogPane().setMinWidth(400);
        alert.showAndWait();
    }
}
