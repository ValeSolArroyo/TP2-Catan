package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class VistaConstrucciones extends Pane {
    private final Map<EspacioConstruible, Node> construcciones = new HashMap<>();
    public VistaConstrucciones() {
        setPickOnBounds(false);
    }

    public void dibujarPoblado(Button botonVertice, Color color, EspacioConstruible vertice) {
        Circle poblado = new Circle(12);
        poblado.setFill(color);
        poblado.setStroke(Color.BLACK);
        poblado.setStrokeWidth(2);

        Point2D puntoEscena = botonVertice.localToScene(botonVertice.getWidth() / 2, botonVertice.getHeight() / 2);
        Point2D destino = this.sceneToLocal(puntoEscena);

        poblado.setLayoutX(destino.getX());
        poblado.setLayoutY(destino.getY());

        this.getChildren().add(poblado);
        construcciones.put(vertice, poblado);
    }

    public void dibujarCarretera(Button botonArista, Color color) {
        Rectangle carretera = new Rectangle(8, 35);
        carretera.setFill(color);
        carretera.setStroke(Color.BLACK);
        carretera.setStrokeWidth(2);

        Point2D centroBotonEnEscena = botonArista.localToScene(
                botonArista.getWidth() / 2,
                botonArista.getHeight() / 2
        );

        Point2D centroCarreteraEnConstrucciones = this.sceneToLocal(centroBotonEnEscena);
        carretera.setX(centroCarreteraEnConstrucciones.getX() - carretera.getWidth() / 2);
        carretera.setY(centroCarreteraEnConstrucciones.getY() - carretera.getHeight() / 2);

        carretera.setRotate(botonArista.getRotate());

        this.getChildren().add(carretera);
        carretera.toBack();
    }

    public void dibujarCiudad(Button botonVertice, Color color, EspacioConstruible vertice) {
        Node previo = construcciones.get(vertice);
        if (previo != null) {
            previo.setVisible(false);
        }

        Polygon ciudad = new Polygon();
        ciudad.getPoints().addAll(0.0, -15.0, -12.0, 10.0, 12.0, 10.0);

        ciudad.setFill(color);
        ciudad.setStroke(Color.BLACK);
        ciudad.setStrokeWidth(2);

        Point2D puntoEscena = botonVertice.localToScene(
                botonVertice.getWidth() / 2,
                botonVertice.getHeight() / 2
        );

        Point2D destino = this.sceneToLocal(puntoEscena);

        ciudad.setLayoutX(destino.getX());
        ciudad.setLayoutY(destino.getY());

        this.getChildren().add(ciudad);
    }

}


