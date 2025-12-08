package edu.fiuba.algo3.vistas.componentes;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Transicion {
    public static void fade(Node nodo){
        FadeTransition transition = new FadeTransition(Duration.seconds(0.4), nodo);
        transition.setFromValue(0.4);
        transition.setToValue(1);
        transition.play();
    }
}
