package edu.fiuba.algo3.controllers.comercio;

import edu.fiuba.algo3.controllers.fasesJuego.AccionControlador;
import edu.fiuba.algo3.controllers.fasesJuego.AccionesTableroControlador;
import edu.fiuba.algo3.modelo.comercio.puertos.ComercioPuerto;
import edu.fiuba.algo3.modelo.comercio.puertos.PuertoEspecial;
import edu.fiuba.algo3.modelo.excepciones.ComercioInvalidoError;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.comercio.VistaComercioPuerto;
import edu.fiuba.algo3.vistas.comercio.VistaPuertoEspecial;
import edu.fiuba.algo3.vistas.comercio.VistaPuertoGenerico;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComercioPuertoControlador implements AccionesTableroControlador {
    private Juego juego;
    private ContenedorPrincipalVistas contenedor;
    private VistaJuegoGeneral vistaJuego;
    private VistaComercioPuerto vistaComercio;
    private VistaTablero vistaTablero;
    private Vertice vertice;
    private Recurso recursoDeseado;
    private VistaPuertoEspecial vistaEspecial;
    private List<Recurso> listaRecursosAEntregar;
    private VistaPuertoGenerico vistaGenerico;

    public ComercioPuertoControlador(Juego juego, ContenedorPrincipalVistas contenedor, VistaJuegoGeneral vistaJuego, VistaTablero vistaTablero) {
        this.juego = juego;
        this.contenedor = contenedor;
        this.vistaJuego = vistaJuego;
        this.vistaTablero = vistaTablero;
        listaRecursosAEntregar = new ArrayList<>();
    }

    public void setVistaComercio(VistaComercioPuerto vistaComercio) {
        this.vistaComercio = vistaComercio;
    }

    @Override
    public void ejecutar() {
        try {
            juego.ejecutarComercioPuerto(vertice, listaRecursosAEntregar, recursoDeseado);
        } catch (ComercioInvalidoError | RecursosInsuficientesError e) {
            PopUpError.mostrar(e.getMessage());
            contenedor.setContenido(vistaComercio);
            vistaComercio.desactivarBotonConfirmar();
            vistaComercio.activarBotonElegir();
            listaRecursosAEntregar.clear();
            return;
        }
        VistaJuegoGeneral nuevaVistaJuego = new VistaJuegoGeneral(contenedor, juego, this.vistaTablero);
        contenedor.setContenido(nuevaVistaJuego);
    }

    public void elegirPuerto() {
        vistaTablero.mostrarVerticesPuerto();
        vistaTablero.setControlador(this);
        vistaComercio.desactivarBotonElegir();
    }

    public void mostrarTextoPuerto(Vertice vertice) {
        ComercioPuerto puerto = vertice.getPuerto();
        if (puerto.getClass() ==  PuertoEspecial.class) {
            Recurso recurso = ((PuertoEspecial) puerto).getTipoRecurso();
            this.vistaComercio.actualizarTexto("Puerto Especial,\nrecurso " + recurso.getNombreRecurso());
        } else {
            this.vistaComercio.actualizarTexto("Puerto Genérico");
        }
    }

    @Override
    public void obtenerVertice(Vertice vertice) {
        mostrarTextoPuerto(vertice);
        this.vertice = vertice;
        vistaComercio.activarBotonConfirmar();
    }

    @Override
    public void obtenerArista(Arista arista) {}

    public void confirmarPuerto() {
        ComercioPuerto puerto = vertice.getPuerto();
        vistaTablero.ocultarVertices();
        if (puerto.getClass() == PuertoEspecial.class) {
            Recurso recurso = ((PuertoEspecial) puerto).getTipoRecurso();
            this.vistaEspecial = new VistaPuertoEspecial(this, (PuertoEspecial) puerto);
            this.listaRecursosAEntregar.add(recurso);
            this.listaRecursosAEntregar.add(recurso);
            contenedor.setContenido(vistaEspecial);
        } else {
            Jugador jugadorActual = juego.jugadorActual();
            Map<String, Integer> recursos = jugadorActual.getRecursosInventario();
            this.vistaGenerico = new VistaPuertoGenerico(this, recursos, vistaJuego);
            contenedor.setContenido(vistaGenerico);
        }
    }

    public void conseguirRecursoDeseadoEspecial(Recurso recurso) {
        this.recursoDeseado = recurso;
        this.vistaEspecial.desactivarBotones();
        this.vistaEspecial.activarBotonEjecutar();
    }

    public void conseguirRecursoDeseadoGenerico(Recurso recurso) {
        this.recursoDeseado = recurso;
        this.vistaGenerico.desactivarBotonesPuerto();
        this.vistaGenerico.activarBotonesElegir();
    }

    public void obtenerRecursoAEntregar(Recurso recurso) {
        this.listaRecursosAEntregar.add(recurso);
        this.listaRecursosAEntregar.add(recurso);
        this.listaRecursosAEntregar.add(recurso);
        this.vistaGenerico.activarBotonEjecutar();
    }

    public List<Recurso> getTiposDeRecurso() {
        return List.of(new Madera(), new Lana(), new Grano(), new Mineral(), new Ladrillo());
    }
}
