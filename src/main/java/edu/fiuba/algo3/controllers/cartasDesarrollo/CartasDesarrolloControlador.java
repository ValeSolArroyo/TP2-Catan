package edu.fiuba.algo3.controllers.cartasDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.vistas.*;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaCaballero;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaProgresoConstruccion;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaProgresoDescubrimiento;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaProgresoMonopolio;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import javafx.stage.Stage;

public class CartasDesarrolloControlador {
    private Juego juego;
    private VistaTablero vistaTablero;
    private VistaJuegoGeneral vistaJuego;
    private ContenedorPrincipalVistas contenedor;

    public CartasDesarrolloControlador(Juego juego, VistaTablero vistaTablero, VistaJuegoGeneral vistaJuego, ContenedorPrincipalVistas contenedor) {
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.vistaJuego = vistaJuego;
        this.contenedor = contenedor;
    }

    public void jugarCaballero() {
        CaballeroControlador controlador = new CaballeroControlador(juego, vistaTablero, contenedor);
        VistaCaballero vista = new VistaCaballero(contenedor, juego, vistaTablero, controlador);
        contenedor.setContenido(vista);
    }

    public void jugarProgresoConstruccion() {
        ProgresoConstruccionControlador controlador = new ProgresoConstruccionControlador(juego, vistaTablero, vistaJuego, contenedor);
        VistaProgresoConstruccion vista = new VistaProgresoConstruccion(juego, vistaTablero, controlador);
        controlador.setVistaProgreso(vista);
        contenedor.setContenido(vista);
    }

    public void jugarProgresoDescubrimiento() {
        ProgresoDescubrimientoControlador controlador = new ProgresoDescubrimientoControlador(juego, vistaJuego, contenedor);
        VistaProgresoDescubrimiento vista = new VistaProgresoDescubrimiento(controlador);
        controlador.setVistaProgreso(vista);
        contenedor.setContenido(vista);

    }

    public void jugarMonopolio() {
        ProgresoMonopolioControlador controlador = new ProgresoMonopolioControlador(juego, vistaJuego, contenedor);
        VistaProgresoMonopolio vista = new VistaProgresoMonopolio(controlador);
        controlador.setVistaProgreso(vista);
        contenedor.setContenido(vista);
    }
}
