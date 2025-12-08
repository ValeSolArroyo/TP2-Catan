package edu.fiuba.algo3.vistas.componentes.popups;

import javafx.scene.control.Alert;

public class PopUpInformativo {
    public static void mostrar(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Información importante!");
        alert.setContentText(mensaje);

        alert.getDialogPane().getStylesheets().add(PopUpAcercaDe.class.getResource("/styles/styles.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("acerca-de");
        alert.getDialogPane().setMinHeight(200);
        alert.getDialogPane().setMinWidth(400);
        alert.showAndWait();
    }
}
