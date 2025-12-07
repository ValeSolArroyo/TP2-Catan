package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Map;

public class ImagenHexagono extends StackPane {
    private static final Map<String, String> rutasImagenes = Map.of(
            "Bosque", "/images/utils/hexagonosFichas/bosque.png",
            "Colina", "/images/utils/hexagonosFichas/colina.png",
            "Pastizal", "/images/utils/hexagonosFichas/pasto.png",
            "Campo", "/images/utils/hexagonosFichas/campo.png",
            "Montaña", "/images/utils/hexagonosFichas/montaña.png"
    );
    private static final double ANCHO = 90;
    private static final double ALTO  = 125;
    private Hexagono hexagono;
    private Button boton;
    private ImageView ladron;

    public ImagenHexagono(Hexagono hexagono) {
        this.hexagono = hexagono;
        String terreno = hexagono.getTerreno();
        int ficha = hexagono.getFicha();
        mostrarImagen(terreno, ficha);
        agregarBoton();
    }

    private void mostrarImagen(String terreno, int ficha) {
        String ruta;

        if (rutasImagenes.containsKey(terreno)) {
            ruta = rutasImagenes.get(terreno);
        } else {
            ruta = "/images/utils/hexagonosFichas/desierto.png";
        }

        Image imagen = new Image(getClass().getResource(ruta).toExternalForm());
        ImageView view = new ImageView(imagen);

        view.setFitWidth(90);
        view.setFitHeight(125);

        this.setAlignment(Pos.CENTER);
        this.getChildren().add(view);

        if (!terreno.equals("Desierto")) {
            Label fichaNumero = new Label(String.valueOf(ficha));
            fichaNumero.getStyleClass().add("ficha-numero");
            this.getChildren().add(fichaNumero);
        }

        Image ladronImagen = new Image(getClass().getResource("/images/utils/ladron.png").toExternalForm());
        ladron = new ImageView(ladronImagen);
        ladron.setFitWidth(40);
        ladron.setPreserveRatio(true);

        if (!terreno.equals("Desierto")) {
            ladron.setOpacity(0);
        }

        this.getChildren().add(ladron);
    }

    public void agregarBoton() {
       boton = new Button();
       boton.setStyle("-fx-background-color: white;");
       boton.setPrefSize(65, 65);
       boton.setDisable(true);
       boton.setOpacity(0);
       this.getChildren().addAll(boton);
    }

    public void mostrarLadron() {
        ladron.setOpacity(1);
    }

    public void ocultarLadron() {
        ladron.setOpacity(0);
    }

    public boolean tieneLadronInicial() {
        return ladron.getOpacity() == 1;
    }

    public Hexagono getHexagono() {
        return hexagono;
    }

    public Button getBoton() {
        return boton;
    }
}