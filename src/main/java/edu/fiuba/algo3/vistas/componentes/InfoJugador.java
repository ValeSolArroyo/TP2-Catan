package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.Map;
import edu.fiuba.algo3.modelo.observer.Observador;
import javafx.scene.paint.Color;

public class InfoJugador extends VBox implements Observador {
    private Jugador jugador;
    private Label nombreLabel;
    private Label puntosLabel;
    private Label recursosLabel;

    public InfoJugador(Jugador jugador) {
        this.jugador = jugador;
        jugador.agregarObservador(this);

        this.setPadding(new Insets(0, 20, 10, 20));
        this.getStyleClass().add("info-jugador");

        Color colorJugador = jugador.getColor();
        this.setBorder(new Border(new BorderStroke(colorJugador, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));

        nombreLabel = new Label(jugador.getNombre());
        puntosLabel = new Label("PV: " + jugador.getPuntosVictoria());
        Label recursosTituloLabel = new Label("Recursos:");
        recursosLabel = new Label(formatearRecursos());

        nombreLabel.getStyleClass().add("nombre-jugador");
        puntosLabel.getStyleClass().add("pv-jugador");
        recursosTituloLabel.getStyleClass().add("titulo-recursos-jugador");
        recursosLabel.getStyleClass().add("recursos-jugador");

        HBox filaNombreYPV = new HBox();
        filaNombreYPV.setSpacing(60);
        filaNombreYPV.getChildren().addAll(nombreLabel, puntosLabel);

        this.getChildren().addAll(filaNombreYPV, recursosTituloLabel, recursosLabel);

        actualizar();
    }

    private String formatearRecursos() {
        Map<String, Integer> recursos = jugador.getRecursosInventario();
        String resultado = "";
        int contador = 0;

        for (String nombre : recursos.keySet()) {
            int cantidad = recursos.get(nombre);
            resultado += nombre + ": " + cantidad;
            contador++;

            if (contador % 3 == 0) {
                resultado += "\n";
            } else if (contador < recursos.size()) {
                resultado += ", ";
            }
        }

        return resultado;
    }

    public void actualizar() {
        puntosLabel.setText("PV: " + jugador.conseguirPuntosDeVictoria());
        recursosLabel.setText(formatearRecursos());
    }
}
