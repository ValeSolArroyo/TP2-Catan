package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.controllers.fasesJuego.ConstruirControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaConstruir;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import javafx.stage.Stage;

public class JuegoControlador {
    private Stage stage;
    private ContenedorPrincipalVistas contenedor;
    private Juego juego;
    private VistaTablero vistaTablero;
    private VistaJuegoGeneral vistaJuego;

    public JuegoControlador(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego, VistaTablero vistaTablero, VistaJuegoGeneral vistaJuego) {
        this.stage = stage;
        this.contenedor = contenedor;
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.vistaJuego = vistaJuego;
    }

    public void construir() {
        VistaConstruir vista = new VistaConstruir(this.stage, this.contenedor, this.juego, this.vistaTablero);
        contenedor.setContenido(vista);
    }

    public void comerciar() {
        // Que aparezcan 3 botones:
            // Comerciar con jugadores
            // Comerciar con banca
            // Comerciar con puertos
        // --> SE TIENEN QUE VER LOS PUERTOS Y DEBEN TENER UN TEXTO CORRESPONDIENTE
        // TIPO 3:1, 2:1...

        //ControladorComercio cc = new ControladorComercio(juego, this);
        //VistaComercio vista = new VistaComercio(cc);
        //contenedor.setContenido(vista);
    }

    public void comprar() {
        ComprarCartaControlador controlador = new ComprarCartaControlador(juego, vistaJuego, contenedor);
        controlador.ejecutar();
    }
}
