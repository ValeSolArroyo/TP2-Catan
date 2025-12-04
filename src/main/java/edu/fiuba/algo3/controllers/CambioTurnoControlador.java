package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.AccionFinalizarTurno;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.observer.Observable;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaLanzarDados;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CambioTurnoControlador extends Observable {
    private Juego juego;
    private Stage stage;
    private ContenedorPrincipalVistas contenedor;
    private String nombreJugadorActual;
    private Color colorJugadorActual;

    public CambioTurnoControlador(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego) {
        this.juego = juego;
        this.stage = stage;
        this.contenedor = contenedor;
        actualizarDatosJugadorActual();
    }

    public void activarAccionFinTurno() {
        AccionFinalizarTurno accion = new AccionFinalizarTurno(juego);
        juego.ejecutarAccion(accion);

        actualizarDatosJugadorActual();

        this.notificarObservadores();

        VistaLanzarDados vistaLanzarDados = new VistaLanzarDados(stage, contenedor, juego);
        contenedor.setContenido(vistaLanzarDados);
    }

    public void actualizarDatosJugadorActual() {
        Jugador jugadorActual = juego.jugadorActual();
        this.nombreJugadorActual = jugadorActual.getNombre();
        this.colorJugadorActual = jugadorActual.getColor();
    }

    public String getNombreJugadorActual() {
        return nombreJugadorActual;
    }

    public Color getColorJugadorActual() {
        return colorJugadorActual;
    }
}
