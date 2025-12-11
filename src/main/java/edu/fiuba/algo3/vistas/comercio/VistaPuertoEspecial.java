package edu.fiuba.algo3.vistas.comercio;

import edu.fiuba.algo3.controllers.comercio.ComercioPuertoControlador;
import edu.fiuba.algo3.modelo.comercio.puertos.PuertoEspecial;
import edu.fiuba.algo3.modelo.recursos.Recurso;
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

import java.util.ArrayList;
import java.util.List;

public class VistaPuertoEspecial extends BorderPane {
    private ComercioPuertoControlador controlador;
    private BotonJuego botonEjecutar;
    private List<BotonGenerico> botonesPorRecurso;

    public VistaPuertoEspecial(ComercioPuertoControlador controlador, PuertoEspecial puerto) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        this.controlador = controlador;
        botonesPorRecurso = new ArrayList<>();

        inicializarRecursos();

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

        for (Recurso tipo : controlador.getTiposDeRecurso()) {
            String ruta = "/images/utils/recursos/" + tipo.getNombreRecurso() + ".png";

            ListadoRecurso recursos = new ListadoRecurso(tipo.getNombreRecurso(), ruta);

            BotonGenerico boton = new BotonGenerico("Pedir recurso", "botones-progreso", 230, 80);
            botonesPorRecurso.add(boton);
            boton.setOnAction(e -> controlador.conseguirRecursoDeseadoEspecial(tipo));

            VBox contenedor = new VBox(10, recursos, boton);
            contenedor.setAlignment(Pos.CENTER);
            hbox.getChildren().add(contenedor);
        }
        this.setCenter(hbox);
    }

    public void desactivarBotones() {
        for (BotonGenerico boton : botonesPorRecurso){
            boton.setDisable(true);
        }
    }

    public void activarBotonEjecutar() {
        botonEjecutar.setDisable(false);
        botonEjecutar.setOpacity(1);
    }
}
