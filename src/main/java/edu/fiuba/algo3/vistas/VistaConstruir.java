package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.controllers.VolverControlador;
import edu.fiuba.algo3.controllers.fasesJuego.ConstruirControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.componentes.*;
import edu.fiuba.algo3.vistas.componentes.botones.BotonGenerico;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaConstruir extends BorderPane {
    private Juego juego;
    private VistaTablero vistaTablero;
    private ConstruirControlador construirControlador;
    private BotonGenerico botonCarretera;
    private BotonJuego botonPoblado;
    private BotonJuego botonCiudad;
    private BotonGenerico botonFinTurno;


    public VistaConstruir(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego, VistaTablero vistaTablero, VistaJuegoGeneral vistaJuego) {
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        construirControlador = new ConstruirControlador(vistaTablero, juego, this);

        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo_sin_cartas.jpg"));

        HBox barraJugadores = new HBox(20);
        barraJugadores.setAlignment(Pos.CENTER);

        for (Jugador jugador : juego.getJugadores()) {
            InfoJugador info = new InfoJugador(jugador);
            HBox.setMargin(info, new Insets(0, 10, 0, 10));
            barraJugadores.getChildren().add(info);
        }

        this.setTop(barraJugadores);

        HBox tableroContenedor = new HBox(20);
        tableroContenedor.setAlignment(Pos.CENTER);
        tableroContenedor.getChildren().add(this.vistaTablero);
        HBox.setMargin(this.vistaTablero, new Insets(0, 0, 7, 363));
        this.setCenter(tableroContenedor);

        inicializarBotones();

        CambioTurnoControlador cambioTurno = new CambioTurnoControlador(stage, contenedor, juego);
        cambioTurno.agregarTablero(this.vistaTablero);
        VistaTurnoActual vistaTurno = new VistaTurnoActual(cambioTurno);

        botonFinTurno = new BotonGenerico("Finalizar construccion", "boton-fin-turno", 300, 45);
        HBox contenedorAbajo = new HBox(20);
        contenedorAbajo.setAlignment(Pos.CENTER_LEFT);
        contenedorAbajo.setPadding(new Insets(0, 0, 20, 70));
        contenedorAbajo.getChildren().addAll(botonFinTurno, vistaTurno);

        BotonJuego botonVolver = new BotonJuego("Volver");
        botonVolver.setOnAction(new VolverControlador(contenedor, vistaJuego));
        botonVolver.setPrefWidth(100);
        botonVolver.setPrefHeight(20);

        HBox botonVolverContenedor = new HBox(botonVolver);
        botonVolverContenedor.setAlignment(Pos.CENTER_RIGHT);
        HBox.setMargin(botonVolver, new Insets(-40, 5, 0, 0));
        StackPane topContainer = new StackPane();

        topContainer.getChildren().addAll(barraJugadores, botonVolverContenedor);
        StackPane.setAlignment(barraJugadores, Pos.TOP_CENTER);
        StackPane.setAlignment(botonVolverContenedor, Pos.TOP_RIGHT);

        this.setTop(topContainer);

        HBox.setMargin(vistaTurno, new Insets(0, 0, 0, 155));

        this.setBottom(contenedorAbajo);

        Transicion.fade(this);
    }

    private void inicializarBotones() {
        VBox botonesDerecha = new VBox(50);
        botonesDerecha.setAlignment(Pos.CENTER_RIGHT);
        botonesDerecha.setPadding(new Insets(100, 20, 0, 0));
        botonCarretera = new BotonGenerico("Colocar Carretera", "botones-derecha", 250, 70);
        botonPoblado = new BotonJuego("Colocar Poblado");
        botonCiudad = new BotonJuego("Colocar Ciudad");

        botonesDerecha.getChildren().addAll(botonCarretera, botonPoblado, botonCiudad);

        this.setRight(botonesDerecha);

        botonPoblado.setOnAction(e -> construirControlador.construirPoblado());
        botonCarretera.setOnAction(e -> construirControlador.construirCarretera());
        botonCiudad.setOnAction(e -> construirControlador.construirCiudad());
    }

    public void desactivarBotones() {
        botonCarretera.setDisable(true);
        botonCarretera.setOpacity(0);
        botonPoblado.setDisable(true);
        botonPoblado.setOpacity(0);
        botonCiudad.setDisable(true);
        botonCiudad.setOpacity(0);
        botonFinTurno.setDisable(true);
    }
}
