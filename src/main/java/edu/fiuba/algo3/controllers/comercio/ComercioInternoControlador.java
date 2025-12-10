package edu.fiuba.algo3.controllers.comercio;

import edu.fiuba.algo3.controllers.fasesJuego.AccionControlador;
import edu.fiuba.algo3.modelo.comercio.interno.ComercioInterno;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.comercio.VistaComercioInterno;
import edu.fiuba.algo3.vistas.comercio.VistaOfertaInterno;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpInformativo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComercioInternoControlador implements AccionControlador  {
    private Juego juego;
    private ContenedorPrincipalVistas contenedor;
    private VistaJuegoGeneral vistaJuego;
    private List<Recurso> recursosAEntregar;
    private List<Recurso> recursosARecibir;
    private VistaComercioInterno vista;
    private Jugador jugadorAceptante;
    private List<Recurso> recursos;
    private Map<String, Integer> recursosJugadorActual;
    private List<Jugador> jugadores;
    private int indice;

    public ComercioInternoControlador(Juego juego, ContenedorPrincipalVistas contenedor, VistaJuegoGeneral vistaJuego, List<Recurso> recursos, Map<String, Integer> recursosJugadorActual) {
        this.juego = juego;
        this.contenedor = contenedor;
        this.vistaJuego = vistaJuego;
        this.recursos = recursos;
        this.recursosJugadorActual = recursosJugadorActual;
        this.jugadores = new ArrayList<>(juego.getJugadores());
        this.jugadores.remove(juego.jugadorActual());
        this.indice = 0;

        recursosAEntregar = new ArrayList<>();
        recursosARecibir = new ArrayList<>();
    }

    public void obtenerRecursosAEntregar(Recurso recurso) {
        this.recursosAEntregar.add(recurso);
        vista.sumarContadorEntregar(recurso);
        vista.activarBotonEjecutar();
    }

    public void obtenerRecursosARecibir(Recurso recurso) {
        this.recursosARecibir.add(recurso);
        vista.sumarContadorRecibir(recurso);
        vista.activarBotonesAEntregar();

    }

    public void setVistaComercio(VistaComercioInterno vista) {
        this.vista = vista;
    }

    public void anunciarOferta() {
        this.jugadorAceptante = this.jugadores.get(indice);
        VistaOfertaInterno vistaOferta = new VistaOfertaInterno(this, juego.jugadorActual(), jugadorAceptante, recursosAEntregar, recursosARecibir);
        contenedor.setContenido(vistaOferta);
    }

    public void rechazarOferta() {
        if (indice == jugadores.size() - 1) {
            PopUpInformativo.mostrar("¡Nadie acepto tu oferta! :(");
            contenedor.setContenido(vistaJuego);
        } else {
            indice++;
            anunciarOferta();
        }
    }

    @Override
    public void ejecutar() {
        try {
            ComercioInterno comerciointerno = new ComercioInterno(juego.jugadorActual(), recursosAEntregar, recursosARecibir);
            juego.ejecutarComercioJugador(comerciointerno, jugadorAceptante);
            contenedor.setContenido(vistaJuego);
        } catch (RecursosInsuficientesError e) {
            PopUpError.mostrar(e.getMessage());
            VistaComercioInterno vistaNueva = new VistaComercioInterno(contenedor, this, recursos, vistaJuego, recursosJugadorActual);
            contenedor.setContenido(vistaNueva);
        }
    }
}
