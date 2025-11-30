package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class InfoJugador extends HBox {
    private Jugador jugador;
    private Label nombreLabel;
    private Label puntosLabel;
    private Label recursosLabel;

    public InfoJugador(Jugador jugador) {
        this.jugador = jugador;

        this.setSpacing(5);
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: rgba(0,0,0,0.4); -fx-background-radius: 10;");

        nombreLabel = new Label(jugador.getNombre());
        puntosLabel = new Label("PV: " + jugador.getPuntosVictoria());
        recursosLabel = new Label("Recursos: " + jugador.getInventario().cantidadTotal());

        nombreLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
        puntosLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");
        recursosLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");

        this.getChildren().addAll(nombreLabel, puntosLabel, recursosLabel);
    }

    public void actualizar() {
        puntosLabel.setText("PV: " + jugador.conseguirPuntosDeVictoria());
        recursosLabel.setText("Recursos: " + jugador.getInventario().cantidadTotal());
    }
}
