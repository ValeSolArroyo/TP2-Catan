package edu.fiuba.algo3.vistas.cartasBonificacion;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.Brillos;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class VistaGranRutaComercial extends BorderPane {
    public VistaGranRutaComercial(CambioTurnoControlador controlador, Jugador jugadorActual, VistaJuegoGeneral vistaJuego) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        StackPane cartaConBrillos = new StackPane();
        cartaConBrillos.setAlignment(Pos.CENTER);

        VBox contenido = new VBox(20);
        contenido.setAlignment(Pos.CENTER);

        Label textoGanador = new Label("¡Enhorabuena " + jugadorActual.getNombre() + " obtuviste la carta de Gran Ruta Comercial!");
        textoGanador.getStyleClass().add("obtencion-bonificacion");

        Image imagenCarta = new Image(getClass().getResource("/images/utils/bonificacion/carretera.png").toExternalForm());

        ImageView imagenView = new ImageView(imagenCarta);
        imagenView.setFitWidth(250);
        imagenView.setPreserveRatio(true);

        Label textoExplicativo = new Label("Si un jugador consigue una ruta mas larga que tu,\nperderas esta valiosa carta que otorga dos Puntos de Victoria...");
        textoExplicativo.getStyleClass().add("obtencion-bonificacion");

        contenido.getChildren().addAll(textoGanador, imagenView, textoExplicativo);

        cartaConBrillos.getChildren().addAll(Brillos.crearBrillos(), contenido);

        this.setCenter(cartaConBrillos);

        BotonJuego botonContinuar = new BotonJuego("Continuar");
        botonContinuar.setOnAction(e -> controlador.continuarTurno());

        VBox botonVolverContenedor = new VBox(botonContinuar);
        botonVolverContenedor.setAlignment(Pos.CENTER);
        botonVolverContenedor.setPadding(new Insets(20));

        this.setBottom(botonVolverContenedor);

        Transicion.fade(this);
    }
}


