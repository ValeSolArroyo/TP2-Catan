package edu.fiuba.algo3.controllers.cartasDesarrollo;

import edu.fiuba.algo3.controllers.fasesJuego.AccionHexagonoControlador;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.Caballero;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaCaballero;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;

public class CaballeroControlador implements AccionHexagonoControlador {
    private Juego juego;
    private VistaCaballero vistaCaballero;
    private Hexagono nuevoLugar;
    private VistaTablero vistaTablero;
    private Jugador victima;
    private ContenedorPrincipalVistas contenedor;
    private VistaJuegoGeneral vistaJuego;

    public CaballeroControlador(Juego juego, VistaTablero vistaTablero, VistaJuegoGeneral vistaJuego, ContenedorPrincipalVistas contenedor) {
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.vistaTablero.setControlador(this);
        this.vistaJuego = vistaJuego;
        this.contenedor = contenedor;
    }

    public void elegirLugarLadron(VistaCaballero vistaCaballero) {
        this.vistaCaballero = vistaCaballero;
        vistaCaballero.desactivarMoverLadron();
        vistaTablero.activarHexagonos();
    }

    @Override
    public void obtenerHexagono(Hexagono hexagono) {
       this.nuevoLugar = hexagono;
       vistaTablero.desactivarHexagonos();
       vistaCaballero.activarBotonRobar();
    }

    public void conseguirVictima(Jugador victima){
        this.victima = victima;
    }

    @Override
    public void ejecutar() {
        Accion accion = new Caballero(juego, nuevoLugar, victima, juego.jugadorActual());
        juego.ejecutarAccion(accion);
        contenedor.setContenido(vistaJuego);

    }

    public void elegirVictima() {
        //la idea es que consiga los jugadores que tiene asentamiento en ese hexgono y le pase a la vista sus instancias
        //con sus nombres como texto y cada boton guarda la instancia del jugador
        vistaCaballero.desactivarRobarCarta();
        vistaCaballero.activarBotonEjecutar();
    }
}
