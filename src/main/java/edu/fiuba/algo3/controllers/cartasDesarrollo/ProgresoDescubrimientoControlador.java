package edu.fiuba.algo3.controllers.cartasDesarrollo;

import edu.fiuba.algo3.controllers.fasesJuego.AccionControlador;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.ProgresoDescubrimiento;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.VistaProgresoDescubrimiento;

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

    public void agregarMadera() {
        if (recursosDeseados.size() == 1) {
            recursosDeseados.add(new Madera());
            vista.sumarContador("Madera");
            vista.desactivarBotones();
            vista.activarBotonEjecutar();
        }
        recursosDeseados.add(new Madera());
        vista.sumarContador("Madera");
    }

    public void agregarLana() {
        if (recursosDeseados.size() == 1) {
            recursosDeseados.add(new Lana());
            vista.sumarContador("Lana");
            vista.desactivarBotones();
            vista.activarBotonEjecutar();
            return;
        }
        recursosDeseados.add(new Lana());
        vista.sumarContador("Lana");

    }

    public void agregarGrano() {
        if (recursosDeseados.size() == 1) {
            recursosDeseados.add(new Grano());
            vista.sumarContador("Grano");
            vista.desactivarBotones();
            vista.activarBotonEjecutar();
            return;
        }
        recursosDeseados.add(new Grano());
        vista.sumarContador("Grano");
    }

    public void agregarMineral() {
        if (recursosDeseados.size() == 1) {
            recursosDeseados.add(new Mineral());
            vista.sumarContador("Mineral");
            vista.desactivarBotones();
            vista.activarBotonEjecutar();
            return;
        }
        recursosDeseados.add(new Mineral());
        vista.sumarContador("Mineral");
    }

    public void agregarLadrillo() {
        if (recursosDeseados.size() == 1) {
            recursosDeseados.add(new Ladrillo());
            vista.sumarContador("Ladrillo");
            vista.desactivarBotones();
            vista.activarBotonEjecutar();
        }
        recursosDeseados.add(new Ladrillo());
        vista.sumarContador("Ladrillo");

    }

    @Override
    public void ejecutar() {

        Accion accion = new ProgresoDescubrimiento(juego.jugadorActual(), recursosDeseados);
        juego.ejecutarAccion(accion);
        contenedor.setContenido(vistaJuego);

    }
}
