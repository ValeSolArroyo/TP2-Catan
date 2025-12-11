package edu.fiuba.algo3.vistas.cartasDesarrollo;

import edu.fiuba.algo3.controllers.cartasDesarrollo.ProgresoDescubrimientoControlador;
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

import java.util.HashMap;
import java.util.Map;

public class VistaProgresoDescubrimiento extends BorderPane {
    private Map<Recurso, BotonGenerico> botonesPorRecurso = new HashMap<>();
    private ProgresoDescubrimientoControlador controlador;
    private BotonJuego botonEjecutar;

    public VistaProgresoDescubrimiento(ProgresoDescubrimientoControlador controlador){
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        this.controlador = controlador;
        controlador.setVistaProgreso(this);

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

            BotonGenerico boton = new BotonGenerico("Seleccionar: 0", "botones-progreso", 230, 80);
            botonesPorRecurso.put(tipo, boton);
            boton.setOnAction(e -> controlador.agregar(tipo));

            VBox contenedor = new VBox(10, recursos, boton);
            contenedor.setAlignment(Pos.CENTER);
            hbox.getChildren().add(contenedor);
        }
        this.setCenter(hbox);
    }

    public void sumarContador(Recurso recurso){
        BotonGenerico boton = botonesPorRecurso.get(recurso);
        int valor = Integer.parseInt(boton.getText().replace("Seleccionar: ", "").trim());
        int nuevoValor = valor + 1;
        boton.setText("Seleccionar: " + nuevoValor);
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