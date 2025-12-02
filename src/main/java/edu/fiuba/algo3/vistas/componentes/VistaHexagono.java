package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Map;

public class VistaHexagono extends StackPane {
    private static final Map<String, String> rutasImagenes = Map.of(
            "Bosque", "/images/utils/hexagono/bosque.png",
            "Colina", "/images/utils/hexagono/colina.png",
            "Pastizal", "/images/utils/hexagono/pasto.png",
            "Campo", "/images/utils/hexagono/campo.png",
            "Montaña", "/images/utils/hexagono/montaña.png"
    );
    private static final Map<String, Color> colores = Map.of(
            "Amarillo", Color.YELLOW,
            "Verde", Color.GREEN,
            "Azul", Color.BLUE,
            "Rosa", Color.PINK,
            "Rojo", Color.RED,
            "Naranja", Color.ORANGE
    );
    private static final double ANCHO = 90;
    private static final double ALTO  = 125;
    private List<Button> botonesVertices;
    private List<Button> botonesAristas;
    private Hexagono hexagono;

    public VistaHexagono(Hexagono hexagono) {
        botonesVertices = new java.util.ArrayList<>();
        botonesAristas = new java.util.ArrayList<>();
        this.hexagono = hexagono;

        String terreno = hexagono.getTerreno();
        int ficha = hexagono.getFicha();
        mostrarImagen(terreno, ficha);

        crearBotonesVertices();
        crearBotonesAristas();

        ocultarVertices();
        ocultarAristas();
    }

    private void mostrarImagen(String terreno, int ficha) {
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
    private void crearBotonesVertices() {
        double[][] vertices = {{0.0, -0.90}, {0.90, -0.45}, {0.90, 0.45},
            {0.0, 0.90}, {-0.90, 0.45}, {-0.90, -0.45}};

        for (int i = 0; i < hexagono.getVertices().size(); i++) {
            Button boton = new Button();

            boton.setPrefSize(12, 12);
            boton.getStyleClass().add("boton-vertice");

            StackPane.setAlignment(boton, Pos.CENTER);

            double x = vertices[i][0] * ANCHO / 2;
            double y = vertices[i][1] * ALTO / 2;

            boton.setTranslateX(x);
            boton.setTranslateY(y);

            botonesVertices.add(boton);
            this.getChildren().add(boton);
        }
    }

    private void crearBotonesAristas() {
        double[][] aristas = {{0.55, -0.70}, {0.90, 0.00}, {0.55, 0.70},
            {-0.55, 0.70}, {-0.90, 0.00}, {-0.55, -0.70}};

        double[] rotaciones = {135, 0, -135, 135, 0, -135};

        for (int i = 0; i < hexagono.getAristas().size(); i++) {
            Button boton = new Button();
            boton.setPrefWidth(2);
            boton.getStyleClass().add("boton-arista");

            StackPane.setAlignment(boton, Pos.CENTER);

            double x = aristas[i][0] * ANCHO / 2;
            double y = aristas[i][1] * ALTO / 2;

            boton.setTranslateX(x);
            boton.setTranslateY(y);

            boton.setRotate(rotaciones[i]);

            botonesAristas.add(boton);
            this.getChildren().add(boton);
        }
    }

    public List<Button> getBotonesVertices() {
        return botonesVertices;
    }

    public List<Button> getBotonesAristas() {
        return botonesAristas;
    }

    public Hexagono getHexagono() {
        return hexagono;
    }

    public void ocultarVertices() {
        botonesVertices.forEach(boton -> {
            boton.setOpacity(0);
            boton.setDisable(true);
        });
    }

    public void ocultarAristas() {
        botonesAristas.forEach(boton -> {
            boton.setOpacity(0);
            boton.setDisable(true);
        });
    }

    public void mostrarVertices() {
        botonesVertices.forEach(boton -> {
            boton.setOpacity(1);
            boton.setDisable(false);
        });
    }

    public void mostrarAristas() {
        botonesAristas.forEach(boton -> {
            boton.setOpacity(1);
            boton.setDisable(false);
        });
    }

    public boolean contieneVertice(Vertice vertice) {
        return hexagono.getVertices().contains(vertice);
    }

    public boolean contieneArista(Arista arista) {
        return hexagono.getAristas().contains(arista);
    }

    public void dibujarPobladoEn(Vertice vertice, String color) {
        Color colorDibujo = colores.get(color);
        int idx = hexagono.getVertices().indexOf(vertice);
        Button boton = botonesVertices.get(idx);

        Circle circulo = new Circle(10);
        circulo.setFill(colorDibujo);
        circulo.setStroke(Color.BLACK);

        circulo.setTranslateX(boton.getTranslateX());
        circulo.setTranslateY(boton.getTranslateY());

        this.getChildren().add(circulo);
        circulo.toFront();
    }

    public void dibujarCarreteraEn(Arista arista, String color) {
        Color colorDibujo = colores.get(color);
        int idx = hexagono.getAristas().indexOf(arista);
        Button boton = botonesAristas.get(idx);

        Rectangle carretera = new Rectangle(6, 30);
        carretera.setFill(colorDibujo);
        carretera.setStroke(Color.BLACK);

        carretera.setTranslateX(boton.getTranslateX());
        carretera.setTranslateY(boton.getTranslateY());

        carretera.setRotate(boton.getRotate());

        this.getChildren().add(carretera);
    }
}