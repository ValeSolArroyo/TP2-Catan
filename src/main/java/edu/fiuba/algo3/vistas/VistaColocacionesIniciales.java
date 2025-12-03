package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.controllers.TableroControlador;
import edu.fiuba.algo3.controllers.fasesJuego.PrimeraColocacionControlador;
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
    private Juego juego;
    private Stage stage;
    private ContenedorPrincipalVistas contenedor;
    private BotonJuego botonPoblado;

    public VistaColocacionesIniciales(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego) {
        this.stage = stage;
        this.contenedor = contenedor;
        this.juego = juego;

        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo_sin_cartas.jpg"));
        HBox barraJugadores = new HBox(20);
        barraJugadores.setAlignment(Pos.CENTER);

        for (Jugador jugador : juego.getJugadores()) {
            InfoJugador info = new InfoJugador(jugador);
            HBox.setMargin(info, new Insets(0, 10, 0, 10));
            barraJugadores.getChildren().add(info);
        }

        this.setTop(barraJugadores);


        Tablero tablero = juego.getTablero();
        VistaTablero vistaTablero = new VistaTablero(tablero);

        HBox contenedorCentro = new HBox(vistaTablero);
        contenedorCentro.setPadding(new Insets(0, 0, 10, 425));

        this.setCenter(contenedorCentro);

        VBox botonesDerecha = new VBox(50);
        botonesDerecha.setAlignment(Pos.CENTER_RIGHT);
        botonesDerecha.setPadding(new Insets(100, 20, 0, 0));

        BotonJuego botonPoblado = new BotonJuego("Colocar poblado");
        BotonJuego botonCarretera = new BotonJuego("Colocar Carretera");
        BotonJuego botonFinColocacion = new BotonJuego("Terminar colocación");

        botonPoblado.setDisable(false);
        botonCarretera.setDisable(true);
        botonFinColocacion.setDisable(true);

        botonesDerecha.getChildren().addAll(botonPoblado, botonCarretera, botonFinColocacion);
        this.setRight(botonesDerecha);

        CambioTurnoControlador cambioTurno = new CambioTurnoControlador(stage, contenedor, juego);
        VistaTurnoActual vistaTurno = new VistaTurnoActual(cambioTurno);

        BotonGenerico botonFinColocaciones = new BotonGenerico("Finalizar colocaciones", "boton-fin-turno", 230, 45);

        HBox contenedorAbajo = new HBox(20);
        contenedorAbajo.setAlignment(Pos.CENTER_LEFT);
        contenedorAbajo.setPadding(new Insets(0, 0, 20, 95));
        contenedorAbajo.getChildren().addAll(botonFinColocaciones, vistaTurno);

        HBox.setMargin(vistaTurno, new Insets(0, 0, 0, 200));

        this.setBottom(contenedorAbajo);

        Transicion.fade(this);
    }
}
