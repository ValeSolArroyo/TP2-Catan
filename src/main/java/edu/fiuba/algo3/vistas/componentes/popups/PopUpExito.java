package edu.fiuba.algo3.vistas.componentes.popups;

import javafx.scene.control.Alert;

public class PopUpExito {
    public static void mostrar(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Enhorabuena!!");
        alert.setContentText(mensaje);

        alert.getDialogPane().getStylesheets().add(PopUpAcercaDe.class.getResource("/styles/styles.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("exito");
        alert.getDialogPane().setMinHeight(400);
        alert.getDialogPane().setMinWidth(400);
        alert.showAndWait();
    }
}
