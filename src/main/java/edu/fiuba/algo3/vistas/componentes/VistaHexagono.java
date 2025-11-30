package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
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

    public VistaHexagono(Hexagono hexagono) {
        String terreno = hexagono.getTerreno();
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

        this.getChildren().add(view);
    }
}
