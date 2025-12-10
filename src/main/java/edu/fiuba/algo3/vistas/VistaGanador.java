package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.componentes.Brillos;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class VistaGanador extends BorderPane {
    public VistaGanador(Jugador jugadorActual) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        StackPane cartaConBrillos = new StackPane();
        cartaConBrillos.setAlignment(Pos.CENTER);

        VBox contenido = new VBox(20);
        contenido.setAlignment(Pos.CENTER);

        Label textoGanador = new Label("¡Ganaste " + jugadorActual.getNombre() + "!");
        Label PVGanador = new Label("Cantidad de PV obtenidos: " + jugadorActual.conseguirPuntosDeVictoriaTotales());

        contenido.getChildren().addAll(textoGanador, PVGanador);

        cartaConBrillos.getChildren().addAll(Brillos.crearBrillos(), contenido);
        this.setCenter(cartaConBrillos);
    }
}
