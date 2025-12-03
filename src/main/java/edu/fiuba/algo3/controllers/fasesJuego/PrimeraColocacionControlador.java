package edu.fiuba.algo3.controllers.fasesJuego;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.AccionPrimeraColocacion;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import javafx.stage.Stage;

public class PrimeraColocacionControlador implements FaseJuegoControlador, AccionesTableroControlador {
    private Juego juego;
    private Stage stage;
    private ContenedorPrincipalVistas contenedor;

    private Vertice vertice;
    private Arista arista;

    public PrimeraColocacionControlador(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego, VistaTablero vistaTablero) {
        this.juego = juego;
        this.stage = stage;
        this.contenedor = contenedor;

    }

    @Override
    public void ejecutarAccion() {
        Jugador jugador = juego.jugadorActual();
        AccionPrimeraColocacion primeraColocacion = new AccionPrimeraColocacion(this.juego, this.vertice, this.arista);
        juego.ejecutarAccion(primeraColocacion);

    }

    @Override
    public void obtenerVertice(Vertice vertice) {
        this.vertice = vertice;
    }

    @Override
    public void obtenerArista(Arista arista) {
        this.arista = arista;
    }
}
