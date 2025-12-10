package edu.fiuba.algo3.vistas.cartasDesarrollo;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.controllers.fasesJuego.RoboControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.componentes.*;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class VistaCaballero extends BorderPane {
    private BotonJuego botonMoverLadron;
    private BotonJuego botonRobarCarta;
    private RoboControlador controlador;
    private BotonJuego botonEjecutar;
    private VBox panelVictimas;
    private Juego juego;
    private VistaTablero vistaTablero;

    public VistaCaballero(ContenedorPrincipalVistas contenedor, Juego juego, VistaTablero vistaTablero, RoboControlador controlador) {
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.controlador = controlador;

        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo_sin_cartas.jpg"));

        HBox barraJugadores = new HBox(20);
        barraJugadores.setAlignment(Pos.CENTER);

        for (Jugador jugador : juego.getJugadores()) {
            InfoJugador info = new InfoJugador(jugador, juego);
            HBox.setMargin(info, new Insets(0, 10, 0, 10));
            barraJugadores.getChildren().add(info);
        }

        this.setTop(barraJugadores);

        panelVictimas = new VBox(10);
        panelVictimas.setAlignment(Pos.CENTER_LEFT);
        panelVictimas.setPrefWidth(180);
        panelVictimas.setVisible(false);

        this.setLeft(panelVictimas);

        HBox tableroContenedor = new HBox(20);
        tableroContenedor.setAlignment(Pos.CENTER);
        tableroContenedor.getChildren().add(this.vistaTablero);
        HBox.setMargin(this.vistaTablero, new Insets(0, 0, 7, 140));
        this.setCenter(tableroContenedor);

        inicializarBotones();

        CambioTurnoControlador cambioTurno = new CambioTurnoControlador(contenedor, juego);
        cambioTurno.agregarTablero(this.vistaTablero);
        VistaTurnoActual vistaTurno = new VistaTurnoActual(cambioTurno);

        HBox contenedorAbajo = new HBox(20);
        contenedorAbajo.setAlignment(Pos.CENTER_LEFT);
        contenedorAbajo.setPadding(new Insets(0, 0, 20, 95));
        contenedorAbajo.getChildren().addAll(vistaTurno);

        HBox.setMargin(vistaTurno, new Insets(0, 0, 0, 450));

        this.setBottom(contenedorAbajo);

        Transicion.fade(this);
    }

    public void inicializarBotones() {
        VBox botonesDerecha = new VBox(50);
        botonesDerecha.setAlignment(Pos.CENTER_RIGHT);
        botonesDerecha.setPadding(new Insets(100, 20, 100, 0));

        this.botonMoverLadron = new BotonJuego("Mover Ladron");
        botonMoverLadron.setOnAction(e -> controlador.elegirLugarLadron(this));
        this.botonRobarCarta = new BotonJuego("Robar Carta");
        botonRobarCarta.setOnAction(e -> controlador.elegirVictima());

        this.botonEjecutar = new BotonJuego("Continuar");
        botonEjecutar.setOnAction(e -> controlador.ejecutar());

        botonMoverLadron.setDisable(false);
        botonRobarCarta.setDisable(true);
        botonEjecutar.setDisable(true);

        botonesDerecha.getChildren().addAll(botonMoverLadron, botonRobarCarta, botonEjecutar);
        this.setRight(botonesDerecha);
    }

    public void mostrarJugadoresParaRobar(List<Jugador> posiblesVictimas) {
        panelVictimas.setAlignment(Pos.CENTER_LEFT);
        for (Jugador jugador : posiblesVictimas) {
            BotonJuego boton = new BotonJuego(jugador.getNombre());
            boton.setOnAction(e -> {controlador.conseguirVictima(jugador);});
            panelVictimas.getChildren().add(boton);
        }
        panelVictimas.setVisible(true);
        this.setLeft(panelVictimas);
    }

    public void ocultarJugadoresParaRobar() {
        panelVictimas.setVisible(false);
    }

    public void activarBotonRobar() {
        this.botonRobarCarta.setDisable(false);
    }

    public void desactivarMoverLadron() {
        this.botonMoverLadron.setDisable(true);
    }

    public void desactivarRobarCarta() {
        this.botonRobarCarta.setDisable(true);
    }

    public void activarBotonEjecutar() {
        this.botonEjecutar.setDisable(false);
    }
}
