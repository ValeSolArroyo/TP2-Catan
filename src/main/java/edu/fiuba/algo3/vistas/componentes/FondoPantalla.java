package edu.fiuba.algo3.vistas.componentes;


import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class FondoPantalla {
    public static Background crearFondo(String rutaImagen) {
        Image imagen = new Image(FondoPantalla.class.getResource(rutaImagen).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                imagen,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        return new Background(backgroundImage);
    }
}
