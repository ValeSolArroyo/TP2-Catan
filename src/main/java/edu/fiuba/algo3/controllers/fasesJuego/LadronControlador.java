package edu.fiuba.algo3.controllers.fasesJuego;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.juegoCommand.AccionMoverLadron;
import edu.fiuba.algo3.modelo.juegoCommand.AccionRobarCarta;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaCaballero;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpInformativo;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LadronControlador implements AccionHexagonoControlador, RoboControlador {
    private final Stage stage;
    private final Juego juego;
    private VistaCaballero vistaLadron;
    private Hexagono nuevoLugar;
    private final VistaTablero vistaTablero;
    private Jugador victima;
    private final ContenedorPrincipalVistas contenedor;

    public LadronControlador(Stage stage, Juego juego, VistaTablero vistaTablero, ContenedorPrincipalVistas contenedor) {
        this.stage = stage;
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.vistaTablero.setControlador(this);
        this.contenedor = contenedor;
    }

    @Override
    public void elegirLugarLadron(VistaCaballero vistaCaballero) {
        this.vistaLadron = vistaCaballero;
        vistaCaballero.desactivarMoverLadron();
        vistaTablero.activarHexagonos();
    }

    @Override
    public void obtenerHexagono(Hexagono hexagono) {
        this.nuevoLugar = hexagono;
        vistaTablero.mostrarLadronEn(hexagono);
        vistaTablero.desactivarHexagonos();
        vistaLadron.activarBotonRobar();
    }

    @Override
    public void conseguirVictima(Jugador victima){
        this.victima = victima;
        vistaLadron.desactivarRobarCarta();
        vistaLadron.ocultarJugadoresParaRobar();
        vistaLadron.activarBotonEjecutar();
    }

    @Override
    public void ejecutar() {
        try {
            Accion mover = new AccionMoverLadron(juego, nuevoLugar);
            juego.ejecutarAccion(mover);
            Accion robar = new AccionRobarCarta(juego, victima);
            juego.ejecutarAccion(robar);
        } catch (IndexOutOfBoundsException e) {
            PopUpError.mostrar("No se le pudo robar a ese jugador ya que no tenía más recursos");
        } catch (NullPointerException e) {
            PopUpInformativo.mostrar("No hay jugadores para robar en donde moviste al ladrón.");
        }
        VistaJuegoGeneral vistaJuego = new VistaJuegoGeneral(stage, contenedor, juego, vistaTablero);
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
        vistaLadron.desactivarRobarCarta();
        if (posiblesVictimas.isEmpty()) {
            vistaLadron.activarBotonEjecutar();
        } else {
            vistaLadron.mostrarJugadoresParaRobar(posiblesVictimas);
        }
    }
}
