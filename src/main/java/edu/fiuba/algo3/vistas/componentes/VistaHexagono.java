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

    public void agregarBotonesConstruccion(ConstruccionControlador controlador, Jugador jugadorActual) {
        // Botones de vértices (6 vértices)
        for (int i = 0; i < 6; i++) {
            int verticeIndex = i;
            Button botonVertice = new Button();
            botonVertice.setStyle("-fx-background-color: transparent;");
            botonVertice.setPrefSize(15, 15);
            botonVertice.setTranslateX(calcularXVertice(i));
            botonVertice.setTranslateY(calcularYVertice(i));

            botonVertice.setOnAction(e -> {
                if (controlador.construirEnVertice(verticeIndex, jugadorActual)) {
                    Rectangle construccion = new Rectangle(15, 15, Color.web(jugadorActual.getColor()));
                    construccion.setArcWidth(5);
                    construccion.setArcHeight(5);
                    construccion.setTranslateX(botonVertice.getTranslateX());
                    construccion.setTranslateY(botonVertice.getTranslateY());
                    this.getChildren().add(construccion);
                    botonVertice.setDisable(true);
                }
            });
            this.getChildren().add(botonVertice);
        }

        // Botones de aristas (6 aristas)
        for (int i = 0; i < 6; i++) {
            int aristaIndex = i;
            Button botonArista = new Button();
            botonArista.setStyle("-fx-background-color: white;");
            botonArista.setPrefSize(40, 5);
            botonArista.setTranslateX(calcularXArista(i));
            botonArista.setTranslateY(calcularYArista(i));

            botonArista.setOnAction(e -> {
                if (controlador.construirEnArista(aristaIndex, jugadorActual)) {
                    Line carretera = new Line(0, 0, 40, 0);
                    carretera.setStroke(Color.web(jugadorActual.getColor()));
                    carretera.setStrokeWidth(4);
                    carretera.setTranslateX(botonArista.getTranslateX());
                    carretera.setTranslateY(botonArista.getTranslateY());
                    this.getChildren().add(carretera);
                    botonArista.setDisable(true);
                }
            });
            this.getChildren().add(botonArista);
        }
    }

    // --- Coordenadas de vértices (aproximadas, ajustar según tu imagen) ---
    private double calcularXVertice(int i) {
        double[] xs = {0, 35, 70, 70, 35, 0};
        return xs[i] - 35; // centrar en el hexágono
    }

    private double calcularYVertice(int i) {
        double[] ys = {0, 0, 25, 60, 85, 60};
        return ys[i] - 42.5;
    }

    // --- Coordenadas de aristas (aproximadas) ---
    private double calcularXArista(int i) {
        double[] xs = {17, 52, 70, 52, 17, 0};
        return xs[i] - 35;
    }

    private double calcularYArista(int i) {
        double[] ys = {0, 12, 42, 72, 85, 42};
        return ys[i] - 42.5;
    }
    
}
