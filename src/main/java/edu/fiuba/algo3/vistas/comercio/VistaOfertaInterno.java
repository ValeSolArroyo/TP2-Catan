package edu.fiuba.algo3.vistas.comercio;

import edu.fiuba.algo3.controllers.comercio.ComercioInternoControlador;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.ListadoRecurso;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaOfertaInterno extends BorderPane {
    private ComercioInternoControlador controlador;
    private Jugador jugadorActual;
    private Map<Recurso, Integer> recursosOfertados;
    private Map<Recurso, Integer> recursosPedidos;
    private Jugador jugadorAceptante;

    public VistaOfertaInterno(ComercioInternoControlador controlador, Jugador jugadorActual, Jugador jugadorAceptante, List<Recurso> recursosAEntregar, List<Recurso> recursosARecibir) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        this.controlador = controlador;
        this.jugadorActual = jugadorActual;
        this.recursosOfertados = new HashMap<>();
        this.recursosPedidos = new HashMap<>();
        this.jugadorAceptante = jugadorAceptante;

        Label titulo = new Label("Intercambio con otro jugador: el jugador " + jugadorActual.getNombre() + " ha propuesto:");


        for (Recurso recurso : recursosAEntregar) {
            recursosOfertados.put(recurso, recursosOfertados.getOrDefault(recurso, 0) + 1);
        }

        VBox columnaIzquierda = new VBox(10);
        columnaIzquierda.setAlignment(Pos.CENTER);
        Label tituloIzq = new Label("Entrega:");
        columnaIzquierda.getChildren().add(tituloIzq);

        for (Recurso recurso : recursosOfertados.keySet()) {
            String ruta = "/images/utils/recursos/" + recurso.getNombreRecurso() + ".png";
            int cantidad = recursosOfertados.get(recurso);
            ListadoRecurso listadoRecurso = new ListadoRecurso(recurso.getNombreRecurso() + " x" + cantidad, ruta);
            columnaIzquierda.getChildren().add(listadoRecurso);
        }

        for (Recurso recurso : recursosARecibir) {
            recursosPedidos.put(recurso, recursosPedidos.getOrDefault(recurso, 0) + 1);
        }

        VBox columnaDerecha = new VBox(10);
        columnaDerecha.setAlignment(Pos.CENTER);
        Label tituloDer = new Label("Pide:");
        columnaDerecha.getChildren().add(tituloDer);

        for (Recurso recurso : recursosPedidos.keySet()) {
            String ruta = "/images/utils/recursos/" + recurso.getNombreRecurso() + ".png";
            int cantidad = recursosPedidos.get(recurso);
            ListadoRecurso listadoRecurso = new ListadoRecurso(recurso.getNombreRecurso() + " x" + cantidad, ruta);
            columnaDerecha.getChildren().add(listadoRecurso);
        }

        HBox oferta = new HBox(80, columnaIzquierda, columnaDerecha);
        oferta.setAlignment(Pos.CENTER);

        Label textoPregunta = new Label("¿" + jugadorAceptante.getNombre() + " que vas a hacer al respecto?");

        BotonJuego botonAceptar = new BotonJuego("Aceptar");
        botonAceptar.setOnAction(e -> controlador.ejecutar());

        BotonJuego botonRechazar = new BotonJuego("Rechazar");
        botonRechazar.setOnAction(e -> controlador.rechazarOferta());

        HBox botones = new HBox(30, botonAceptar, botonRechazar);
        botones.setAlignment(Pos.CENTER);

        VBox layout = new VBox(40, titulo, oferta, textoPregunta, botones);
        layout.setAlignment(Pos.CENTER);

        this.setCenter(layout);

    }
}
