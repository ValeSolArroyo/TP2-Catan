package edu.fiuba.algo3.controllers.cartasDesarrollo;

import edu.fiuba.algo3.controllers.fasesJuego.AccionControlador;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.ProgresoDescubrimiento;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaProgresoDescubrimiento;

import java.util.ArrayList;
import java.util.List;

public class ProgresoDescubrimientoControlador implements AccionControlador {

    private Juego juego;
    private List<Recurso> recursosDeseados = new ArrayList<>();
    private VistaProgresoDescubrimiento vista;
    private VistaJuegoGeneral vistaJuego;
    private ContenedorPrincipalVistas contenedor;

    public ProgresoDescubrimientoControlador(Juego juego, VistaJuegoGeneral vistaJuego, ContenedorPrincipalVistas contenedor){
        this.juego = juego;
        this.vistaJuego = vistaJuego;
        this.contenedor = contenedor;
    }

    public void setVistaProgreso(VistaProgresoDescubrimiento vista) {
        this.vista = vista;

    }

    public void agregar(Recurso recurso) {
        if (recursosDeseados.size() == 1) {
            vista.desactivarBotones();
            vista.activarBotonEjecutar();
        }
        recursosDeseados.add(recurso);
        vista.sumarContador(recurso);
    }

    @Override
    public void ejecutar() {
        Accion accion = new ProgresoDescubrimiento(juego.jugadorActual(), recursosDeseados);
        juego.ejecutarAccion(accion);
        contenedor.setContenido(vistaJuego);
    }

    public List<Recurso> getTiposDeRecurso() {
        return List.of(new Madera(), new Ladrillo(), new Grano(), new Mineral(), new Lana());
    }
}
