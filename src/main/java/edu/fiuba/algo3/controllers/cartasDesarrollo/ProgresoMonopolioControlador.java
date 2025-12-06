package edu.fiuba.algo3.controllers.cartasDesarrollo;

import edu.fiuba.algo3.controllers.fasesJuego.AccionControlador;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.ProgresoMonopolio;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.VistaProgresoMonopolio;

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

    public void elegirRecurso(String tipo){
        if (tipo.equals("Madera")) {
            recursoElegido = new Madera();
        } else if (tipo.equals("Lana")) {
            recursoElegido = new Lana();
        } else if (tipo.equals("Grano")) {
            recursoElegido = new Grano();
        } else if (tipo.equals("Ladrillo")) {
            recursoElegido = new Ladrillo();
        } else if (tipo.equals("Mineral")) {
            recursoElegido = new Mineral();
        }

        vista.desactivarBotones();
        vista.activarBotonEjecutar();
    }

    @Override
    public void ejecutar() {
        Accion accion = new ProgresoMonopolio(juego, recursoElegido, juego.jugadorActual());
        juego.ejecutarAccion(accion);
        contenedor.setContenido(vistaJuego);
    }

    public java.util.List<String> getTiposDeRecurso() {
        return java.util.List.of("Madera", "Lana", "Grano", "Mineral", "Ladrillo");
    }
}