package edu.fiuba.algo3.vistas.componentes;

import javafx.scene.control.Button;

public class BotonJuego extends Button{
    public BotonJuego(String texto) {
        super(texto);
        this.getStyleClass().add("botones-derecha");
        this.setPrefWidth(200);
        this.setPrefHeight(70);
    }
}
