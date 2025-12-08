package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaAyuda;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;

public class BotonAyudaControlador implements EventHandler<ActionEvent> {
    private ContenedorPrincipalVistas contenedor;

    public BotonAyudaControlador(ContenedorPrincipalVistas contenedor) {
        this.contenedor = contenedor;
    }

    @Override
    public void handle(ActionEvent event) {
        Node vistaActual = contenedor.getContenido();
        VistaAyuda vistaAyuda = new VistaAyuda(contenedor, vistaActual);
        contenedor.setContenido(vistaAyuda);
    }

}
