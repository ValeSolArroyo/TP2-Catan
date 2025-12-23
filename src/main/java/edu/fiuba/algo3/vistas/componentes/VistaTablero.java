package edu.fiuba.algo3.vistas.componentes;
import edu.fiuba.algo3.controllers.fasesJuego.AccionHexagonoControlador;
import edu.fiuba.algo3.controllers.fasesJuego.AccionesTableroControlador;
import edu.fiuba.algo3.modelo.tablero.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

import java.util.List;
import java.util.Map;


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

        this.getChildren().addAll(vistaHexagonos, vistaConstrucciones, vistaVertices, vistaAristas);
        ocultarVertices();
        ocultarAristas();
    }

    public void setControlador(AccionesTableroControlador controlador) {
        vistaAristas.setControlador(controlador);
        vistaVertices.setControlador(controlador);
    }

    public void setControlador(AccionHexagonoControlador controlador) {
        vistaHexagonos.setControlador(controlador);
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
        vistaVertices.setOpacity(0.8);
    }

    public void mostrarAristas(List<Arista> aristas) {
        vistaAristas.setDisable(false);
        vistaAristas.setOpacity(0.8);
        if(aristas.size() == 0) {
            vistaAristas.mostrarAristas();
        }else {
            vistaAristas.mostrarSolo(aristas);
        }

    }

    public void mostrarTodasLasAristas() {
        vistaAristas.setDisable(false);
        vistaAristas.setOpacity(0.8);
        this.vistaAristas.mostrarAristas();
    }

    public void dibujarPobladoEn(EspacioConstruible vertice, Color color){
        Button boton = vistaVertices.botonDe(vertice);
        vistaConstrucciones.dibujarPoblado(boton, color, vertice);
    }

    public void dibujarCarreteraEn(EspacioConstruible arista, Color color){
        Button boton = vistaAristas.botonDe(arista);
        vistaConstrucciones.dibujarCarretera(boton, color);
    }

    public void dibujarCiudadEn(EspacioConstruible vertice, Color color) {
        Button boton = vistaVertices.botonDe(vertice);
        vistaConstrucciones.dibujarCiudad(boton, color, vertice);
    }

    public void mostrarVerticesPuerto() {
        mostrarVertices();
        vistaVertices.mostrarVerticesPuerto();
    }

    public void mostrarVerticesConstruccion() {
        vistaVertices.setDisable(false);
        vistaVertices.setOpacity(0.8);
        vistaVertices.mostrarVertices();
    }

    public void mostrarLadronEn(Hexagono hexagono) {
        vistaHexagonos.mostrarLadronEn(hexagono);
    }

    public void desactivarHexagonos() {
        vistaHexagonos.desactivarBotones();
    }

    public void activarHexagonos() {
        vistaHexagonos.activarBoton();
    }
}
