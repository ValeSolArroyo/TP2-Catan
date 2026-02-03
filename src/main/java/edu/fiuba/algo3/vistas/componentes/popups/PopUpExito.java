package edu.fiuba.algo3.vistas.componentes.popups;

import javafx.scene.control.Alert;

public class PopUpExito {
    public static void mostrar(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Enhorabuena!!");
        alert.setContentText(mensaje);

        alert.getDialogPane().getStylesheets().add(PopUpAcercaDe.class.getResource("/styles/styles.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("exito");
        alert.getDialogPane().setPrefHeight(mensaje.length()*4.5);
        alert.showAndWait();
    }
}
