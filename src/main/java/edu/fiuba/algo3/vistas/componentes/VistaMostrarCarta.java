package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.controllers.VolverControlador;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Map;

public class VistaMostrarCarta extends BorderPane {
    private static final Map<String, String> rutasCartas = Map.of(
            "Caballero", "/images/utils/cartas/caballero.png",
            "Progreso de Construccion", "/images/utils/cartas/construccion_carretera.png",
            "Progreso de Descubrimiento", "/images/utils/cartas/descubrimiento.png",
            "Progreso Monopolio", "/images/utils/cartas/monopolio.png",
            "Punto de Victoria", "/images/utils/cartas/pv.png");

    public VistaMostrarCarta(String texto, String nombreCarta, ContenedorPrincipalVistas contenedor, VistaJuegoGeneral vista){
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));

        Label textoObtuviste = new Label(texto);
        textoObtuviste.getStyleClass().add("texto-comprar-carta");
        BorderPane.setAlignment(textoObtuviste, Pos.CENTER);
        BorderPane.setMargin(textoObtuviste, new Insets(20, 0, 0, 0));
        this.setTop(textoObtuviste);

        String ruta = rutasCartas.get(nombreCarta);
        Image imagen = new Image(getClass().getResourceAsStream(ruta));
        ImageView imagenCarta = new ImageView(imagen);
        imagenCarta.setPreserveRatio(true);
        imagenCarta.setFitWidth(300);

        StackPane cartaConBrillos = new StackPane();
        cartaConBrillos.setAlignment(Pos.CENTER);
        cartaConBrillos.getChildren().add(imagenCarta);
        cartaConBrillos.getChildren().add(Brillos.crearBrillos());
        this.setCenter(cartaConBrillos);

        BotonJuego botonAceptar = new BotonJuego("Aceptar");
        botonAceptar.setOnAction(new VolverControlador(contenedor, vista));

        VBox botonVbox = new VBox(botonAceptar);
        botonVbox.setAlignment(Pos.CENTER);
        botonVbox.setSpacing(10);
        VBox.setMargin(botonAceptar, new Insets(0, 0, 10, 0));
        this.setBottom(botonVbox);

        Transicion.fade(this);
    }
}
