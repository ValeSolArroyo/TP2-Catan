package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;

public class VolverControlador implements EventHandler<ActionEvent> {
    private ContenedorPrincipalVistas contenedor;
    private Node vistaAnterior;

    public VolverControlador(ContenedorPrincipalVistas contenedor, Node vistaAnterior) {
        this.contenedor = contenedor;
        this.vistaAnterior = vistaAnterior;
    }

    @Override
    public void handle(ActionEvent event) {
        contenedor.setContenido(vistaAnterior);
    }
}
