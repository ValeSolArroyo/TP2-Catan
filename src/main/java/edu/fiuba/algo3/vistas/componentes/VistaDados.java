package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.juego.Dado;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import edu.fiuba.algo3.modelo.observer.Observador;
import javafx.scene.layout.VBox;

public class VistaDados extends VBox implements Observador {
    private ImageView dado1;
    private ImageView dado2;
    private Label textoResultado;
    private Dado dado;

    public VistaDados(Dado dado) {
        this.dado = dado;
        dado.agregarObservador(this);

        textoResultado = new Label("");
        textoResultado.getStyleClass().add("texto-dados");

        dado1 = new ImageView(new Image(getClass().getResource("/images/utils/dado/dado_placeholder.png").toExternalForm()));
        dado2 = new ImageView(new Image(getClass().getResource("/images/utils/dado/dado_placeholder.png").toExternalForm()));
        dado1.setFitWidth(100); dado1.setFitHeight(100);
        dado2.setFitWidth(100); dado2.setFitHeight(100);

        HBox dadosContenedor = new HBox(dado1, dado2);
        dadosContenedor.setSpacing(15);
        dadosContenedor.setAlignment(Pos.CENTER);

        this.getChildren().addAll(textoResultado, dadosContenedor);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
    }

    @Override
    public void actualizar() {
        int valor1 = dado.obtenerDado1();
        int valor2 = dado.obtenerDado2();
        dado1.setImage(new Image(getClass().getResource("/images/utils/dado/" + valor1 + ".png").toExternalForm()));
        dado2.setImage(new Image(getClass().getResource("/images/utils/dado/" + valor2 + ".png").toExternalForm()));
        textoResultado.setText("¡Obtuviste un " + (valor1 + valor2) + "!");
    }
}