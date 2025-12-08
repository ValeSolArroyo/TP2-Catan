package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.controllers.fasesJuego.AccionesTableroControlador;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;

public class VistaVertices extends StackPane {
    private Map<Integer, Vertice> vertices;
    private List<List<Vertice>> verticesPorFila;
    private VBox capaVertices;
    private AccionesTableroControlador tableroControlador;

    public VistaVertices(Map<Integer, Vertice> vertices) {
        this.vertices = vertices;

        capaVertices = new VBox();
        capaVertices.setTranslateX(-40);
        capaVertices.setTranslateY(25);
        this.getChildren().add(capaVertices);

        inicializarVerticesPorFila();
    }

    private void inicializarVerticesPorFila() {
        verticesPorFila = List.of(
                List.of(vertices.get(0), vertices.get(6), vertices.get(10)),
                List.of(vertices.get(5), vertices.get(1), vertices.get(7), vertices.get(11)),
                List.of(vertices.get(4), vertices.get(2), vertices.get(8), vertices.get(12)),
                List.of(vertices.get(14), vertices.get(3), vertices.get(9), vertices.get(13), vertices.get(24)),
                List.of(vertices.get(15), vertices.get(17), vertices.get(19), vertices.get(21), vertices.get(23)),
                List.of(vertices.get(25), vertices.get(16), vertices.get(18), vertices.get(20), vertices.get(22), vertices.get(37)),
                List.of(vertices.get(26), vertices.get(28), vertices.get(30), vertices.get(32), vertices.get(34), vertices.get(36)),
                List.of(vertices.get(27), vertices.get(29), vertices.get(31), vertices.get(33), vertices.get(35)),
                List.of(vertices.get(38), vertices.get(40), vertices.get(42), vertices.get(44), vertices.get(46)),
                List.of(vertices.get(39), vertices.get(41), vertices.get(43), vertices.get(45)),
                List.of(vertices.get(47), vertices.get(49), vertices.get(51), vertices.get(53)),
                List.of(vertices.get(48), vertices.get(50), vertices.get(52))
        );

        construirBotonesVertices();
    }

    private void construirBotonesVertices() {
        for (List<Vertice> fila : verticesPorFila) {
            HBox filaVertices = new HBox();
            filaVertices.setAlignment(Pos.CENTER);
            filaVertices.setSpacing(65);
            filaVertices.setPadding(new Insets(8, 0, 9, 0));
            for (Vertice vertice : fila) {
                Button boton = new Button();
                boton.setPrefSize(20, 20);
                boton.getStyleClass().add("boton-colocacion-vertice");

                boton.setOnAction(e -> {
                    tableroControlador.obtenerVertice(vertice);
                });

                filaVertices.getChildren().add(boton);
            }
            capaVertices.getChildren().add(filaVertices);
        }
    }

    public void setControlador(AccionesTableroControlador controlador) {
        this.tableroControlador = controlador;

    }

    public Button botonDe(EspacioConstruible vertice) {
        for (List<Vertice> fila : verticesPorFila) {
            for (Vertice verticeFila : fila) {
                if (verticeFila == vertice) {
                    int index = fila.indexOf(verticeFila);
                    HBox filaBox = (HBox) capaVertices.getChildren().get(verticesPorFila.indexOf(fila));
                    return (Button) filaBox.getChildren().get(index);
                }
            }
        }
        return new Button();

    }
}
