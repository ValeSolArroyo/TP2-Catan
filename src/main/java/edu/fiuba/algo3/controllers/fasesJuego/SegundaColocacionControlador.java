package edu.fiuba.algo3.controllers.fasesJuego;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.AccionPrimeraColocacion;
import edu.fiuba.algo3.modelo.juegoCommand.AccionSegundaColocacion;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.VistaColocacionesIniciales;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import javafx.scene.paint.Color;

public class SegundaColocacionControlador implements ControladorColocaciones {
    private final CambioTurnoControlador cambioTurno;
    private Juego juego;
    private VistaTablero vistaTablero;
    private VistaColocacionesIniciales vistaColocaciones;

    private Vertice vertice;
    private Arista arista;

    public SegundaColocacionControlador(Juego juego, VistaTablero vistaTablero, VistaColocacionesIniciales colocacionesIniciales, CambioTurnoControlador cambioTurno) {
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.vistaColocaciones = colocacionesIniciales;
        this.cambioTurno = cambioTurno;
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
    }

    @Override
    public void iniciarCarretera() {
        vistaTablero.mostrarAristas(vertice.getAristas());
        vistaColocaciones.activarCarretera(false);
        vistaColocaciones.activarFinalizar(true);
    }

    @Override
    public void terminarColocacion() {
        int turnoActual = juego.getIndiceTurno();
        Jugador jugador = juego.jugadorActual();
        Color color = jugador.getColor();
        AccionSegundaColocacion colocacion = new AccionSegundaColocacion(juego, vertice, arista);
        juego.ejecutarAccion(colocacion);

        if (turnoActual == 0) {
            // Vista dado
        } else {
            vistaTablero.dibujarPobladoEn(vertice, color);
            vistaTablero.dibujarCarreteraEn(arista, color);
            cambioTurno.actualizarDatosJugadorActual();
            cambioTurno.notificarObservadores();

            vistaColocaciones.activarFinalizar(false);
            vistaColocaciones.activarPoblado(true);
        }
    }

    @Override
    public void iniciarPoblado() {
        vistaTablero.mostrarVertices();
        vistaColocaciones.activarPoblado(false);
        vistaColocaciones.activarCarretera(true);
    }

}
