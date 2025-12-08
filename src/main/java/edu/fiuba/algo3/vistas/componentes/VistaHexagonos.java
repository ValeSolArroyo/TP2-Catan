package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.controllers.fasesJuego.AccionHexagonoControlador;
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
    private AccionHexagonoControlador controlador;
    private ImagenHexagono imagenConLadronActual;

    private final int[] hexagonosPorFila = {3, 4, 5, 4, 3};

    public VistaHexagonos(List<Hexagono> hexagonos) {
        this.hexagonos = hexagonos;

        capaHexagonos = new VBox(-38);
        capaHexagonos.setAlignment(Pos.CENTER);
        capaHexagonos.setPadding(new Insets(25, 85, 0, 5));

        construirHexagonos();

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
                if (vista.tieneLadronInicial()) {
                    imagenConLadronActual = vista;
                }
                filaHexagono.getChildren().add(vista);
            }
            capaHexagonos.getChildren().add(filaHexagono);
        }
    }

    public void setControlador(AccionHexagonoControlador controlador){
        this.controlador = controlador;
    }

    public void activarBoton() {
        for (ImagenHexagono imagenHexagono : vistasHexagonos) {
            if (imagenHexagono == imagenConLadronActual) {
                imagenHexagono.getBoton().setDisable(true);
                imagenHexagono.getBoton().setOpacity(0);
                continue;
            }
            imagenHexagono.getBoton().setOpacity(0.5);
            imagenHexagono.getBoton().setDisable(false);
            imagenHexagono.getBoton().setOnAction(e -> controlador.obtenerHexagono(imagenHexagono.getHexagono()));
        }
    }

    public void desactivarBotones() {
        for (ImagenHexagono imagenHexagono : vistasHexagonos) {
            imagenHexagono.getBoton().setDisable(true);
            imagenHexagono.getBoton().setOpacity(0);
        }
    }

    public void mostrarLadronEn(Hexagono hexagono) {
        imagenConLadronActual.ocultarLadron();
        imagenConLadronActual.getBoton().setDisable(true);
        imagenConLadronActual.getBoton().setOpacity(0);

        for (ImagenHexagono imagen : vistasHexagonos) {
            if (imagen.getHexagono() == hexagono) {
                imagen.getBoton().setDisable(true);
                imagen.getBoton().setOpacity(0);
                imagen.mostrarLadron();
                imagenConLadronActual = imagen;
                return;
            }
        }
    }
}
