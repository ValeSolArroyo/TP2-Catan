package edu.fiuba.algo3.vistas.comercio;

import edu.fiuba.algo3.controllers.comercio.ComercioInternoControlador;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.ListadoRecurso;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaOfertaInterno extends BorderPane {
    private Map<Recurso, Integer> recursosOfertados;
    private Map<Recurso, Integer> recursosPedidos;

    public VistaOfertaInterno(ComercioInternoControlador controlador, Jugador jugadorActual, Jugador jugadorAceptante, List<Recurso> recursosAEntregar, List<Recurso> recursosARecibir) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        this.recursosOfertados = new HashMap<>();
        this.recursosPedidos = new HashMap<>();

        Label titulo = new Label("Intercambio con otro jugador: el jugador " + jugadorActual.getNombre() + " ha propuesto:");
        titulo.getStyleClass().add("titulo-comercio");
        VBox topContainer = new VBox(titulo);
        topContainer.setPadding(new Insets(20, 0, 0, 0));
        topContainer.setAlignment(Pos.CENTER);
        this.setTop(topContainer);

        for (Recurso recurso : recursosAEntregar) {
            recursosOfertados.put(recurso, recursosOfertados.getOrDefault(recurso, 0) + 1);
        }

        for (Recurso recurso : recursosARecibir) {
            recursosPedidos.put(recurso, recursosPedidos.getOrDefault(recurso, 0) + 1);
        }

        VBox columnaIzquierda = new VBox(10);
        columnaIzquierda.setAlignment(Pos.CENTER);
        columnaIzquierda.setPadding(new Insets(0, 0, 0, 20));
        Label textoIzq = new Label("El jugador que oferta te entregaria:");
        textoIzq.getStyleClass().add("texto-comercio");
        columnaIzquierda.getChildren().add(textoIzq);

        HBox filaOfertado = new HBox(40);
        filaOfertado.setAlignment(Pos.CENTER);
        int contadorOfertado = 0;
        for (Recurso recurso : recursosOfertados.keySet()) {
            String ruta = "/images/utils/recursos/" + recurso.getNombreRecurso() + ".png";
            int cantidad = recursosOfertados.get(recurso);
            ListadoRecurso listadoRecurso = new ListadoRecurso(recurso.getNombreRecurso() + " x" + cantidad, ruta);

            filaOfertado.getChildren().add(listadoRecurso);
            contadorOfertado++;
            if (contadorOfertado % 3 == 0) {
                columnaIzquierda.getChildren().add(filaOfertado);
                filaOfertado = new HBox(40);
                filaOfertado.setAlignment(Pos.CENTER);
            }
        }
        columnaIzquierda.getChildren().add(filaOfertado);
        this.setLeft(columnaIzquierda);

        VBox columnaDerecha = new VBox(10);
        columnaDerecha.setAlignment(Pos.CENTER);
        columnaDerecha.setPadding(new Insets(0, 20, 0, 0));
        Label textoDer = new Label("El jugador que oferta te pide:");
        textoDer.getStyleClass().add("texto-comercio");
        columnaDerecha.getChildren().add(textoDer);

        HBox filaPedido = new HBox(40);
        filaPedido.setAlignment(Pos.CENTER);
        int contadorPedido = 0;
        for (Recurso recurso : recursosPedidos.keySet()) {
            String ruta = "/images/utils/recursos/" + recurso.getNombreRecurso() + ".png";
            int cantidad = recursosPedidos.get(recurso);
            ListadoRecurso listadoRecurso = new ListadoRecurso(recurso.getNombreRecurso() + " x" + cantidad, ruta);

            filaPedido.getChildren().add(listadoRecurso);
            contadorPedido++;
            if (contadorPedido % 3 == 0) {
                columnaDerecha.getChildren().add(filaPedido);
                filaPedido = new HBox(40);
                filaPedido.setAlignment(Pos.CENTER);
            }
        }
        columnaDerecha.getChildren().add(filaPedido);
        this.setRight(columnaDerecha);

        Label textoPregunta = new Label("¿" + jugadorAceptante.getNombre() + " qué vas a hacer al respecto?");
        textoPregunta.getStyleClass().add("texto-comercio");

        BotonJuego botonAceptar = new BotonJuego("Aceptar");
        botonAceptar.setOnAction(e -> controlador.ejecutar());

        BotonJuego botonRechazar = new BotonJuego("Rechazar");
        botonRechazar.setOnAction(e -> controlador.rechazarOferta());

        HBox botones = new HBox(30, botonRechazar, botonAceptar);
        botones.setAlignment(Pos.CENTER);

        VBox contenedorABajo = new VBox(15, textoPregunta, botones);
        contenedorABajo.setAlignment(Pos.CENTER);
        contenedorABajo.setPadding(new Insets(20));
        this.setBottom(contenedorABajo);

        Transicion.fade(this);
    }
}
