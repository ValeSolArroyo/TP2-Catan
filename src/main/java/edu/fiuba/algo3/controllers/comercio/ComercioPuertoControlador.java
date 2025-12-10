package edu.fiuba.algo3.controllers.comercio;

import edu.fiuba.algo3.controllers.fasesJuego.AccionControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.comercio.VistaComercioPuerto;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;

import java.util.List;

public class ComercioPuertoControlador implements AccionControlador {
    private Juego juego;
    private ContenedorPrincipalVistas contenedor;
    private VistaJuegoGeneral vistaJuego;
    private VistaComercioPuerto vistaComercio;
    private VistaTablero vistaTablero;

    public ComercioPuertoControlador(Juego juego, ContenedorPrincipalVistas contenedor, VistaJuegoGeneral vistaJuego, VistaTablero vistaTablero) {
        this.juego = juego;
        this.contenedor = contenedor;
        this.vistaJuego = vistaJuego;
        this.vistaTablero = vistaTablero;
    }

    public void setVistaComercio(VistaComercioPuerto vistaComercio) {
        this.vistaComercio = vistaComercio;
    }


    public void comerciar(Vertice verticeSeleccionado, List<Recurso> recursosEntregados, Recurso recursoDeseado) {
    }

    @Override
    public void ejecutar() {
    }
}
