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

        Point2D punto = botonVertice.localToScene(0, 0);

        Point2D destino = this.sceneToLocal(punto);

        poblado.setLayoutX(destino.getX() + botonVertice.getWidth() / 2);
        poblado.setLayoutY(destino.getY() + botonVertice.getHeight() / 2);

        this.getChildren().add(poblado);
    }

    public void dibujarCarretera(Button botonArista, Color color) {

        Rectangle carretera = new Rectangle(6, 40);
        carretera.setFill(color);
        carretera.setStroke(Color.BLACK);
        carretera.setStrokeWidth(1.5);

        javafx.geometry.Point2D punto = botonArista.localToScene(0, 0);

        Point2D destino = this.sceneToLocal(punto);

        carretera.setLayoutX(destino.getX() + botonArista.getWidth() / 2);
        carretera.setLayoutY(destino.getY() + botonArista.getHeight() / 2);

        carretera.setRotate(botonArista.getRotate());

        this.getChildren().add(carretera);
    }
}


