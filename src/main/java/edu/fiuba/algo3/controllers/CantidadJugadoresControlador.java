package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaSeleccionCantidadJugadores;
import edu.fiuba.algo3.vistas.VistaSeleccionNombreYColor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class CantidadJugadoresControlador implements EventHandler<ActionEvent> {
    private Stage stage;
    private ContenedorPrincipalVistas contenedor;
    private final int cantidadJugadores;

    public CantidadJugadoresControlador(Stage stage, ContenedorPrincipalVistas contenedor, int cantidadJugadores){
        this.stage = stage;
        this.contenedor = contenedor;
        this.cantidadJugadores = cantidadJugadores;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (cantidadJugadores < 2 || cantidadJugadores > 4) {
            mostrarError("La cantidad de jugadores debe ser entre 2 y 4.");
            return;
        }
        VistaSeleccionNombreYColor vistaNombresYColor = new VistaSeleccionNombreYColor(stage, contenedor, cantidadJugadores);
        contenedor.setContenido(vistaNombresYColor);
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error!!");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
