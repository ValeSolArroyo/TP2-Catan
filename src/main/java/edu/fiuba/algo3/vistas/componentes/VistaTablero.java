package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class VistaTablero extends StackPane {
    private VBox capaHexagonos;
    private VBox capaVertices;
    private VBox capaAristas;


    private List<VistaHexagono> vistasHexagonos = new ArrayList<>();

    public VistaTablero(Tablero tablero) {
        capaHexagonos = new VBox(-38);
        capaHexagonos.setAlignment(Pos.CENTER);
        capaHexagonos.setPadding(new Insets(25, 85, 0, 5));

        construirHexagonos(tablero);

        capaVertices = new VBox();
        capaVertices.setTranslateX(-40);
        capaVertices.setTranslateY(28);
        construirVertices();

        this.getChildren().addAll(capaHexagonos, capaVertices, capaAristas);
    }

    private void construirHexagonos(Tablero tablero) {
        List<Hexagono> hexagonos = tablero.getHexagonos();
        int[] hexagonosPorFila = {3, 4, 5, 4, 3};
        int indiceHexagonos = 0;

        for (int filaHexagonos = 0; filaHexagonos < hexagonosPorFila.length; filaHexagonos++) {
            int cantHexagonosPorFila = hexagonosPorFila[filaHexagonos];
            HBox fila = new HBox(-5);
            fila.setAlignment(Pos.CENTER);

            for (int i = 0; i < cantHexagonosPorFila; i++) {
                Hexagono hexagono = hexagonos.get(indiceHexagonos++);
                VistaHexagono vista = new VistaHexagono(hexagono);
                vistasHexagonos.add(vista);
                fila.getChildren().add(vista);
            }

            capaHexagonos.getChildren().add(fila);
        }
    }

    private void construirVertices() {
        int[] verticesPorFila = {3, 4, 4, 5, 5, 6, 6, 5, 5, 4, 4, 3};
        for (int cantVertices : verticesPorFila) {
            HBox filaVertices = new HBox();
            filaVertices.setAlignment(Pos.CENTER);

            filaVertices.setSpacing(70);
            filaVertices.setPadding(new Insets(7, 0, 8, 0));

            for (int i = 0; i < cantVertices; i++) {
                Button botonVertice = crearBotonVertice();
                filaVertices.getChildren().add(botonVertice);
            }

            capaVertices.getChildren().add(filaVertices);
        }
    }

    private Button crearBotonVertice() {
        Button boton = new Button();
        boton.setPrefSize(14, 14);
        boton.setStyle("-fx-background-radius: 10; -fx-background-color: white;");

        // cuando tengamos el controlador!!!
        // boton.setOnAction(e -> controladorSeleccionarVertice(...));

        return boton;
    }
}
