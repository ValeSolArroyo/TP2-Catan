package edu.fiuba.algo3.controllers.cartasDesarrollo;

import edu.fiuba.algo3.controllers.fasesJuego.AccionHexagonoControlador;
import edu.fiuba.algo3.controllers.fasesJuego.RoboControlador;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.Caballero;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaCaballero;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpInformativo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CaballeroControlador implements AccionHexagonoControlador, RoboControlador {
    private Juego juego;
    private VistaCaballero vistaCaballero;
    private Hexagono nuevoLugar;
    private VistaTablero vistaTablero;
    private Jugador victima;
    private ContenedorPrincipalVistas contenedor;

    public CaballeroControlador(Juego juego, VistaTablero vistaTablero, ContenedorPrincipalVistas contenedor) {
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.vistaTablero.setControlador(this);
        this.contenedor = contenedor;
    }

    @Override
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

    @Override
    public void conseguirVictima(Jugador victima){
        this.victima = victima;
        vistaCaballero.desactivarRobarCarta();
        vistaCaballero.ocultarJugadoresParaRobar();
        vistaCaballero.activarBotonEjecutar();
    }

    @Override
    public void ejecutar() {
        Jugador jugadorActual = juego.jugadorActual();
        try {
            Accion accion = new Caballero(juego, nuevoLugar, victima, juego.jugadorActual());
            juego.ejecutarAccion(accion);
        } catch (IndexOutOfBoundsException e) {
            PopUpError.mostrar("No se le pudo robar a ese jugador ya que no tenía más recursos");
        } catch (NullPointerException e) {
            PopUpInformativo.mostrar("No hay jugadores para robar en donde moviste al ladrón.");
        }
        jugadorActual.eliminarCarta(new Caballero());
        VistaJuegoGeneral vistaJuego = new VistaJuegoGeneral(contenedor, juego, vistaTablero);
        contenedor.setContenido(vistaJuego);
    }

    @Override
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
        vistaCaballero.desactivarRobarCarta();
        if (posiblesVictimas.isEmpty()) {
            vistaCaballero.activarBotonEjecutar();
        } else {
            vistaCaballero.mostrarJugadoresParaRobar(posiblesVictimas);
        }
    }
}
