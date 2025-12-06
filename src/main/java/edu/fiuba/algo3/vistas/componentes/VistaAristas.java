package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.controllers.fasesJuego.AccionesTableroControlador;
import edu.fiuba.algo3.modelo.tablero.Arista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VistaAristas extends StackPane {
    private Map<Integer, Arista> aristas;
    private List<List<Arista>> aristasPorFila;
    private VBox capaAristas;
    private AccionesTableroControlador tableroControlador;
    private Map<Arista, Button> botonesAristas = new HashMap<>();

    private static final Set<Integer> rotacion_45 = Set.of(
        5, 10, 15, 20, 2, 8, 13, 35, 17, 22, 25, 29, 32, 37, 40, 43, 47, 50, 54, 57, 60, 63, 67, 70);

    private static final Set<Integer> rotacion_neg_45 = Set.of(
        0, 6, 11, 3, 9, 14, 27, 18, 23, 26, 30, 45, 33, 38, 41, 44, 48, 51, 55, 58, 61, 64, 68, 71);

    private static final Set<Integer> rotacion_vertical = Set.of(
        4, 1, 7, 12, 19, 16, 21, 24, 28, 34, 31, 36, 39, 42, 46, 52, 41, 53, 56, 59, 65, 62, 66, 69);

    public VistaAristas(Map<Integer, Arista> aristas) {
        this.aristas = aristas;

        capaAristas = new VBox();
        capaAristas.setTranslateX(-40);
        capaAristas.setTranslateY(35);
        this.getChildren().add(capaAristas);

        inicializarAristasPorFila();
    }

    public void inicializarAristasPorFila() {
        aristasPorFila = List.of(
            List.of(aristas.get(5), aristas.get(0), aristas.get(10), aristas.get(6), aristas.get(15), aristas.get(11)),
            List.of(aristas.get(4), aristas.get(1), aristas.get(7), aristas.get(12)),
            List.of(aristas.get(20), aristas.get(3), aristas.get(2), aristas.get(9), aristas.get(8), aristas.get(14), aristas.get(13), aristas.get(27)),
            List.of(aristas.get(19), aristas.get(16), aristas.get(21), aristas.get(24), aristas.get(28)),
            List.of(aristas.get(35), aristas.get(18), aristas.get(17), aristas.get(23), aristas.get(22), aristas.get(26), aristas.get(25), aristas.get(30), aristas.get(29), aristas.get(45)),
            List.of(aristas.get(34), aristas.get(31), aristas.get(36), aristas.get(39), aristas.get(42), aristas.get(46)),
            List.of(aristas.get(33), aristas.get(32), aristas.get(38), aristas.get(37), aristas.get(41), aristas.get(40), aristas.get(44), aristas.get(43), aristas.get(48), aristas.get(47)),
            List.of(aristas.get(52), aristas.get(49), aristas.get(53), aristas.get(56), aristas.get(59)),
            List.of(aristas.get(51), aristas.get(50), aristas.get(55), aristas.get(54), aristas.get(58), aristas.get(57), aristas.get(61), aristas.get(60)),
            List.of(aristas.get(65), aristas.get(62), aristas.get(66), aristas.get(69)),
            List.of(aristas.get(64), aristas.get(63), aristas.get(68), aristas.get(67), aristas.get(71), aristas.get(70)));
        construirBotonesAristas();
    }

    public void construirBotonesAristas() {
        for (List<Arista> fila : aristasPorFila) {
            HBox filaAristas = new HBox(20);
            filaAristas.setAlignment(Pos.CENTER);
            filaAristas.setSpacing(26);
            filaAristas.setPadding(new Insets(4, 8, 4, 8));

            for (Arista arista : fila) {
                Button boton = new Button();
                boton.setPrefSize(2, 35);
                boton.getStyleClass().add("boton-colocacion-arista");

                int id = arista.getId();

                if (rotacion_45.contains(id)) {
                    boton.setRotate(45);
                } else if (rotacion_neg_45.contains(id)) {
                    boton.setRotate(-45);
                } else if (rotacion_vertical.contains(id)) {
                    boton.setRotate(0);
                    HBox.setMargin(boton, new Insets(0, 22, 0, 22));
                }

                boton.setOnAction(e -> { tableroControlador.obtenerArista(arista);
                });

                botonesAristas.put(arista, boton);

                filaAristas.getChildren().add(boton);
            }

            capaAristas.getChildren().add(filaAristas);
        }
    }

    public void setControlador(AccionesTableroControlador controlador) {
        this.tableroControlador = controlador;

    }

    public void mostrarAristas() {
        for (Button boton: botonesAristas.values()) {
            boton.setDisable(false);
            boton.setOpacity(1);
        }

    }

    public void mostrarSolo(List<Arista> aristasValidas) {
        for (Map.Entry<Arista, Button> entry : botonesAristas.entrySet()) {

            Arista arista = entry.getKey();
            Button boton = entry.getValue();

            boolean esValida = aristasValidas.contains(arista);

            boton.setDisable(!esValida);

            if (esValida) {
                boton.setOpacity(1);
            } else {
                boton.setOpacity(0);
            }
        }
    }


    public Button botonDe(Arista arista) {
        return botonesAristas.get(arista);
    }
}
