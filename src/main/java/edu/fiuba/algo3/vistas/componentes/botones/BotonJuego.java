package edu.fiuba.algo3.vistas.componentes.botones;

import javafx.scene.control.Button;

public class BotonJuego extends Button{
    public BotonJuego(String texto) {
        super(texto);
        this.getStyleClass().add("botones-derecha");
        this.setPrefWidth(210);
        this.setPrefHeight(70);
    }
}
