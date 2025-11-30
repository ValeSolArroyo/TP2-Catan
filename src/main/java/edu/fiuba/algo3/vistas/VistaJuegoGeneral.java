package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.TableroControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.componentes.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VistaJuegoGeneral extends BorderPane {
    private Juego juego;
    public VistaJuegoGeneral(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego){
        this.juego = juego;
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo.jpg"));

        HBox barraJugadores = new HBox(20);
        barraJugadores.setAlignment(Pos.CENTER);

        for (Jugador jugador : juego.getJugadores()) {
            InfoJugador info = new InfoJugador(jugador);
            HBox.setMargin(info, new Insets(0, 10, 0, 10));
            barraJugadores.getChildren().add(info);
        }

        this.setTop(barraJugadores);

        TableroControlador controladorTablero = new TableroControlador(juego.getTablero());
        Pane vistaTablero = controladorTablero.mostrarTablero();
        this.setCenter(vistaTablero);

        VBox botonesDerecha = new VBox(50);
        botonesDerecha.setAlignment(Pos.CENTER_RIGHT);
        botonesDerecha.setPadding(new Insets(100, 20, 0, 0));

        BotonJuego botonComerciar = new BotonJuego("Comerciar");
        BotonJuego botonConstruir = new BotonJuego("Construir");
        BotonJuego botonComprarCartas = new BotonJuego("Comprar cartas");

        botonesDerecha.getChildren().addAll(botonComerciar, botonConstruir, botonComprarCartas);

        VistaDados contenedorDados = new VistaDados();
        botonesDerecha.getChildren().add(contenedorDados);
        this.setRight(botonesDerecha);

        BotonJuego botonJugarCarta =  new BotonJuego("Jugar carta");
        HBox contenedorArribaIzquierda = new HBox(botonJugarCarta);
        //TODO: ver si cambiamos el padding una vez agregamos botones ladron..
        contenedorArribaIzquierda.setPadding(new Insets(30, 0, 0, 110));
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
