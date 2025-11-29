package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.VolverControlador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VistaAyuda extends StackPane{
    public VistaAyuda(ContenedorPrincipalVistas contenedor, Node vistaAnterior) {
        this.setAlignment(Pos.CENTER);
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/ayuda.jpg"));
        Button botonVolver = new Button("Volver");
        botonVolver.getStyleClass().add("boton-menu");
        botonVolver.setPrefWidth(130);
        StackPane.setAlignment(botonVolver, Pos.TOP_RIGHT);

        botonVolver.setOnAction(new VolverControlador(contenedor, vistaAnterior));

        this.getChildren().add(botonVolver);

        FadeTransition transition = new FadeTransition(Duration.seconds(0.4), this);
        transition.setFromValue(0.4);
        transition.setToValue(1);
        transition.play();
    }
}
