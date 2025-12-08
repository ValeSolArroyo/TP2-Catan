package edu.fiuba.algo3.vistas.componentes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ListadoRecurso extends VBox {
    private static final double ANCHO = 110;
    private static final double ALTO  = 145;
    public ListadoRecurso(String nombreRecurso, String rutaImagen) {
        super(10);
        this.setAlignment(Pos.CENTER);

        Label texto = new Label("Recurso: " + nombreRecurso);
        texto.getStyleClass().add("texto-recurso-hexagono");

        ImageView imagen = new ImageView(new Image(getClass().getResourceAsStream(rutaImagen)));
        imagen.setFitWidth(ANCHO);
        imagen.setFitHeight(ALTO);

        this.getChildren().addAll(texto, imagen);
    }
}
