package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.VolverControlador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import edu.fiuba.algo3.vistas.componentes.botones.BotonGenerico;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;

public class VistaAyuda extends StackPane{
    public VistaAyuda(ContenedorPrincipalVistas contenedor, Node vistaAnterior) {
        this.setAlignment(Pos.CENTER);
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/ayuda.jpg"));

        BotonGenerico botonVolver = new BotonGenerico("Volver", "boton-menu", 130);
        StackPane.setAlignment(botonVolver, Pos.TOP_RIGHT);
        botonVolver.setOnAction(new VolverControlador(contenedor, vistaAnterior));

        this.getChildren().add(botonVolver);

        Transicion.fade(this);
    }
}
