package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
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


    public VistaConstruir(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego, VistaTablero vistaTablero) {
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
        HBox.setMargin(this.vistaTablero, new Insets(0, 0, 7, 100));
        this.setCenter(tableroContenedor);

        inicializarBotones();

        CambioTurnoControlador cambioTurno = new CambioTurnoControlador(stage, contenedor, juego);
        cambioTurno.agregarTablero(this.vistaTablero);
        VistaTurnoActual vistaTurno = new VistaTurnoActual(cambioTurno);

        botonFinTurno = new BotonGenerico("Finalizar turno", "boton-fin-turno", 230, 45);

        HBox contenedorAbajo = new HBox(20);
        contenedorAbajo.setAlignment(Pos.CENTER_LEFT);
        contenedorAbajo.setPadding(new Insets(0, 0, 20, 95));
        contenedorAbajo.getChildren().addAll(botonFinTurno, vistaTurno);

        HBox.setMargin(vistaTurno, new Insets(0, 0, 0, 200));

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
