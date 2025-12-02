package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.AccionFinalizarTurno;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.observer.Observable;


public class CambioTurnoControlador extends Observable {
    private Juego juego;
    private String nombreJugadorActual;
    private String colorJugadorActual;

    public CambioTurnoControlador(Juego juego) {
        this.juego = juego;
        actualizarDatosJugadorActual();
    }

    public void activarAccionFinTurno() {
        AccionFinalizarTurno accion = new AccionFinalizarTurno(juego);
        juego.ejecutarAccion(accion);

        actualizarDatosJugadorActual();

        this.notificarObservadores();
    }

    private void actualizarDatosJugadorActual() {
        Jugador jugadorActual = juego.jugadorActual();
        this.nombreJugadorActual = jugadorActual.getNombre();
        this.colorJugadorActual = jugadorActual.getColor();
    }

    public String getNombreJugadorActual() {
        return nombreJugadorActual;
    }

    public String getColorJugadorActual() {
        return colorJugadorActual;
    }
}
