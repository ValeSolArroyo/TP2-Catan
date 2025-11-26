package edu.fiuba.algo3.vistas;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ContenedorPrincipalVistas extends BorderPane {
    private Stage stage;

    public ContenedorPrincipalVistas(Stage stage) {
        super();
        this.stage = stage;
    }

    public void setContenido(Node vista) {
        this.setCenter(vista);
    }

    public Stage getStage() {
        return stage;
    }
}
