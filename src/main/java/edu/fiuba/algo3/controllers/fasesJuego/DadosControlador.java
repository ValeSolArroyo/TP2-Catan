package edu.fiuba.algo3.controllers.fasesJuego;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import javafx.stage.Stage;

public class DadosControlador {
    private final Juego juego;
    private final Stage stage;
    private final ContenedorPrincipalVistas contenedor;
    private boolean dadosLanzados = false;

    public DadosControlador(Stage stage, Juego juego, ContenedorPrincipalVistas contenedor) {
        this.juego = juego;
        this.stage = stage;
        this.contenedor = contenedor;
    }

    public void lanzarDados() {
        if (!dadosLanzados) {
            juego.lanzarDados();
            dadosLanzados = true;
        } else {
            VistaJuegoGeneral vista = new VistaJuegoGeneral(stage, contenedor, juego);
            contenedor.setContenido(vista);
        }
    }

    public boolean seLanzaronDados() {
        return dadosLanzados;
    }
}
