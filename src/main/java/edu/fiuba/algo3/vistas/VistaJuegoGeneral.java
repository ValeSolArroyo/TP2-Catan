package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.vistas.componentes.BotonJuego;
import edu.fiuba.algo3.vistas.componentes.ContenedorDados;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VistaJuegoGeneral extends BorderPane {
    private Juego juego;
    public VistaJuegoGeneral(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego){
        this.juego = juego;
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

        BotonJuego botonJugarCarta =  new BotonJuego("Jugar carta");
        HBox contenedorArribaIzquierda = new HBox(botonJugarCarta);
        //TODO: ver si cambiamos el padding una vez agregamos botones ladron..
        contenedorArribaIzquierda.setPadding(new Insets(80, 0, 0, 110));
        contenedorArribaIzquierda.setAlignment(Pos.CENTER_LEFT);

        this.setLeft(contenedorArribaIzquierda);

        Button botonFinTurno = new Button("Finalizar turno");
        botonFinTurno.getStyleClass().add("boton-fin-turno");
        botonFinTurno.setPrefWidth(230);
        botonFinTurno.setPrefHeight(45);
        HBox contenedorAbajoIzquierda = new HBox(botonFinTurno);
        contenedorAbajoIzquierda.setPadding(new Insets(0, 0, 20,95 ));
        StackPane.setAlignment(botonFinTurno, Pos.BOTTOM_LEFT);

        this.setBottom(contenedorAbajoIzquierda);

        Transicion.fade(this);
    }
}
