package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.controllers.HexagonoControlador;
import edu.fiuba.algo3.controllers.fasesJuego.AccionesTableroControlador;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class VistaTablero extends VBox {
    private List<HexagonoControlador> controladores = new ArrayList<>();
    private List<VistaHexagono> vistasHexagonos = new ArrayList<>();
    private AccionesTableroControlador controlador;

    public VistaTablero(Tablero tablero, AccionesTableroControlador controlador) {
        super(-38);
        this.controlador = controlador;

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(25, 85, 0, 5));

        construirTablero(tablero);
        conectarBotonesAEventos();
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
                Hexagono hexagono = hexagonos.get(indiceHexagonos++);
                VistaHexagono vista = new VistaHexagono(hexagono);
                HexagonoControlador controladorHexagono = new HexagonoControlador(hexagono, vista);
                vistasHexagonos.add(vista);
                controladores.add(controladorHexagono);

                fila.getChildren().add(vista);
            }

            this.getChildren().add(fila);
        }
    }

    private void conectarBotonesAEventos() {
        for (VistaHexagono vista : vistasHexagonos) {
            Hexagono hexagono = vista.getHexagono();
            for (int i = 0; i < vista.getBotonesVertices().size(); i++) {
                Button boton = vista.getBotonesVertices().get(i);
                Vertice vertice = hexagono.getVertices().get(i);

                boton.setOnAction(e -> controlador.obtenerVertice(vertice));
            }

            for (int i = 0; i < vista.getBotonesAristas().size(); i++) {
                Button boton = vista.getBotonesAristas().get(i);
                Arista arista = hexagono.getAristas().get(i);

                boton.setOnAction(e -> controlador.obtenerArista(arista));
            }
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
            controlador.ocultarVertices();
            controlador.ocultarAristas();
        }
    }

    public void setControlador(AccionesTableroControlador controlador) {
        this.controlador = controlador;
    }
}
