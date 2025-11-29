package edu.fiuba.algo3.vistas;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VistaJuegoGeneral extends BorderPane {
    public VistaJuegoGeneral(Stage stage, ContenedorPrincipalVistas contenedor){
        Image imagenTitulo = new Image(getClass().getResource("/images/backgrounds/fondo_sin_puertos.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                imagenTitulo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        this.setBackground(new Background(background));

        // TODO: falta el tablero, acá habría que vincular con un controlador que hay que crear

        VBox botonesDerecha = new VBox(50);
        botonesDerecha.setAlignment(Pos.CENTER_RIGHT);
        botonesDerecha.setPadding(new Insets(100, 20, 0, 0));

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

        botonesDerecha.getChildren().addAll(botonComerciar, botonConstruir, botonComprarCartas);

        HBox contenedorDados = new HBox(15);
        contenedorDados.setAlignment(Pos.BOTTOM_RIGHT);

        // TODO: ver cómo hacemos al tirar los dados...... por ahora PLACEHOLDER!
        Image imagenDado1 = new Image(getClass().getResource("/images/utils/dado/1.png").toExternalForm());
        Image imagenDado2 = new Image(getClass().getResource("/images/utils/dado/2.png").toExternalForm());

        ImageView dado1 = new ImageView(imagenDado1);
        dado1.setFitWidth(100);
        dado1.setFitHeight(100);

        ImageView dado2 = new ImageView(imagenDado2);
        dado2.setFitWidth(100);
        dado2.setFitHeight(100);

        contenedorDados.getChildren().addAll(dado1, dado2);
        botonesDerecha.getChildren().add(contenedorDados);
        this.setRight(botonesDerecha);

        Button botonFinTurno = new Button("Finalizar turno");
        botonFinTurno.getStyleClass().add("boton-fin-turno");
        botonFinTurno.setPrefWidth(230);
        botonFinTurno.setPrefHeight(45);

        StackPane contenedorFinTurno = new StackPane(botonFinTurno);
        contenedorFinTurno.setPadding(new Insets(20));
        StackPane.setAlignment(botonFinTurno, Pos.BOTTOM_CENTER);

        this.setBottom(contenedorFinTurno);

        FadeTransition transition = new FadeTransition(Duration.seconds(0.4), this);
        transition.setFromValue(0.4);
        transition.setToValue(1);
        transition.play();
    }
}
