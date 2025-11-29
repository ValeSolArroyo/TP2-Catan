package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CantidadJugadoresControlador;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VistaSeleccionCantidadJugadores extends VBox {
    private TextField inputCantidad;

    public VistaSeleccionCantidadJugadores(Stage stage, ContenedorPrincipalVistas contenedor) {
        this.setAlignment(Pos.CENTER);
        Image fondoMar = new Image(getClass().getResource("/images/backgrounds/mar.jpeg").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                fondoMar,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        this.setBackground(new Background(background));
        this.setSpacing(20);

        Pane panelCantidad = new Pane();
        panelCantidad.setPrefSize(App.ANCHO - 700, App.ALTO - 300);
        panelCantidad.setMaxSize(App.ANCHO - 700, App.ALTO - 300);
        panelCantidad.getStyleClass().add("panel-cantidad");
        panelCantidad.setPadding(new Insets(20));

        Label pedidoCant = new Label("Seleccione:");
        pedidoCant.getStyleClass().add("texto-ingrese");
        pedidoCant.setLayoutX(220);
        pedidoCant.setLayoutY(50);

        VBox opcionesBotones = new VBox(45);
        opcionesBotones.setAlignment(Pos.CENTER);
        opcionesBotones.setLayoutX(150);
        opcionesBotones.setLayoutY(120);

        for (int i = 2; i <= 4; i++) {
            Button botonCantidad = new Button("Jugar con " + i + " jugadores");
            botonCantidad.getStyleClass().add("boton-confirmar");
            botonCantidad.setOnAction(new CantidadJugadoresControlador(stage, contenedor, i));

            opcionesBotones.getChildren().add(botonCantidad);
        }
        panelCantidad.getChildren().addAll(pedidoCant, opcionesBotones);

        this.getChildren().add(panelCantidad);

        FadeTransition transition = new FadeTransition(Duration.seconds(0.4), this);
        transition.setFromValue(0.4);
        transition.setToValue(1);
        transition.play();
    }
}