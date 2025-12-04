package edu.fiuba.algo3.controllers.fasesJuego;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.AccionPrimeraColocacion;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;


public class PrimeraColocacionControlador implements FaseJuegoControlador, AccionesTableroControlador {
    private Juego juego;
    private VistaTablero vistaTablero;

    private Vertice vertice;
    private Arista arista;

    public PrimeraColocacionControlador(Juego juego, VistaTablero vistaTablero) {
        this.juego = juego;
        this.vistaTablero = vistaTablero;
    }

    @Override
    public void ejecutarAccion() {
        Jugador jugador = juego.jugadorActual();
        AccionPrimeraColocacion primeraColocacion = new AccionPrimeraColocacion(this.juego, this.vertice, this.arista);
        juego.ejecutarAccion(primeraColocacion);
        System.out.println("Me ejecuté (primera colocacion)");
        // TODO: construir! pasando el color del jugador
    }

    @Override
    public void obtenerVertice(Vertice vertice) {
        this.vertice = vertice;
        vistaTablero.ocultarVertices();
    }

    @Override
    public void obtenerArista(Arista arista) {
        this.arista = arista;
        vistaTablero.ocultarAristas();
        ejecutarAccion();
    }
}
