package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Map;

public class InfoJugador extends VBox {
    private Jugador jugador;
    private Label nombreLabel;
    private Label puntosLabel;
    private Label recursosLabel;

    public InfoJugador(Jugador jugador) {
        this.jugador = jugador;

        this.setSpacing(5);
        this.setPadding(new Insets(10));

        nombreLabel = new Label(jugador.getNombre());
        puntosLabel = new Label("PV: " + jugador.getPuntosVictoria());
        recursosLabel = new Label(formatearRecursos());

        nombreLabel.getStyleClass().add("nombre-jugador");
        puntosLabel.getStyleClass().add("pv-jugador");
        recursosLabel.getStyleClass().add("recursos-jugador");

        this.getChildren().addAll(nombreLabel, puntosLabel, recursosLabel);
    }

    private String formatearRecursos() {
        Map<String, Integer> recursos = jugador.getRecursosInventario();

        return recursos.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .reduce((a, b) -> a + " | " + b)
                .orElse("");
    }

    public void actualizar() {
        puntosLabel.setText("PV: " + jugador.conseguirPuntosDeVictoria());
        recursosLabel.setText(formatearRecursos());
    }
}
