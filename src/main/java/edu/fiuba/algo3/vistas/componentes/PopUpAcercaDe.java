package edu.fiuba.algo3.vistas.componentes;

import javafx.scene.control.Alert;
import javafx.scene.text.Font;

public class PopUpAcercaDe {
    public static void mostrar() {
        Font.loadFont(PopUpAcercaDe.class.getResourceAsStream("/fonts/Minecraft.ttf"), 20);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Catán - Trabajo Práctico de Paradigmas de Programación");
        alert.setContentText(
                "Facultad de Ingeniería de la UBA, Cátedra Suarez. 2C2025.\n\n" +
                        "Integrantes:\n" +
                        "★ Arias, Rafaela Pilar, 112272.\n" +
                        "★ Arroyo, Valentina Sol, 112294.\n" +
                        "★ Baéz, Brenda Carolina, 112496.\n" +
                        "★ López Angelini, Chiara, 111225.\n" +
                        "★ Marchesini, Carlota, 112099."
        );

        alert.getDialogPane().getStylesheets().add(PopUpAcercaDe.class.getResource("/styles/styles.css").toExternalForm());
        alert.showAndWait();
    }
}
