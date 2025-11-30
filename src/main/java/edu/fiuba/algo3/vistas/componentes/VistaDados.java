package edu.fiuba.algo3.vistas.componentes;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class VistaDados extends HBox {
    private ImageView dado1;
    private ImageView dado2;

    public VistaDados() {
        dado1 = new ImageView();
        dado2 = new ImageView();
        dado1.setFitWidth(100); dado1.setFitHeight(100);
        dado2.setFitWidth(100); dado2.setFitHeight(100);
        this.getChildren().addAll(dado1, dado2);
        this.setSpacing(15);
        this.setAlignment(Pos.CENTER);
    }

    public void actualizarDados(int valor1, int valor2) {
        // Esto lo llamaría el controlador cuando el modelo le de los valores
        dado1.setImage(new Image(getClass().getResource("/images/utils/dado/" + valor1 + ".png").toExternalForm()));
        dado2.setImage(new Image(getClass().getResource("/images/utils/dado/" + valor2 + ".png").toExternalForm()));
    }
}