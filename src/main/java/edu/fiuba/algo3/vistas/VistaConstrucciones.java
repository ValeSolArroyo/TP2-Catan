package edu.fiuba.algo3.vistas;

import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class VistaConstrucciones extends Pane {
    public VistaConstrucciones() {
        setPickOnBounds(false);
    }

    public void dibujarPoblado(Button botonVertice, Color color) {
        Circle poblado = new Circle(12);
        poblado.setFill(color);
        poblado.setStroke(Color.BLACK);
        poblado.setStrokeWidth(2);

        Point2D puntoEscena = botonVertice.localToScene(botonVertice.getWidth() / 2, botonVertice.getHeight() / 2);
        Point2D destino = this.sceneToLocal(puntoEscena);

        poblado.setLayoutX(destino.getX());
        poblado.setLayoutY(destino.getY());

        this.getChildren().add(poblado);
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
    //public void dibujarCiudad(Button botonVertice, Color color)
    // que sea un triángulo
}


