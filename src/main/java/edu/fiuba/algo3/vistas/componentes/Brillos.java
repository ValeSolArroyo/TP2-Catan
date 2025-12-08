package edu.fiuba.algo3.vistas.componentes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Brillos {
    private static final String RUTA_BRILLOS = "/images/utils/efectos/brillos_amarillos.gif";

    public static StackPane crearBrillos() {
        Image gifBrillo = new Image(Brillos.class.getResourceAsStream(RUTA_BRILLOS));

        ImageView botonArribaIzq = crearBrillo(gifBrillo);
        ImageView botonArribaDer = crearBrillo(gifBrillo);
        ImageView botonAbajoIzq = crearBrillo(gifBrillo);
        ImageView botonAbajoDer = crearBrillo(gifBrillo);

        StackPane.setAlignment(botonArribaIzq, Pos.TOP_LEFT);
        StackPane.setAlignment(botonArribaDer, Pos.TOP_RIGHT);
        StackPane.setAlignment(botonAbajoIzq, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(botonAbajoDer, Pos.BOTTOM_RIGHT);

        StackPane.setMargin(botonArribaIzq, new Insets(60, 0, 0, 60));
        StackPane.setMargin(botonArribaDer, new Insets(60, 60, 0, 0));
        StackPane.setMargin(botonAbajoIzq, new Insets(0, 0, 60, 60));
        StackPane.setMargin(botonAbajoDer, new Insets(0, 60, 60, 0));

        StackPane brillos = new StackPane();
        brillos.getChildren().addAll(botonArribaIzq, botonArribaDer, botonAbajoIzq, botonAbajoDer);

        return brillos;
    }

    private static ImageView crearBrillo(Image gif) {
        ImageView brillo = new ImageView(gif);
        brillo.setFitWidth(200);
        brillo.setPreserveRatio(true);
        return brillo;
    }
}
