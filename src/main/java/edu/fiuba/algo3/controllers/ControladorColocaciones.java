package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.AccionPrimeraColocacion;
import edu.fiuba.algo3.modelo.juegoCommand.AccionSegundaColocacion;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.VistaColocacionesIniciales;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import javafx.scene.paint.Color;

public class ControladorColocaciones {
    private final CambioTurnoControlador cambioTurno;
    private Juego juego;
    private VistaTablero vistaTablero;
    private VistaColocacionesIniciales vistaColocaciones;

    private Vertice vertice;
    private Arista arista;
    private boolean primeraColocacionTerminada;


    public ControladorColocaciones(Juego juego, VistaTablero vistaTablero, VistaColocacionesIniciales colocacionesIniciales, CambioTurnoControlador cambioTurno)  {
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.vistaColocaciones = colocacionesIniciales;
        this.cambioTurno = cambioTurno;
        this.primeraColocacionTerminada = false;

    }

    public void obtenerVertice(Vertice vertice) {
        this.vertice = vertice;
        vistaTablero.ocultarVertices();
    }

    public void obtenerArista(Arista arista){
        this.arista = arista;
        vistaTablero.ocultarAristas();
    }

    public void iniciarCarretera() {
        vistaTablero.mostrarAristas(vertice.getAristas());
        vistaColocaciones.activarCarretera(false);
        vistaColocaciones.activarFinalizar(true);
    }

    public void terminarColocacion() {
        int ultimoIndice = juego.getJugadores().size();
        Jugador jugador = juego.jugadorActual();
        Color color = jugador.getColor();

        if (!primeraColocacionTerminada) {
            AccionPrimeraColocacion colocacion = new AccionPrimeraColocacion(juego, vertice, arista);
            juego.ejecutarAccion(colocacion);

            if (juego.getIndiceTurno() == ultimoIndice) {
                this.primeraColocacionTerminada = true;
            }
        }else {
            juego.ejecutarAccion(new AccionSegundaColocacion(juego,vertice,arista));
        }

        vistaTablero.dibujarPobladoEn(vertice, color);
        vistaTablero.dibujarCarreteraEn(arista, color);
        cambioTurno.actualizarDatosJugadorActual();
        cambioTurno.notificarObservadores();

        vistaColocaciones.activarFinalizar(false);
        vistaColocaciones.activarPoblado(true);

    }

    public void iniciarPoblado() {
        vistaTablero.mostrarVertices();
        vistaColocaciones.activarPoblado(false);
        vistaColocaciones.activarCarretera(true);
    }
}
