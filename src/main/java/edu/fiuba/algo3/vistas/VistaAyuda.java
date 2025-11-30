package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.VolverControlador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

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

        Transicion.fade(this);
    }
}
