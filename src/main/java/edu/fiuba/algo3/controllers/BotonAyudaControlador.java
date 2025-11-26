package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaAyuda;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class BotonAyudaControlador implements EventHandler<ActionEvent> {
    private Stage stage;
    private ContenedorPrincipalVistas contenedor;

    public BotonAyudaControlador(Stage stage, ContenedorPrincipalVistas contenedor) {
        this.stage = stage;
        this.contenedor = contenedor;
    }

    @Override
    public void handle(ActionEvent event) {
        VistaAyuda vistaAyuda = new VistaAyuda(stage, contenedor);
        contenedor.setContenido(vistaAyuda);
    }
}
