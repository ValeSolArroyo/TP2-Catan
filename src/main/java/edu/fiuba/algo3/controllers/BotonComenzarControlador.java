package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaSeleccionCantidadJugadores;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class BotonComenzarControlador implements EventHandler<ActionEvent> {
    private Stage stage;
    private ContenedorPrincipalVistas contenedor;

    public BotonComenzarControlador(Stage stage, ContenedorPrincipalVistas contenedor) {
        this.stage = stage;
        this.contenedor = contenedor;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        VistaSeleccionCantidadJugadores seleccion = new VistaSeleccionCantidadJugadores(stage, contenedor);
        contenedor.setContenido(seleccion);
    }
}
