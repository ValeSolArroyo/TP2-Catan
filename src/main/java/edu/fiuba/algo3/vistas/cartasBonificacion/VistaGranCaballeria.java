package edu.fiuba.algo3.vistas.cartasBonificacion;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.Brillos;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class VistaGranCaballeria extends BorderPane {

    public VistaGranCaballeria(Jugador jugadorActual, VistaJuegoGeneral vistaJuego) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        StackPane cartaConBrillos = new StackPane();
        cartaConBrillos.setAlignment(Pos.CENTER);

        VBox contenido = new VBox(20);
        contenido.setAlignment(Pos.CENTER);

        Label textoGanador = new Label("¡Enhorabuena " + jugadorActual.getNombre() + " obtuviste la carta de Gran Caballeria!");

        Image imagenCarta = new Image(getClass().getResource("/images/utils/bonificacion/caballero.png").toExternalForm());

        ImageView imagenView = new ImageView(imagenCarta);
        imagenView.setFitWidth(250);
        imagenView.setPreserveRatio(true);

        contenido.getChildren().addAll(textoGanador, imagenView);

        cartaConBrillos.getChildren().addAll(Brillos.crearBrillos(), contenido);

        this.setCenter(cartaConBrillos);
    }

}
