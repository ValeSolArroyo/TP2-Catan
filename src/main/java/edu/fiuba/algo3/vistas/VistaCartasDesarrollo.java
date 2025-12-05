package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CartasDesarrolloControlador;
import edu.fiuba.algo3.controllers.VolverControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

public class VistaCartasDesarrollo extends BorderPane {
    private static final Map<String, String> rutasCartas = Map.of(
            "Caballero", "/images/utils/cartas/caballero.png",
            "Progreso de Construccion", "/images/utils/cartas/construccion_carretera.png",
            "Progreso de Descubrimiento", "/images/utils/cartas/descubrimiento.png",
            "Progreso Monopolio", "/images/utils/cartas/monopolio.png"

    );

    public VistaCartasDesarrollo(Juego juego, ContenedorPrincipalVistas contenedor, Map<String, Integer> contadorCartas, VistaJuegoGeneral vistaJuego, CartasDesarrolloControlador controlador) {

        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));

        HBox cartasHBox = new HBox(40);
        cartasHBox.setAlignment(Pos.CENTER);

        for (String nombreCarta : rutasCartas.keySet()) {
            int cantidad = contadorCartas.get(nombreCarta);
            VBox cartaVBox = crearVistaCarta(nombreCarta, cantidad, controlador);
            cartasHBox.getChildren().addAll(cartaVBox);
        }

        this.setCenter(cartasHBox);

        BotonJuego botonVolver = new BotonJuego("Volver");
        botonVolver.setOnAction(new VolverControlador(contenedor, vistaJuego));

        VBox botonVbox = new VBox(botonVolver);
        botonVbox.setAlignment(Pos.CENTER);
        botonVbox.setSpacing(10);
        VBox.setMargin(botonVolver, new Insets(0, 0, 10, 0));
        this.setBottom(botonVbox);

        Transicion.fade(this);

    }

    private VBox crearVistaCarta(String nombreCarta, int cantidad, CartasDesarrolloControlador controlador) {

        String ruta = rutasCartas.get(nombreCarta);
        Image img = new Image(getClass().getResourceAsStream(ruta));
        ImageView imgView = new ImageView(img);

        imgView.setPreserveRatio(true);
        imgView.setFitWidth(160);


        BotonJuego botonJugar = new BotonJuego( " " + cantidad + "");

        if (cantidad == 0) {
            botonJugar.setDisable(true);
        }


        botonJugar.setOnAction(e -> {
            switch (nombreCarta) {
                case "Caballero":
                    controlador.jugarCaballero();
                    break;

                case "Progreso de Construccion":
                    controlador.jugarProgresoConstruccion();
                    break;

                case "Progreso de Descubrimiento":
                    controlador.jugarProgresoDescubrimiento();
                    break;

                case "Progreso Monopolio":
                    controlador.jugarMonopolio();
                    break;

            }
        });

        VBox vbox = new VBox(10, imgView, botonJugar);
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }
}





