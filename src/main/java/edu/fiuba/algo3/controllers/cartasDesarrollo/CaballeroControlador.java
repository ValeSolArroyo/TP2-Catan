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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        vistaTablero.mostrarLadronEn(hexagono);
        vistaTablero.desactivarHexagonos();
        vistaCaballero.activarBotonRobar();
    }

    public void conseguirVictima(Jugador victima){
        this.victima = victima;
        ejecutar();
    }

    @Override
    public void ejecutar() {
        Accion accion = new Caballero(juego, nuevoLugar, victima, juego.jugadorActual());
        juego.ejecutarAccion(accion);
        contenedor.setContenido(vistaJuego);
        System.out.println("Me ejecuté");
    }

    public void elegirVictima() {
        List<Jugador> jugadores = juego.getJugadores();
        Set<Integer> ids =  nuevoLugar.getIdsJugadoresConConstruccion();
        List<Jugador> posiblesVictimas = new ArrayList<>();

        for (Integer id : ids) {
            if (id != -1) {
                for (Jugador jugador : jugadores) {
                    if (jugador.getId() == id && jugador != juego.jugadorActual()) {
                        posiblesVictimas.add(jugador);
                    }
                }
            }
        }
        vistaCaballero.mostrarJugadoresParaRobar(posiblesVictimas);
        vistaCaballero.desactivarRobarCarta();
        vistaCaballero.activarBotonEjecutar();
        vistaCaballero.activarBotonEjecutar();
    }
}
