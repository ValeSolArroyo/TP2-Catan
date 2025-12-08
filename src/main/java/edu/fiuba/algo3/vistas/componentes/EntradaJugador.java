package edu.fiuba.algo3.vistas.componentes;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class EntradaJugador extends HBox {
    private final TextField nombreJugador;
    private final ComboBox<String> selectorColor;

    public EntradaJugador(int numeroJugador, String[] opcionesColores) {
        super(15);

        Label textoJugador = new Label("Jugador " + numeroJugador + ":");
        textoJugador.getStyleClass().add("texto-jugador");

        this.nombreJugador = new TextField();
        nombreJugador.setPromptText("Nombre del jugador " + numeroJugador);
        nombreJugador.getStyleClass().add("input-nombre-jugador");
        nombreJugador.setPrefWidth(220);

        this.selectorColor = new ComboBox<>();
        selectorColor.getItems().addAll(opcionesColores);
        selectorColor.getStyleClass().add("selector-color");
        selectorColor.getSelectionModel().select(opcionesColores[numeroJugador - 1]);
        selectorColor.setPrefWidth(128);
        selectorColor.setPrefHeight(40);

        this.getChildren().addAll(textoJugador, nombreJugador, selectorColor);
    }

    public TextField getNombre() {
        return nombreJugador;
    }

    public ComboBox<String> getColor() {
        return selectorColor;
    }
}
