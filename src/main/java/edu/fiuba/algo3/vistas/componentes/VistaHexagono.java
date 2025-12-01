package edu.fiuba.algo3.vistas.componentes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
    private static final double ANCHO = 90;
    private static final double ALTO  = 125;

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

        crearBotonesVertices();
        crearBotonesAristas();
    }

    private void crearBotonesVertices() {
        double[][] vertices = {
            {0.0, -0.90},
            {0.90, -0.45},
            {0.90, 0.45},
            {0.0, 0.90},
            {-0.90, 0.45},
            {-0.90, -0.45}
        };

        for (int i = 0; i < 6; i++) {
            Button boton = new Button();

            boton.setPrefSize(10, 10);
            boton.setStyle("-fx-background-color: white; -fx-background-radius: 10px");

            StackPane.setAlignment(boton, Pos.CENTER);

            double x = vertices[i][0] * ANCHO / 2;
            double y = vertices[i][1] * ALTO / 2;

            boton.setTranslateX(x);
            boton.setTranslateY(y);

            boton.setOnAction(e -> {
                //TODO
            });

            this.getChildren().add(boton);
        }
    }

    private void crearBotonesAristas() {
        double[][] aristas = {
            {0.55, -0.70},
            {0.90, 0.00},
            {0.55, 0.70},
            {-0.55, 0.70},
            {-0.90, 0.00},
            {-0.55, -0.70}
        };

        double[] rotaciones = {
            135,
            0,
            -135,
            135,
            0,
            -135
        };

        for (int i = 0; i < 6; i++) {
            Button arista = new Button();
            arista.setPrefWidth(2);
            arista.setStyle("-fx-background-color: white; -fx-background-radius: 5px;");

            StackPane.setAlignment(arista, Pos.CENTER);

            double x = aristas[i][0] * ANCHO / 2;
            double y = aristas[i][1] * ALTO / 2;

            arista.setTranslateX(x);
            arista.setTranslateY(y);

            arista.setRotate(rotaciones[i]);

            arista.setOnAction(e -> {
                //TODO
            });

            this.getChildren().add(arista);
        }
    }
}