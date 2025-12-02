package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.juego.Dado;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import edu.fiuba.algo3.modelo.observer.Observador;

public class VistaDados extends HBox implements Observador {
    private ImageView dado1;
    private ImageView dado2;
    private Dado dado;
    private Button botonLanzar;

    public VistaDados(Dado dado) {
        this.dado = dado;
        dado.agregarObservador(this);

        // PLaceholders, ver si una vez que se lanza se reemplazan por el resultado o qué
        dado1 = new ImageView(new Image(getClass().getResource("/images/utils/dado/1.png").toExternalForm()));
        dado2 = new ImageView(new Image(getClass().getResource("/images/utils/dado/2.png").toExternalForm()));
        dado1.setFitWidth(100); dado1.setFitHeight(100);
        dado2.setFitWidth(100); dado2.setFitHeight(100);

        this.getChildren().addAll(dado1, dado2);
        this.setSpacing(15);
        this.setAlignment(Pos.CENTER);
    }

    @Override
    public void actualizar() {
        int valor1 = dado.obtenerDado1();
        int valor2 = dado.obtenerDado2();
        System.out.println("/images/utils/dado/" + valor1 + ".png");
        System.out.println("/images/utils/dado/" + valor2 + ".png");
        dado1.setImage(new Image(getClass().getResource("/images/utils/dado/" + valor1 + ".png").toExternalForm()));
        dado2.setImage(new Image(getClass().getResource("/images/utils/dado/" + valor2 + ".png").toExternalForm()));
    }
}