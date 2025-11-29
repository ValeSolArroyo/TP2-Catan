package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ConfirmarJugadoresControlador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class VistaSeleccionNombreYColor extends VBox {
    private final List<TextField> nombres = new ArrayList<>();
    private final List<ComboBox<String>> colores = new ArrayList<>();
    private static final String[] opcionesColores = {"Amarillo", "Verde", "Azul", "Rosa", "Rojo", "Naranja"};

    public VistaSeleccionNombreYColor(Stage stage, ContenedorPrincipalVistas contenedor, int cantidad) {
        this.setAlignment(Pos.CENTER);
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        this.setSpacing(20);

        Pane panelIngresarJugadores = new Pane();
        panelIngresarJugadores.setPrefSize(App.ANCHO - 700, App.ALTO - 250);
        panelIngresarJugadores.setMaxSize(App.ANCHO - 700, App.ALTO - 250);
        panelIngresarJugadores.getStyleClass().add("panel-cantidad");

        Label pedidoDatos = new Label("Ingrese los nombres y colores:");
        pedidoDatos.setLayoutX(65);
        pedidoDatos.setLayoutY(50);
        pedidoDatos.getStyleClass().add("texto-ingrese");

        VBox listaJugadores = new VBox(20);
        listaJugadores.setLayoutX(37);
        listaJugadores.setLayoutY(130);

        for (int i = 1; i <= cantidad; i++) {
            HBox fila = new HBox(15);

            Label textoJugador = new Label("Jugador " + i + ":");
            textoJugador.getStyleClass().add("texto-jugador");

            TextField nombreJugador = new TextField();
            nombreJugador.setPromptText("Nombre del jugador " + i);
            nombreJugador.getStyleClass().add("input-nombre-jugador");
            nombreJugador.setPrefWidth(220);

            ComboBox<String> selectorColor = new ComboBox<>();
            selectorColor.getItems().addAll(opcionesColores);
            selectorColor.getStyleClass().add("selector-color");
            selectorColor.getSelectionModel().select(opcionesColores[i - 1]);
            selectorColor.setPrefWidth(128);
            selectorColor.setPrefHeight(40);

            nombres.add(nombreJugador);
            colores.add(selectorColor);

            fila.getChildren().addAll(textoJugador, nombreJugador, selectorColor);
            listaJugadores.getChildren().add(fila);
        }

        Button botonContinuar = new Button("Continuar");
        botonContinuar.setLayoutX(200);
        botonContinuar.setLayoutY(390);
        botonContinuar.setPrefWidth(200);
        botonContinuar.setPrefHeight(60);
        botonContinuar.getStyleClass().add("boton-confirmar");
        botonContinuar.setOnAction(new ConfirmarJugadoresControlador(stage, contenedor, nombres, colores));

        Button botonVolver = new Button("Volver");
        botonVolver.setLayoutX(487);
        botonVolver.setLayoutY(7);
        botonVolver.setPrefWidth(85);
        botonVolver.setPrefHeight(40);
        botonVolver.getStyleClass().add("boton-volver-atras");
        botonVolver.setOnAction(e -> contenedor.setContenido(new VistaSeleccionCantidadJugadores(stage, contenedor)));

        panelIngresarJugadores.getChildren().addAll(pedidoDatos, listaJugadores, botonContinuar, botonVolver);
        this.getChildren().add(panelIngresarJugadores);

        FadeTransition transition = new FadeTransition(Duration.seconds(0.4), panelIngresarJugadores);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();
    }
}
