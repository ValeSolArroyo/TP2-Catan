package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.vistas.componentes.BotonJuego;
import edu.fiuba.algo3.vistas.componentes.ContenedorDados;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
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
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo_cartas_arriba.jpg"));

        // TODO: falta el tablero, acá habría que vincular con un controlador que hay que crear

        VBox botonesDerecha = new VBox(50);
        botonesDerecha.setAlignment(Pos.CENTER_RIGHT);
        botonesDerecha.setPadding(new Insets(100, 20, 0, 0));

        BotonJuego botonComerciar = new BotonJuego("Comerciar");
        BotonJuego botonConstruir = new BotonJuego("Construir");
        BotonJuego botonComprarCartas = new BotonJuego("Comprar cartas");

        botonesDerecha.getChildren().addAll(botonComerciar, botonConstruir, botonComprarCartas);

        ContenedorDados contenedorDados = new ContenedorDados();
        botonesDerecha.getChildren().add(contenedorDados);
        this.setRight(botonesDerecha);

        // TODO: agregar botones de Jugar Carta y si sale ladrón que aparezcan los botones correspondientes
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
