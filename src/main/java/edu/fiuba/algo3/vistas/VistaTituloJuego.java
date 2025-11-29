package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.BotonComenzarControlador;
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
        Image imagenTitulo = new Image(getClass().getResource("/images/backgrounds/titulo_juego.jpeg").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                imagenTitulo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        this.setBackground(new Background(background));

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
