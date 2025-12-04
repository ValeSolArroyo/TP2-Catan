package edu.fiuba.algo3.vistas.componentes;
import edu.fiuba.algo3.controllers.fasesJuego.AccionesTableroControlador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.scene.layout.StackPane;


public class VistaTablero extends StackPane {
    private VistaHexagonos vistaHexagonos;
    private VistaVertices vistaVertices;
    private VistaAristas vistaAristas;

    public VistaTablero(Tablero tablero) {
        vistaHexagonos = new VistaHexagonos(tablero.getHexagonos());
        vistaVertices = new VistaVertices(tablero.getVertices());
        vistaAristas  = new VistaAristas(tablero.getAristas());
        // TODO: agregar vistaConstrucciones? para que se pongan encima de todo

        this.getChildren().addAll(vistaHexagonos, vistaVertices, vistaAristas);
        ocultarVertices();
        ocultarAristas();
    }

    public void setControlador(AccionesTableroControlador controlador) {
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
}
