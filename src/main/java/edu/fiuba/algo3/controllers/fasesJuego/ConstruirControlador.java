package edu.fiuba.algo3.controllers.fasesJuego;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.AccionConstruir;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.VistaConstruir;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;

public class ConstruirControlador implements AccionesTableroControlador{
    private VistaTablero vistaTablero;
    private Juego juego;
    private Construccion construccion;
    private EspacioConstruible espacio;
    private VistaConstruir vistaConstruir;

    public ConstruirControlador(VistaTablero vistaTablero, Juego juego, VistaConstruir vistaConstruir) {
        this.vistaTablero = vistaTablero;
        this.vistaTablero.setControlador(this);

        this.juego = juego;
        this.vistaConstruir = vistaConstruir;
    }

    @Override
    public void obtenerVertice(Vertice vertice) {
        this.espacio = vertice;
        vistaTablero.ocultarVertices();
    }

    @Override
    public void obtenerArista(Arista arista) {
        this.espacio = arista;
        vistaTablero.ocultarAristas();
    }

    @Override
    public void ejecutar() {
        AccionConstruir construir = new AccionConstruir(this.construccion, this.espacio, juego);
        try {
            juego.ejecutarAccion(construir);
        } catch (ConstruccionInvalidaError | YaHayCarreteraError |
                 YaHayPobladoError | YaHayCiudadError | RecursosInsuficientesError e) {
            PopUpError.mostrar(e.getMessage());
        }
    }

    public void construirPoblado() {
        this.construccion = new Poblado(this.juego.jugadorActual());
        vistaTablero.mostrarVertices();
        vistaConstruir.desactivarBotones();
    }

    public void construirCarretera() {
        this.construccion = new Carretera(this.juego.jugadorActual());
        vistaTablero.mostrarAristas();
        vistaConstruir.desactivarBotones();
    }

    public void construirCiudad() {
        this.construccion = new Ciudad(this.juego.jugadorActual());
        vistaTablero.mostrarVertices();
        vistaConstruir.desactivarBotones();
    }
}
