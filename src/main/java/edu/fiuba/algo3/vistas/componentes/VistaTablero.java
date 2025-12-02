package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.controllers.HexagonoControlador;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class VistaTablero extends VBox {
    private List<HexagonoControlador> controladores = new ArrayList<>();

    public VistaTablero(Tablero tablero) {
        super(-38);

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

            HBox fila = new HBox(-5);
            fila.setAlignment(Pos.CENTER);

            for (int i = 0; i < cantHexagonosPorFila; i++) {

                VistaHexagono vista = new VistaHexagono(hexagonos.get(indiceHexagonos++));
                HexagonoControlador controladorHexagono = new HexagonoControlador(vista);
                controladores.add(controladorHexagono);

                fila.getChildren().add(vista);
            }

            this.getChildren().add(fila);
        }
    }
    public void mostrarVertices() {
        for (HexagonoControlador controlador : controladores) {
            controlador.mostrarVertices();
        }
    }

    public void mostrarAristas() {
        for (HexagonoControlador controlador : controladores) {
            controlador.mostrarAristas();
        }
    }

    public void ocultarTodo() {
        for (HexagonoControlador controlador : controladores) {
            controlador.ocultarTodo();
        }
    }
}
