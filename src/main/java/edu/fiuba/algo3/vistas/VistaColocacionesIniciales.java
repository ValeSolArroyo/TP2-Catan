package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.controllers.fasesJuego.PrimerasColocacionesControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.vistas.componentes.*;
import edu.fiuba.algo3.vistas.componentes.botones.BotonGenerico;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaColocacionesIniciales extends BorderPane  {
    private final VistaTurnoActual vistaTurno;
    private Juego juego;
    private Stage stage;
    private ContenedorPrincipalVistas contenedor;
    private BotonJuego botonPoblado;
    private BotonGenerico botonCarretera;
    private BotonGenerico botonFinalizar;
    private PrimerasColocacionesControlador controlador;
    private CambioTurnoControlador cambioTurno;
    private VistaTablero vistaTablero;


    public VistaColocacionesIniciales(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego, CambioTurnoControlador cambioTurno, VistaTablero  vistaTablero) {
        this.stage = stage;
        this.contenedor = contenedor;
        this.juego = juego;
        this.vistaTablero = vistaTablero;

        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo_sin_cartas.jpg"));
        HBox barraJugadores = new HBox(20);
        barraJugadores.setAlignment(Pos.CENTER);

        for (Jugador jugador : juego.getJugadores()) {
            InfoJugador info = new InfoJugador(jugador);
            HBox.setMargin(info, new Insets(0, 10, 0, 10));
            barraJugadores.getChildren().add(info);
        }

        this.setTop(barraJugadores);

        cambioTurno.agregarTablero(this.vistaTablero);

        this.vistaTurno = new VistaTurnoActual(cambioTurno);

        PrimerasColocacionesControlador controladorColocaciones = new PrimerasColocacionesControlador(juego, vistaTablero, this, cambioTurno);

        this.vistaTablero.setControlador(controladorColocaciones);
        this.controlador = controladorColocaciones;

        HBox contenedorCentro = new HBox(this.vistaTablero);
        contenedorCentro.setPadding(new Insets(0, 0, 10, 425));

        this.setCenter(contenedorCentro);

        VBox botonesDerecha = new VBox(50);
        botonesDerecha.setAlignment(Pos.CENTER_RIGHT);
        botonesDerecha.setPadding(new Insets(100, 20, 100, 0));

        this.botonPoblado = new BotonJuego("Colocar Poblado");
        this.botonCarretera = new BotonGenerico("Colocar Carretera", "botones-derecha", 230, 70);

        botonPoblado.setDisable(false);
        botonCarretera.setDisable(true);

        botonesDerecha.getChildren().addAll(botonPoblado, botonCarretera);
        this.setRight(botonesDerecha);

        this.botonFinalizar = new BotonGenerico("Finalizar colocaciones", "boton-fin-turno", 270, 45);
        botonFinalizar.setDisable(true);
        HBox contenedorAbajo = new HBox(20);
        contenedorAbajo.setAlignment(Pos.CENTER_LEFT);
        contenedorAbajo.setPadding(new Insets(0, 0, 20, 95));
        contenedorAbajo.getChildren().addAll(botonFinalizar, vistaTurno);

        HBox.setMargin(vistaTurno, new Insets(0, 0, 0, 200));

        this.setBottom(contenedorAbajo);

        botonPoblado.setOnAction(e -> controlador.iniciarPoblado());
        botonCarretera.setOnAction(e -> controlador.iniciarCarretera());
        botonFinalizar.setOnAction(e -> controlador.ejecutar());


        Transicion.fade(this);
    }

    public void activarPoblado(boolean activar) { botonPoblado.setDisable(!activar); }
    public void activarCarretera(boolean activar) { botonCarretera.setDisable(!activar); }
    public void activarFinalizar(boolean activar) { botonFinalizar.setDisable(!activar); }

}
