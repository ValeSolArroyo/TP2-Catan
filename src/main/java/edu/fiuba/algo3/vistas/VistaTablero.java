package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.vistas.componentes.VistaHexagono;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class VistaTablero extends VBox {
    public VistaTablero(Tablero tablero) {
        super(-38); // spacing vertical entre filas

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(25, 85, 0, 5));

        construirTablero(tablero);
    }

    private void construirTablero(Tablero tablero) {

        List<Hexagono> hexagonos = tablero.getHexagonos();
        int[] hexagonosPorFila = {3, 4, 5, 4, 3};

        int indiceHexagonos = 0;

        for (int filaHexagonos = 0; filaHexagonos < hexagonosPorFila.length; filaHexagonos++) {

            int cantHexagonosPorFila = hexagonosPorFila[filaHexagonos];

            HBox fila = new HBox(-5);  // spacing horizontal entre hexagonos
            fila.setAlignment(Pos.CENTER);

            for (int i = 0; i < cantHexagonosPorFila; i++) {
                Hexagono hexagono = hexagonos.get(indiceHexagonos++);
                VistaHexagono vista = new VistaHexagono(hexagono.getTerreno(), hexagono.getFicha());

                fila.getChildren().add(vista);
            }

            this.getChildren().add(fila);
        }
    }
}
