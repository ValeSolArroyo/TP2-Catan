package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.modelo.observer.Observador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VistaTurnoActual extends HBox implements Observador {
    private final Label texto;
    private final Rectangle fondo;
    private CambioTurnoControlador controlador;

    public VistaTurnoActual(CambioTurnoControlador controlador) {
        this.controlador = controlador;

        fondo = new Rectangle(200, 40);
        fondo.setFill(Color.LIGHTBLUE);
        fondo.setStroke(Color.BLACK);
        fondo.setStrokeWidth(4);
        fondo.setArcWidth(10);
        fondo.setArcHeight(10);

        texto = new Label("");
        texto.setPadding(new Insets(5));
        texto.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-font-family: 'Minecraft';");

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(fondo, texto);
        this.setSpacing(-200);

        controlador.agregarObservador(this);

        actualizar();
    }

    @Override
    public void actualizar() {
        texto.setText("Turno de " + controlador.getNombreJugadorActual());
        Color colorJugador = controlador.getColorJugadorActual();
        fondo.setStroke(colorJugador);
    }
}
