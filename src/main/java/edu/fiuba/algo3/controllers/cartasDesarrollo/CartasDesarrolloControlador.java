package edu.fiuba.algo3.controllers.cartasDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaCaballero;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import javafx.stage.Stage;

public class CartasDesarrolloControlador {
    
    private Juego juego;
    private VistaTablero vistaTablero;
    private VistaJuegoGeneral vistaJuego;
    private ContenedorPrincipalVistas contenedor;
    private Stage stage;
    
    public CartasDesarrolloControlador(Juego juego, VistaTablero vistaTablero, VistaJuegoGeneral vistaJuego, ContenedorPrincipalVistas contenedor, Stage stage){
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.vistaJuego = vistaJuego;
        this.contenedor = contenedor;
        this.stage = stage;
    }

    public void jugarCaballero() {
        CaballeroControlador controlador = new CaballeroControlador(juego, vistaTablero, vistaJuego, contenedor);
        VistaCaballero vista = new VistaCaballero(stage, contenedor, juego, vistaTablero, controlador);
        contenedor.setContenido(vista);
    }

    public void jugarProgresoConstruccion() {
    }

    public void jugarProgresoDescubrimiento() {
    }

    public void jugarMonopolio() {
    }
}
