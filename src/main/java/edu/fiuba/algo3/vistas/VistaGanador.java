package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.componentes.Brillos;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class VistaGanador extends BorderPane {
    public VistaGanador(Jugador jugadorActual, CambioTurnoControlador controlador) {
        this.setBackground(FondoPantalla.crearFondo("/images/ganador.gif"));
        StackPane cartaConBrillos = new StackPane();
        cartaConBrillos.setAlignment(Pos.CENTER);

        Label textoGanador = new Label("¡Ganaste " + jugadorActual.getNombre() + "!");
        textoGanador.getStyleClass().add("obtencion-bonificacion");

        HBox contenedorTop = new HBox(textoGanador);
        contenedorTop.setAlignment(Pos.CENTER);
        contenedorTop.setPadding(new Insets(60, 0, 20, 0));

        this.setTop(contenedorTop);

        VBox contenido = new VBox(20);
        contenido.setAlignment(Pos.CENTER);
        contenido.getChildren().addAll();

        cartaConBrillos.getChildren().addAll(Brillos.crearBrillos(), contenido);
        this.setCenter(cartaConBrillos);

        BotonJuego botonNuevaPartida = new BotonJuego("Nueva Partida");
        botonNuevaPartida.setOnAction(e -> controlador.iniciarNuevaPartida());

        HBox contenedorBoton = new HBox(botonNuevaPartida);
        contenedorBoton.setAlignment(Pos.CENTER);
        contenedorBoton.setPadding(new Insets(20, 0, 40, 0));

        this.setBottom(contenedorBoton);

        Transicion.fade(this);
    }
}
