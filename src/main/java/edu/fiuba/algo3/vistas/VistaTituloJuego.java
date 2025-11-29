package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.BotonComenzarControlador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VistaTituloJuego extends StackPane{
    public VistaTituloJuego(Stage stage, ContenedorPrincipalVistas contenedor) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/titulo_juego.jpeg"));

        Button botonInicio = new Button("Comenzar");
        botonInicio.getStyleClass().add("boton-comenzar");
        botonInicio.setOnAction(new BotonComenzarControlador(stage, contenedor));
        StackPane.setAlignment(botonInicio, Pos.BOTTOM_CENTER);
        StackPane.setMargin(botonInicio, new Insets(0, 0, 140, 5));

        this.getChildren().add(botonInicio);

        FadeTransition transition = new FadeTransition(Duration.seconds(0.4), this);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();
    }
}
