package edu.fiuba.algo3.vistas.componentes.botones;

import javafx.scene.control.Button;

public class BotonGenerico extends Button {
    public BotonGenerico(String texto, String css, int width, int height) {
        super(texto);
        this.getStyleClass().add(css);
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        }

    public BotonGenerico(String texto, String css, int width) {
        super(texto);
        this.getStyleClass().add(css);
        this.setPrefWidth(width);
    }
}
