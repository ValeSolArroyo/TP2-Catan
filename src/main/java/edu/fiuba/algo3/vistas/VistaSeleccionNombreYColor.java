package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ConfirmarJugadoresControlador;
import edu.fiuba.algo3.controllers.VolverControlador;
import edu.fiuba.algo3.vistas.componentes.EntradaJugador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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
        pedidoDatos.getStyleClass().add("texto-ingrese");
        pedidoDatos.setLayoutX(65);
        pedidoDatos.setLayoutY(50);

        VBox listaJugadores = new VBox(20);
        listaJugadores.setLayoutX(37);
        listaJugadores.setLayoutY(130);

        for (int i = 1; i <= cantidad; i++) {
            EntradaJugador entrada = new EntradaJugador(i, opcionesColores);
            nombres.add(entrada.getNombre());
            colores.add(entrada.getColor());
            listaJugadores.getChildren().add(entrada);
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
        botonVolver.setOnAction(new VolverControlador(contenedor, new VistaSeleccionCantidadJugadores(stage, contenedor)));

        panelIngresarJugadores.getChildren().addAll(pedidoDatos, listaJugadores, botonContinuar, botonVolver);
        this.getChildren().add(panelIngresarJugadores);

        Transicion.fade(this);
    }
}
