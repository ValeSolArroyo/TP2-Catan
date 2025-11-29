package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.BotonAyudaControlador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ContenedorPrincipalVistas extends StackPane {

    private Stage stage;
    private BorderPane contenedor;

    public ContenedorPrincipalVistas(Stage stage) {
        this.stage = stage;

        contenedor = new BorderPane();
        this.getChildren().add(contenedor);

        VBox menuGlobal = crearMenuGlobal();
        StackPane.setAlignment(menuGlobal, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(menuGlobal, new Insets(20));

        menuGlobal.setPickOnBounds(false);
        this.getChildren().add(menuGlobal);
    }

    public void setContenido(Node vista) {
        contenedor.setCenter(vista);
    }

    public Stage getStage() {
        return stage;
    }

    private VBox crearMenuGlobal() {
        Button botonMenu = new Button("Menu");
        botonMenu.getStyleClass().add("boton-menu");
        botonMenu.setTranslateY(50);
        botonMenu.setPrefWidth(100);
        botonMenu.setPrefHeight(25);

        HBox menuDesplegable = new HBox(10);
        menuDesplegable.setAlignment(Pos.CENTER_RIGHT);
        menuDesplegable.setVisible(false);

        Button botonMusica = new Button("Musica");
        botonMusica.getStyleClass().add("boton-menu");
        botonMusica.setPrefWidth(90);
        botonMusica.setPrefHeight(28);

        Button botonOpciones = new Button("Opciones");
        botonOpciones.getStyleClass().add("boton-menu");
        botonOpciones.setPrefWidth(90);
        botonOpciones.setPrefHeight(28);

        // TODO: Por ahora al apretar Volver en la vista Ayuda, vuelve directo al tablero:(
        // Esto nos va a traer problemas porque se puede poner Ayuda en la pantalla de inicio
        // y después Volver. Y ahí te salteás lo de elegir cant jugadores, los nombres, colores...
        // y vas directo al juego.
        Button botonAyuda = new Button("Ayuda");
        botonAyuda.getStyleClass().add("boton-menu");
        botonAyuda.setPrefWidth(90);
        botonAyuda.setOnAction(new BotonAyudaControlador(stage, this));

        Button botonAcerca = new Button("Acerca de");
        botonAcerca.getStyleClass().add("boton-menu");
        botonAcerca.setPrefWidth(100);
        botonAcerca.setOnAction(e -> {
            Alert popup = new Alert(Alert.AlertType.INFORMATION);
            popup.setTitle("Acerca de");
            popup.setHeaderText("Catán - Trabajo Práctico de Paradigmas de Programación");
            popup.setContentText("Facultad de Ingeniería de la UBA, Cátedra Suarez. 2C2025.\n\n" +
                    "Integrantes:\n★ Arias, Rafaela Pilar, 112272.\n★ Arroyo, Valentina Sol, 112294." +
                    "\n★ Baéz, Brenda Carolina, 112496.\n★ López Angelini, Chiara, 111225." +
                    "\n★ Marchesini, Carlota, 112099.");
            popup.showAndWait();
        });

        menuDesplegable.getChildren().addAll(botonMusica, botonOpciones, botonAyuda, botonAcerca);

        botonMenu.setOnAction(e -> {
            boolean estabaAbierto = menuDesplegable.isVisible();
            menuDesplegable.setVisible(!estabaAbierto);
            if (!estabaAbierto) {
                botonMenu.setTranslateY(-15);
            } else {
                botonMenu.setTranslateY(50);
            }
        });

        VBox contenidoMenu = new VBox(5, botonMenu, menuDesplegable);
        contenidoMenu.setAlignment(Pos.BOTTOM_RIGHT);

        return contenidoMenu;
    }
}
