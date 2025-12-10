package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.AccionFinalizarTurno;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.observer.Observable;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaGanador;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.VistaLanzarDados;
import edu.fiuba.algo3.vistas.cartasBonificacion.VistaGranCaballeria;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CambioTurnoControlador extends Observable {
    private Juego juego;
    private ContenedorPrincipalVistas contenedor;
    private String nombreJugadorActual;
    private Color colorJugadorActual;
    private VistaTablero vistaTablero;
    private VistaJuegoGeneral vistaJuego;

    public CambioTurnoControlador(ContenedorPrincipalVistas contenedor, Juego juego) {
        this.juego = juego;
        this.contenedor = contenedor;
        actualizarDatosJugadorActual();
    }

    public void activarAccionFinTurno() {
        Jugador jugadorActual = juego.jugadorActual();
        if (jugadorActual.evaluarSiEsGanador()) {
            VistaGanador vistaGanador = new VistaGanador(jugadorActual);
            contenedor.setContenido(vistaGanador);
            return;
        }

        if (juego.revisarGranCaballeria(jugadorActual)) {
            VistaGranCaballeria vistaGCaballeria = new VistaGranCaballeria(contenedor, jugadorActual, this.vistaJuego);
            contenedor.setContenido(vistaGCaballeria);
            return;
        }

        //validarGranRutaComercial(jugadorActual);

        AccionFinalizarTurno accion = new AccionFinalizarTurno(juego);
        juego.ejecutarAccion(accion);

        actualizarDatosJugadorActual();

        this.notificarObservadores();

        VistaLanzarDados vistaLanzarDados = new VistaLanzarDados(contenedor, juego, vistaTablero);
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

    public void agregarTablero(VistaTablero vistaTablero) {
        this.vistaTablero = vistaTablero;
    }

    public void setVistaJuego(VistaJuegoGeneral vistaJuego) {
        this.vistaJuego = vistaJuego;
    }
}
