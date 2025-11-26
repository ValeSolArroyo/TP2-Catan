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
    private VistaSeleccionCantidadJugadores vista;

    public CantidadJugadoresControlador(Stage stage, ContenedorPrincipalVistas contenedor, VistaSeleccionCantidadJugadores vista){
        this.stage = stage;
        this.contenedor = contenedor;
        this.vista = vista;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String texto = vista.getCantidadIngresada();

        int cantidad;
        try {
            cantidad = Integer.parseInt(texto);
        } catch (Exception excepcion) {
            mostrarError("Debe ingresar un número.");
            return;
        }

        if (cantidad < 2 || cantidad > 4) {
            mostrarError("La cantidad de jugadores debe ser entre 2 y 4.");
            return;
        }

        VistaSeleccionNombreYColor vistaNombresYColor = new VistaSeleccionNombreYColor(stage, contenedor, cantidad);
        contenedor.setContenido(vistaNombresYColor);
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error!!");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
