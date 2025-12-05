package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.controllers.VolverControlador;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.Map;

public class VistaMostrarCarta extends BorderPane {
    private static final Map<String, String> rutasCartas = Map.of(
            "Caballero", "/images/utils/cartas/caballero.png",
            "Progreso de Construccion", "/images/utils/cartas/construccion_carretera.png",
            "Progreso de Descubrimiento", "/images/utils/cartas/descubrimiento.png",
            "Progreso Monopolio", "/images/utils/hexagono/monopolio.png",
            "Punto de Victoria", "/images/utils/hexagono/pv.png"
    );

    public VistaMostrarCarta(String texto, String nombreCarta, ContenedorPrincipalVistas contenedor, VistaJuegoGeneral vista){
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));

        Label textoObtuviste = new Label(texto);
        this.getStyleClass().add("texto-comprar-carta");
        BorderPane.setAlignment(textoObtuviste, Pos.CENTER);
        this.setTop(textoObtuviste);

        String ruta = rutasCartas.get(nombreCarta);
        Image imagen = new Image(getClass().getResourceAsStream(ruta));
        ImageView imagenCarta = new ImageView(imagen);
        imagenCarta.setPreserveRatio(true);
        imagenCarta.setFitWidth(300);

        BorderPane.setAlignment(imagenCarta, Pos.CENTER);
        this.setCenter(imagenCarta);

        BotonJuego botonAceptar = new BotonJuego("Aceptar");
        botonAceptar.setOnAction(new VolverControlador(contenedor, vista));

        VBox botonVbox = new VBox(botonAceptar);
        botonVbox.setAlignment(Pos.CENTER);
        botonVbox.setSpacing(10);
        VBox.setMargin(botonAceptar, new Insets(0, 0, 10, 0));
        this.setBottom(botonVbox);
    }
}
