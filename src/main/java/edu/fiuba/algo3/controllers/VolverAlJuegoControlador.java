package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class VolverAlJuegoControlador implements EventHandler<ActionEvent> {
    private Stage stage;
    private ContenedorPrincipalVistas contenedor;

    public VolverAlJuegoControlador(Stage stage, ContenedorPrincipalVistas contenedor) {
        this.stage = stage;
        this.contenedor = contenedor;
    }

    @Override
    public void handle(ActionEvent event) {
        VistaJuegoGeneral vista = new VistaJuegoGeneral(stage, contenedor);
        contenedor.setContenido(vista);
    }
}
