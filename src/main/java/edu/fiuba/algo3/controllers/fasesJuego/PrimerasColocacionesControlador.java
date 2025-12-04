package edu.fiuba.algo3.controllers.fasesJuego;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.juegoCommand.AccionPrimeraColocacion;
import edu.fiuba.algo3.modelo.juegoCommand.AccionSegundaColocacion;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.VistaColocacionesIniciales;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import javafx.scene.paint.Color;


public class PrimerasColocacionesControlador implements  AccionesTableroControlador {
    private final CambioTurnoControlador cambioTurno;
    private Juego juego;
    private VistaTablero vistaTablero;
    private VistaColocacionesIniciales vistaColocaciones;
    private Vertice vertice;
    private Arista arista;
    private boolean esSegundaColocacion;

    public PrimerasColocacionesControlador(Juego juego, VistaTablero vistaTablero, VistaColocacionesIniciales colocacionesIniciales, CambioTurnoControlador cambioTurno) {
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.vistaColocaciones = colocacionesIniciales;
        this.cambioTurno = cambioTurno;
        this.esSegundaColocacion = false;
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

    public void iniciarPoblado() {
        vistaTablero.mostrarVertices();
        vistaColocaciones.activarPoblado(false);
        vistaColocaciones.activarCarretera(true);
    }

    public void iniciarCarretera() {
        vistaTablero.mostrarAristas(vertice.getAristas());
        vistaColocaciones.activarCarretera(false);
        vistaColocaciones.activarFinalizar(true);
    }

    public void terminarColocacion() {
        Accion accion;
        Jugador jugador = juego.jugadorActual();
        Color color = jugador.getColor();
        int ultimoIndice = juego.getJugadores().size() -1;
        int turnoActual = juego.getIndiceTurno();

        if (!esSegundaColocacion) {
            accion = new AccionPrimeraColocacion(juego, vertice, arista);
            System.out.println("1era colocación");
        } else {
            accion = new AccionSegundaColocacion(juego, vertice, arista);
            System.out.println("2da colocación");
        }

        juego.ejecutarAccion(accion);

        vistaTablero.dibujarPobladoEn(vertice, color);
        vistaTablero.dibujarCarreteraEn(arista, color);

        cambioTurno.actualizarDatosJugadorActual();
        cambioTurno.notificarObservadores();

        if (!esSegundaColocacion && turnoActual == ultimoIndice) {
            this.esSegundaColocacion = true;
            System.out.println("Fin de la 1ra colocación. Iniciando 2da colocación (inverso)");
        } else {
            if (esSegundaColocacion && turnoActual == 0) {
                vistaColocaciones.activarFinalizar(false);
                vistaColocaciones.activarPoblado(false);
                vistaColocaciones.activarCarretera(false);
                System.out.println("Fin de la 2da colocación. Pasamos a tirar dados");
                cambioTurno.activarAccionFinTurno();
            }
        }
        vistaColocaciones.activarFinalizar(false);
        vistaColocaciones.activarCarretera(false);
        vistaColocaciones.activarPoblado(true);
    }
}
