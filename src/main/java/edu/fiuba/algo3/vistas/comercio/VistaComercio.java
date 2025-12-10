package edu.fiuba.algo3.vistas.comercio;

import edu.fiuba.algo3.controllers.comercio.ComercioControlador;
import edu.fiuba.algo3.controllers.VolverControlador;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.botones.BotonGenerico;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class VistaComercio extends BorderPane {
    private ComercioControlador controlador;

    public VistaComercio(ContenedorPrincipalVistas contenedor, ComercioControlador controlador, VistaJuegoGeneral vista) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        this.controlador = controlador;

        VBox botonesComercio = new VBox(60);
        botonesComercio.setAlignment(Pos.CENTER);
        botonesComercio.setPadding(new Insets(100, 20, 0, 0));

        BotonGenerico botonComerciarBanca = new BotonGenerico("Comerciar con Banca", "botones-derecha", 300, 80);
        botonComerciarBanca.setOnAction( e -> controlador.comerciarConBanca());

        BotonGenerico botonComerciarJugadores = new BotonGenerico("Comerciar con Jugadores", "botones-derecha", 320, 80);
        botonComerciarJugadores.setOnAction( e -> controlador.comerciarConJugadores());

        BotonGenerico botonComerciarPuerto = new BotonGenerico("Comerciar con Puerto", "botones-derecha", 300, 80);
        botonComerciarPuerto.setOnAction( e -> controlador.comerciarConPuertos());

        BotonJuego botonVolver = new BotonJuego("Volver");
        botonVolver.setOnAction(new VolverControlador(contenedor, vista));

        botonesComercio.getChildren().addAll(botonComerciarBanca, botonComerciarJugadores, botonComerciarPuerto, botonVolver);
        this.setCenter(botonesComercio);

    }
}
