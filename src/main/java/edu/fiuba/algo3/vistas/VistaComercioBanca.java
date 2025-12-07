package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ComercioBancaControlador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import javafx.scene.layout.BorderPane;

public class VistaComercioBanca extends BorderPane {
    private ComercioBancaControlador controlador;

    public VistaComercioBanca(ComercioBancaControlador controlador) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        this.controlador = controlador;
    }

    public void activarEntrega() {
    }

    public void activarBotonEjecutar() {
    }
}
