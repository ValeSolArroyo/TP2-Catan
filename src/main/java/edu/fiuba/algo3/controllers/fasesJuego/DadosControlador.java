package edu.fiuba.algo3.controllers.fasesJuego;

import edu.fiuba.algo3.controllers.cartasDesarrollo.CaballeroControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaCaballero;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import javafx.stage.Stage;

public class DadosControlador {
    private final Juego juego;
    private final Stage stage;
    private final ContenedorPrincipalVistas contenedor;
    private boolean dadosLanzados = false;
    private VistaTablero vistaTablero;
    private int resultado;

    public DadosControlador(Stage stage, Juego juego, ContenedorPrincipalVistas contenedor, VistaTablero vistaTablero) {
        this.juego = juego;
        this.stage = stage;
        this.contenedor = contenedor;
        this.vistaTablero = vistaTablero;
    }

    public void lanzarDados() {
        if (!dadosLanzados) {
             this.resultado = juego.lanzarDados();
            dadosLanzados = true;
        } else {
            if (this.resultado == 7) {
                CaballeroControlador caballeroControlador = new CaballeroControlador(stage, juego, this.vistaTablero, contenedor);
                VistaCaballero vistaCaballero = new VistaCaballero(stage, contenedor, juego, this.vistaTablero, caballeroControlador);
                contenedor.setContenido(vistaCaballero);
                caballeroControlador.elegirLugarLadron(vistaCaballero);
            } else {
                VistaJuegoGeneral vista = new VistaJuegoGeneral(stage, contenedor, juego, this.vistaTablero);
                contenedor.setContenido(vista);
            }
        }
    }

    public boolean seLanzaronDados() {
        return dadosLanzados;
    }
}
