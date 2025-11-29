package edu.fiuba.algo3.vistas.componentes;

import javafx.scene.control.Button;

public class BotonMenu extends Button {

    public BotonMenu(String texto) {
        super(texto);
        this.getStyleClass().add("boton-menu");
        this.setPrefWidth(100);
        this.setPrefHeight(28);
    }
}
