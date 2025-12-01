package edu.fiuba.algo3.controllers;

import javafx.stage.Stage;

public class MenuGlobalControlador {
    private final Stage stage;

    public MenuGlobalControlador(Stage stage) {
        this.stage = stage;
    }

    public void activarPantallaCompleta() {
        // TODO: no anda :(
        this.stage.setFullScreen(true);
    }

    public void salir() {
        this.stage.close();
    }
}
