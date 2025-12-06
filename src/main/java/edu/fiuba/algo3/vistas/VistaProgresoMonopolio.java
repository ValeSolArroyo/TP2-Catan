package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.cartasDesarrollo.ProgresoMonopolioControlador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.ListadoRecurso;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import edu.fiuba.algo3.vistas.componentes.botones.BotonGenerico;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class VistaProgresoMonopolio extends BorderPane {
    private Map<String, BotonGenerico> botonesPorRecurso = new HashMap<>();
    private ProgresoMonopolioControlador controlador;
    private BotonJuego botonEjecutar;

    public VistaProgresoMonopolio(ProgresoMonopolioControlador controlador){
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        this.controlador = controlador;
        controlador.setVistaProgreso(this);

        inicializarRecursos();

        // TODO: estaría buieno si podemos que se pueda poner volver, y que lo deje
        // volver a elegir. detalle igual
        botonEjecutar = new BotonJuego("Confirmar");
        botonEjecutar.setOnAction(e -> controlador.ejecutar());
        botonEjecutar.setDisable(true);
        botonEjecutar.setOpacity(0);

        VBox botonVbox = new VBox(botonEjecutar);
        botonVbox.setAlignment(Pos.CENTER);
        botonVbox.setSpacing(10);
        VBox.setMargin(botonEjecutar, new Insets(0, 0, 10, 0));
        this.setBottom(botonVbox);

        Transicion.fade(this);
    }

    private void inicializarRecursos() {
        HBox hbox = new HBox(40);
        hbox.setAlignment(Pos.CENTER);

        for (String tipo : controlador.getTiposDeRecurso()) {
            String ruta = "/images/utils/hexagonos/hexagono_" + tipo + ".png";

            ListadoRecurso recursos = new ListadoRecurso(tipo, ruta);

            BotonGenerico boton = new BotonGenerico("Pedir recurso", "botones-progreso", 230, 80);
            botonesPorRecurso.put(tipo, boton);
            boton.setOnAction(e -> controlador.elegirRecurso(tipo));

            VBox contenedor = new VBox(10, recursos, boton);
            contenedor.setAlignment(Pos.CENTER);
            hbox.getChildren().add(contenedor);
        }
        this.setCenter(hbox);
    }

    public void desactivarBotones() {
        for (BotonGenerico boton : botonesPorRecurso.values()){
            boton.setDisable(true);
            boton.setOpacity(0);
        }
    }

    public void activarBotonEjecutar() {
        botonEjecutar.setDisable(false);
        botonEjecutar.setOpacity(1);
    }
}
