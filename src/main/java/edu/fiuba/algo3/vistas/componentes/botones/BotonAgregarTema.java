package edu.fiuba.algo3.vistas.componentes.botones;

import edu.fiuba.algo3.controllers.musica.Musica;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class BotonAgregarTema extends VBox {
    public BotonAgregarTema(String textoBoton, String textoDescripcion, String rutaMusica, Musica musica, int ancho, int alto) {
        super(5);
        this.setAlignment(Pos.CENTER);

        BotonGenerico boton = new BotonGenerico(textoBoton, "botones-musica", ancho, alto);
        boton.setOnAction(e -> musica.reproducir(rutaMusica));

        Label texto = new Label(textoDescripcion);
        texto.getStyleClass().add("texto-musica");

        this.getChildren().addAll(boton, texto);
    }
}
