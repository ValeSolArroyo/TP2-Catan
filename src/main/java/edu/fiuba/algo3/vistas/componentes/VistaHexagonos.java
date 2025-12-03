package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class VistaHexagonos extends StackPane {
    private final List<Hexagono> hexagonos;
    private final List<ImagenHexagono> vistasHexagonos = new ArrayList<>();
    private final VBox capaHexagonos;

    private final int[] hexagonosPorFila = {3, 4, 5, 4, 3};

    public VistaHexagonos(List<Hexagono> hexagonos) {
        this.hexagonos = hexagonos;

        capaHexagonos = new VBox(-38);
        capaHexagonos.setAlignment(Pos.CENTER);
        capaHexagonos.setPadding(new Insets(25, 85, 0, 5));

        construirHexagonos();
        activarBoton();

        this.getChildren().add(capaHexagonos);
    }

    private void construirHexagonos() {
        int indiceHexagonos = 0;
        for (int fila = 0; fila < hexagonosPorFila.length; fila++) {
            int cantHexagonosPorFila = hexagonosPorFila[fila];
            HBox filaHexagono = new HBox(-5);
            filaHexagono.setAlignment(Pos.CENTER);

            for (int i = 0; i < cantHexagonosPorFila; i++) {
                Hexagono hexagono = hexagonos.get(indiceHexagonos++);
                ImagenHexagono vista = new ImagenHexagono(hexagono);
                vistasHexagonos.add(vista);
                filaHexagono.getChildren().add(vista);
            }
            capaHexagonos.getChildren().add(filaHexagono);
        }
    }

    public void activarBoton() {
        for (ImagenHexagono imagenHexagono : vistasHexagonos) {
            imagenHexagono.getBoton().setOnAction(e -> {} //controlador.seleccionarHexagono(this)
            );
        }
    }
}
