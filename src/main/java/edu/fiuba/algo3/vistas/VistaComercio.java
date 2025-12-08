package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ComercioControlador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VistaComercio extends BorderPane {
    private ComercioControlador controlador;

    public VistaComercio(ComercioControlador controlador) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        this.controlador = controlador;

        VBox botonesComercio = new VBox(60);
        botonesComercio.setAlignment(Pos.CENTER);
        botonesComercio.setPadding(new Insets(100, 20, 0, 0));

        BotonJuego botonComerciarBanca = new BotonJuego("Comerciar con Banca");
        botonComerciarBanca.setOnAction( e -> controlador.comerciarConBanca());
        botonComerciarBanca.setPrefWidth(200);

        BotonJuego botonComerciarJugadores = new BotonJuego("Comerciar con Jugadores");
        botonComerciarJugadores.setOnAction( e -> controlador.comerciarConJugadores());
        botonComerciarJugadores.setPrefWidth(200);

        BotonJuego botonComerciarPuerto = new BotonJuego("Comerciar con Puerto");
        botonComerciarPuerto.setOnAction( e -> controlador.comerciarConPuertos());
        botonComerciarPuerto.setPrefWidth(200);

        botonesComercio.getChildren().addAll(botonComerciarBanca, botonComerciarJugadores, botonComerciarPuerto);
        this.setCenter(botonesComercio);

    }
}
