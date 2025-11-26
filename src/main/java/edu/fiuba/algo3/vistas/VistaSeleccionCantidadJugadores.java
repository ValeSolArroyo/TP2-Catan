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
        panelCantidad.setPadding(new Insets(20, 20, 20, 20));

        Label pedidoCant = new Label("Ingrese la cantidad de jugadores");
        pedidoCant.getStyleClass().add("texto-ingrese");
        pedidoCant.setLayoutX(50);
        pedidoCant.setLayoutY(50);

        inputCantidad = new TextField();
        inputCantidad.setPromptText("¡Deben ser de 2 a 4!");
        inputCantidad.getStyleClass().add("input-cantidad");
        inputCantidad.setLayoutX(140);
        inputCantidad.setLayoutY(155);
        inputCantidad.setPrefWidth(300);
        inputCantidad.setPrefHeight(50);

        Button botonConfirmarCant = new Button("Confirmar");
        botonConfirmarCant.getStyleClass().add("boton-confirmar");
        botonConfirmarCant.setLayoutX(195);
        botonConfirmarCant.setLayoutY(290);
        botonConfirmarCant.setPrefWidth(200);
        botonConfirmarCant.setPrefHeight(60);
        botonConfirmarCant.setOnAction(new CantidadJugadoresControlador(stage, contenedor, this));

        panelCantidad.getChildren().addAll(pedidoCant, inputCantidad, botonConfirmarCant);
        this.getChildren().add(panelCantidad);

        FadeTransition transition = new FadeTransition(Duration.seconds(0.4), this);
        transition.setFromValue(0.4);
        transition.setToValue(1);
        transition.play();
    }

    public String getCantidadIngresada() {
        return inputCantidad.getText();
    }
}