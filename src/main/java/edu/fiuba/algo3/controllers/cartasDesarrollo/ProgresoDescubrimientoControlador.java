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

    private void agregar(Recurso recurso, String nombre) {
        if (recursosDeseados.size() == 1) {
            vista.desactivarBotones();
            vista.activarBotonEjecutar();
        }
        recursosDeseados.add(recurso);
        vista.sumarContador(nombre);
    }

    public void agregarRecurso(String tipo) {
        if (tipo.equals("Madera")) {
            agregar(new Madera(), "Madera");
        } else if (tipo.equals("Lana")) {
            agregar(new Lana(), "Lana");
        } else if (tipo.equals("Grano")) {
            agregar(new Grano(), "Grano");
        } else if (tipo.equals("Ladrillo")) {
            agregar(new Ladrillo(), "Ladrillo");
        } else if (tipo.equals("Mineral")) {
            agregar(new Mineral(), "Mineral");
        }
    }

    @Override
    public void ejecutar() {
        Accion accion = new ProgresoDescubrimiento(juego.jugadorActual(), recursosDeseados);
        juego.ejecutarAccion(accion);
        contenedor.setContenido(vistaJuego);
    }

    public List<String> getTiposDeRecurso() {
        return List.of("Madera", "Lana", "Grano", "Mineral", "Ladrillo");
    }
}
