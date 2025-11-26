package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.VolverAlJuegoControlador;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VistaAyuda extends StackPane{
    public VistaAyuda(Stage stage, ContenedorPrincipalVistas contenedor) {
        this.setAlignment(Pos.CENTER);
        Image imagenAyuda = new Image(getClass().getResource("/images/backgrounds/ayuda.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                imagenAyuda,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        this.setBackground(new Background(background));

        Button botonVolver = new Button("Volver");
        botonVolver.getStyleClass().add("boton-ayuda");
        botonVolver.setPrefWidth(130);

        StackPane.setAlignment(botonVolver, Pos.BOTTOM_RIGHT);

        botonVolver.setOnAction(new VolverAlJuegoControlador(stage, contenedor));

        this.getChildren().add(botonVolver);

        FadeTransition transition = new FadeTransition(Duration.seconds(0.4), this);
        transition.setFromValue(0.4);
        transition.setToValue(1);
        transition.play();
    }
}
