package edu.fiuba.algo3.controllers.fasesJuego;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.juegoCommand.AccionConstruir;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaConstruir;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;
import javafx.scene.paint.Color;

import java.util.List;

public class ConstruirControlador implements AccionesTableroControlador{
    private VistaTablero vistaTablero;
    private Juego juego;
    private Construccion construccion;
    private EspacioConstruible espacio;
    private VistaConstruir vistaConstruir;
    private ContenedorPrincipalVistas contenedor;
    private VistaJuegoGeneral vistaJuego;

    public ConstruirControlador(VistaTablero vistaTablero, Juego juego, ContenedorPrincipalVistas contenedor, VistaJuegoGeneral vistaJuego) {
        this.vistaTablero = vistaTablero;
        this.vistaTablero.setControlador(this);

        this.juego = juego;
        this.contenedor = contenedor;
        this.vistaJuego = vistaJuego;
    }

    @Override
    public void obtenerVertice(Vertice vertice) {
        this.espacio = vertice;
        vistaTablero.ocultarVertices();
        vistaConstruir.habilitarBotonFinConstruccion();
        vistaConstruir.ocultarBotonCancelar();
    }

    @Override
    public void obtenerArista(Arista arista) {
        this.espacio = arista;
        vistaTablero.ocultarAristas();
        vistaConstruir.habilitarBotonFinConstruccion();
        vistaConstruir.ocultarBotonCancelar();
    }

    @Override
    public void ejecutar() {
        Jugador jugador = juego.jugadorActual();
        Color color = jugador.getColor();
        Accion accion = new AccionConstruir(this.construccion, this.espacio, juego);
        try {
            juego.ejecutarAccion(accion);
        } catch (ConstruccionInvalidaError | YaHayCarreteraError | YaHayPobladoError
                 | YaHayCiudadError | RecursosInsuficientesError | ReglaDeDistanciaError e) {
            PopUpError.mostrar(e.getMessage());
            vistaConstruir.desactivarBotonFinConstruccion();
            vistaConstruir.activarBotones();
            return;
        }

        if (construccion.getClass() == Poblado.class) {
            vistaTablero.dibujarPobladoEn(this.espacio, color);
        } else if (construccion.getClass() == Ciudad.class) {
            vistaTablero.dibujarCiudadEn( this.espacio, color);

        } else if (construccion.getClass() == Carretera.class) {
            vistaTablero.dibujarCarreteraEn(this.espacio, color);
        }
        cancelarConstruccion();
    }

    public void cancelarConstruccion() {
        vistaTablero.ocultarVertices();
        vistaTablero.ocultarAristas();

        vistaConstruir.desactivarBotonFinConstruccion();
        vistaConstruir.activarBotones();
    }

    public void construirPoblado() {
        this.construccion = new Poblado(this.juego.jugadorActual());
        vistaTablero.mostrarVerticesConstruccion();
        vistaConstruir.desactivarBotones();
    }

    public void construirCarretera() {
        this.construccion = new Carretera(this.juego.jugadorActual());
        vistaTablero.mostrarTodasLasAristas();
        vistaConstruir.desactivarBotones();
    }

    public void construirCiudad() {
        this.construccion = new Ciudad(this.juego.jugadorActual());
        vistaTablero.mostrarVerticesConstruccion();
        vistaConstruir.desactivarBotones();
    }

    public void setVistaConstruir(VistaConstruir vista) {
        this.vistaConstruir = vista;
    }
}
