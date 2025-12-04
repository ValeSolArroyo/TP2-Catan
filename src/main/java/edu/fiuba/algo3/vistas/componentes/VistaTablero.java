package edu.fiuba.algo3.vistas.componentes;
import edu.fiuba.algo3.controllers.ControladorColocaciones;
import edu.fiuba.algo3.controllers.fasesJuego.AccionesTableroControlador;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.VistaConstrucciones;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

import java.util.List;


public class VistaTablero extends StackPane {
    private VistaHexagonos vistaHexagonos;
    private VistaVertices vistaVertices;
    private VistaAristas vistaAristas;
    private VistaConstrucciones vistaConstrucciones;

    public VistaTablero(Tablero tablero) {
        vistaHexagonos = new VistaHexagonos(tablero.getHexagonos());
        vistaVertices = new VistaVertices(tablero.getVertices());
        vistaAristas  = new VistaAristas(tablero.getAristas());
        vistaConstrucciones = new VistaConstrucciones();

        this.getChildren().addAll(vistaHexagonos, vistaVertices, vistaAristas, vistaConstrucciones);
        ocultarVertices();
        ocultarAristas();
    }

    public void setControlador(ControladorColocaciones controlador) {
        vistaAristas.setControlador(controlador);
        vistaVertices.setControlador(controlador);
    }

    public void ocultarVertices() {
        vistaVertices.setDisable(true);
        vistaVertices.setOpacity(0);
    }

    public void ocultarAristas() {
        vistaAristas.setDisable(true);
        vistaAristas.setOpacity(0);
    }

    public void mostrarVertices() {
        vistaVertices.setDisable(false);
        vistaVertices.setOpacity(1);
    }

    public void mostrarAristas() {
        vistaAristas.setDisable(false);
        vistaAristas.setOpacity(1);
    }

    public void mostrarAristas(List<Arista> aristas) {
        vistaAristas.setDisable(false);
        vistaAristas.setOpacity(1);
        vistaAristas.mostrarSolo(aristas);
    }


    public void dibujarPobladoEn(Vertice vertice, Color color){
        Button boton = vistaVertices.botonDe(vertice);
        vistaConstrucciones.dibujarPoblado(boton, color);
    }

    public void dibujarCarreteraEn(Arista arista, Color color){
        Button boton = vistaAristas.botonDe(arista);
        vistaConstrucciones.dibujarCarretera(boton, color);
    }
}
