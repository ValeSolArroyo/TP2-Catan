package edu.fiuba.algo3.vistas.componentes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Map;

public class VistaHexagono extends StackPane {
    private static final Map<String, String> rutasImagenes = Map.of(
            "Bosque", "/images/utils/hexagono/bosque.png",
            "Colina", "/images/utils/hexagono/colina.png",
            "Pastizal", "/images/utils/hexagono/pasto.png",
            "Campo", "/images/utils/hexagono/campo.png",
            "Montaña", "/images/utils/hexagono/montaña.png"
    );

    public VistaHexagono(String terreno, int ficha) {
        String ruta;

        if (rutasImagenes.containsKey(terreno)) {
            ruta = rutasImagenes.get(terreno);
        } else {
            ruta = "/images/utils/hexagono/desierto.png";
        }

        Image img = new Image(getClass().getResource(ruta).toExternalForm());
        ImageView view = new ImageView(img);

        view.setFitWidth(90);
        view.setFitHeight(125);

        this.setAlignment(Pos.CENTER);
        this.getChildren().add(view);

        if (!terreno.equals("Desierto")) {
            Label fichaNumero = new Label(String.valueOf(ficha));
            fichaNumero.getStyleClass().add("ficha-numero");
            this.getChildren().add(fichaNumero);
        } else {
            // TODO: al mover el ladrón hay que ver cómo hacemos
            Image ladronImagen = new Image(getClass().getResource("/images/utils/ladron.png").toExternalForm());
            ImageView ladron = new ImageView(ladronImagen);
            ladron.setFitWidth(40);
            ladron.setPreserveRatio(true);

            this.getChildren().add(ladron);
        }
    }
}