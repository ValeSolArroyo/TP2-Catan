package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.BotonAyudaControlador;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VistaJuegoGeneral extends StackPane {
    public VistaJuegoGeneral(Stage stage, ContenedorPrincipalVistas contenedor){
        this.setAlignment(Pos.CENTER);
        Image imagenTitulo = new Image(getClass().getResource("/images/backgrounds/fondo_sin_puertos.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                imagenTitulo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        this.setBackground(new Background(background));

        BorderPane layout = new BorderPane();

        // TODO: falta el tablero, acá habría que vincular con un controlador que hay que crear

        VBox botonesDerecha = new VBox(70);
        botonesDerecha.setAlignment(Pos.CENTER_RIGHT);
        botonesDerecha.setPadding(new Insets(15));

        Button botonComerciar = new Button("Comerciar");
        botonComerciar.getStyleClass().add("botones-derecha");
        botonComerciar.setPrefWidth(200);
        botonComerciar.setPrefHeight(70);

        Button botonConstruir = new Button("Construir");
        botonConstruir.getStyleClass().add("botones-derecha");
        botonConstruir.setPrefWidth(200);
        botonConstruir.setPrefHeight(70);

        Button botonComprarCartas = new Button("Comprar cartas");
        botonComprarCartas.getStyleClass().add("botones-derecha");
        botonComprarCartas.setPrefWidth(220);
        botonComprarCartas.setPrefHeight(70);

        // TODO: agregar dados

        botonesDerecha.getChildren().addAll(botonComerciar, botonConstruir, botonComprarCartas);
        layout.setRight(botonesDerecha);

        Button botonFinTurno = new Button("Finalizar turno");
        botonFinTurno.getStyleClass().add("boton-fin-turno");
        botonFinTurno.setPrefWidth(230);
        botonFinTurno.setPrefHeight(45);

        BorderPane.setAlignment(botonFinTurno, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(botonFinTurno, new Insets(20, 5, 15, 25));
        layout.setBottom(botonFinTurno);

        Button botonAyuda = new Button("Ayuda");
        botonAyuda.getStyleClass().add("boton-ayuda");
        botonAyuda.setPrefWidth(130);
        botonAyuda.setOnAction(new BotonAyudaControlador(stage, contenedor));
        StackPane.setAlignment(botonAyuda, Pos.BOTTOM_RIGHT);

        this.getChildren().addAll(layout, botonAyuda);

        FadeTransition transition = new FadeTransition(Duration.seconds(0.4), this);
        transition.setFromValue(0.4);
        transition.setToValue(1);
        transition.play();
    }
}
