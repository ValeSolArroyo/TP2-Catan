package edu.fiuba.algo3.controllers.cartasDesarrollo;

import edu.fiuba.algo3.controllers.fasesJuego.AccionControlador;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.ProgresoMonopolio;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaProgresoMonopolio;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpInformativo;

import java.util.List;

public class ProgresoMonopolioControlador implements AccionControlador {
    private Juego juego;
    private Recurso recursoElegido;
    private VistaProgresoMonopolio vista;
    private VistaJuegoGeneral vistaJuego;
    private ContenedorPrincipalVistas contenedor;

    public ProgresoMonopolioControlador(Juego juego, VistaJuegoGeneral vistaJuego, ContenedorPrincipalVistas contenedor){
        this.juego = juego;
        this.vistaJuego = vistaJuego;
        this.contenedor = contenedor;
    }

    public void setVistaProgreso(VistaProgresoMonopolio vista) {
        this.vista = vista;
    }

    public void elegirRecurso(Recurso tipo){
        recursoElegido = tipo;
        vista.desactivarBotones();
        vista.activarBotonEjecutar();
    }

    @Override
    public void ejecutar() {
        Jugador jugadorActual =  juego.jugadorActual();
        Accion accion = new ProgresoMonopolio(juego, recursoElegido, jugadorActual);
        try {
            juego.ejecutarAccion(accion);
        } catch (RecursosInsuficientesError e) {
            PopUpInformativo.mostrar("Los jugadores que no tienen de ese tipo no pudieron entregar.");
            jugadorActual.eliminarCarta(new ProgresoMonopolio());
        }
        contenedor.setContenido(vistaJuego);
    }

    public List<Recurso> getTiposDeRecurso() {
        return List.of(new Madera(), new Ladrillo(), new Grano(), new Mineral(), new Lana());
    }
}