package edu.fiuba.algo3.controllers.fasesJuego;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;
import edu.fiuba.algo3.modelo.excepciones.YaHayPobladoError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.juegoCommand.AccionPrimeraColocacion;
import edu.fiuba.algo3.modelo.juegoCommand.AccionSegundaColocacion;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaColocacionesIniciales;
import edu.fiuba.algo3.vistas.VistaLanzarDados;
import edu.fiuba.algo3.vistas.componentes.VistaDados;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;
import javafx.scene.paint.Color;


public class PrimerasColocacionesControlador implements AccionesTableroControlador {
    private final CambioTurnoControlador cambioTurno;
    private Juego juego;
    private VistaTablero vistaTablero;
    private VistaColocacionesIniciales vistaColocaciones;
    private Vertice vertice;
    private Arista arista;
    private boolean esSegundaColocacion;
    private ContenedorPrincipalVistas contenedor;

    public PrimerasColocacionesControlador(Juego juego, ContenedorPrincipalVistas contenedor, VistaTablero vistaTablero, VistaColocacionesIniciales colocacionesIniciales, CambioTurnoControlador cambioTurno) {
        this.juego = juego;
        this.contenedor = contenedor;

        this.vistaTablero = vistaTablero;
        this.vistaTablero.setControlador(this);

        this.vistaColocaciones = colocacionesIniciales;
        this.cambioTurno = cambioTurno;
        this.esSegundaColocacion = false;
    }

    @Override
    public void obtenerVertice(Vertice vertice) {
        this.vertice = vertice;
        vistaTablero.ocultarVertices();
        vistaColocaciones.activarCarretera(true);
    }

    @Override
    public void obtenerArista(Arista arista) {
        this.arista = arista;
        vistaTablero.ocultarAristas();
        vistaColocaciones.activarFinalizar(true);
    }

    public void iniciarPoblado() {
        vistaTablero.mostrarVertices();
        vistaColocaciones.activarPoblado(false);
        vistaColocaciones.activarCarretera(false);
    }

    public void iniciarCarretera() {
        vistaTablero.mostrarAristas(vertice.getAristas());
        vistaColocaciones.activarCarretera(false);
        vistaColocaciones.activarFinalizar(false);
    }

    @Override
    public void ejecutar() {
        Accion accion;
        Jugador jugador = juego.jugadorActual();
        jugador.notificarObservadores();
        Color color = jugador.getColor();
        int ultimoIndice = juego.getJugadores().size() -1;
        int turnoActual = juego.getIndiceTurno();

        if (!esSegundaColocacion) {
            accion = new AccionPrimeraColocacion(juego, vertice, arista);
        } else {
            accion = new AccionSegundaColocacion(juego, vertice, arista);
        }

        try {
            juego.ejecutarAccion(accion);
        } catch (ReglaDeDistanciaError | YaHayPobladoError e) {
            PopUpError.mostrar(e.getMessage());
            resetearTurno();
            return;
        }

        vistaTablero.dibujarPobladoEn(vertice, color);
        vistaTablero.dibujarCarreteraEn(arista, color);

        cambioTurno.actualizarDatosJugadorActual();
        cambioTurno.notificarObservadores();

        if (!esSegundaColocacion && turnoActual == ultimoIndice) {
            this.esSegundaColocacion = true;
        } else {
            if (esSegundaColocacion && turnoActual == 0) {
                vistaColocaciones.activarFinalizar(false);
                vistaColocaciones.activarPoblado(false);
                vistaColocaciones.activarCarretera(false);
                VistaLanzarDados vistaDados = new VistaLanzarDados(contenedor, juego, vistaTablero);
                contenedor.setContenido(vistaDados);
            }
        }
        vistaColocaciones.activarFinalizar(false);
        vistaColocaciones.activarCarretera(false);
        vistaColocaciones.activarPoblado(true);
    }

    public void resetearTurno() {
        vistaTablero.ocultarAristas();
        vistaTablero.ocultarVertices();

        vistaColocaciones.activarFinalizar(false);
        vistaColocaciones.activarCarretera(false);
        vistaColocaciones.activarPoblado(true);
    }
}
