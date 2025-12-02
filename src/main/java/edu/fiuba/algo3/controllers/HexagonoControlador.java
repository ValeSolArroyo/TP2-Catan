package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.componentes.VistaHexagono;
import javafx.scene.control.Button;

public class HexagonoControlador {
    private final VistaHexagono vista;
    private Hexagono hexagonoModelo;

    public HexagonoControlador(Hexagono hexagonoModelo, VistaHexagono vista) {
        this.vista = vista;
        this.hexagonoModelo = hexagonoModelo;
        conectarBotonesAEventos();
    }

    private void conectarBotonesAEventos() {
        for (int i = 0; i < vista.getBotonesVertices().size(); i++) {
            Button boton = vista.getBotonesVertices().get(i);
            Vertice vertice = hexagonoModelo.getVertices().get(i);

            boton.setOnAction(e -> System.out.println("Click en vértice del modelo: " + vertice));
            //sería la logica del modelo je (construir) pero queria chequear q se vincule ok
        }

        for (int i = 0; i < vista.getBotonesAristas().size(); i++) {
            Button boton = vista.getBotonesAristas().get(i);
            Arista arista = hexagonoModelo.getAristas().get(i);

            boton.setOnAction(e -> System.out.println("Click en arista del modelo: " + arista));
            //sería la logica del modelo je (construir)pero queria chequear q se vincule ok
        }
    }

    public void mostrarVertices() {
        vista.mostrarVertices();
    }

    public void mostrarAristas() {
        vista.mostrarAristas();
    }

    public void ocultarTodo() {
        vista.ocultarVertices();
        vista.ocultarAristas();
    }
}
