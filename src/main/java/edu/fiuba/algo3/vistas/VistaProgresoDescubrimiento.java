package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.cartasDesarrollo.ProgresoDescubrimientoControlador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import edu.fiuba.algo3.vistas.componentes.botones.BotonGenerico;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class VistaProgresoDescubrimiento extends BorderPane {
    private static final double ANCHO = 110;
    private static final double ALTO  = 145;
    private static final Map<String, String> rutasImagenes = Map.of(
            "Bosque", "/images/utils/hexagonos/hexagono_Madera.png",
            "Colina", "/images/utils/hexagonos/hexagono_Ladrillo.png",
            "Pastizal", "/images/utils/hexagonos/hexagono_Lana.png",
            "Campo", "/images/utils/hexagonos/hexagono_Grano.png",
            "Montaña", "/images/utils/hexagonos/hexagono_Mineral.png"
    );

    private Map<String, BotonGenerico> botonesPorRecurso = new HashMap<>();
    private ProgresoDescubrimientoControlador controlador;
    private BotonJuego botonEjecutar;

    public VistaProgresoDescubrimiento(ProgresoDescubrimientoControlador controlador){
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        this.controlador = controlador;
        controlador.setVistaProgreso(this);

        inicializarRecursos();

        // TODO: estaría buieno si podemos que se pueda poner volver, y que lo deje
        // volver a elegir. detalle igual
        botonEjecutar = new BotonJuego("Confirmar");
        botonEjecutar.setOnAction(e -> controlador.ejecutar());
        botonEjecutar.setDisable(true);
        botonEjecutar.setOpacity(0);

        VBox botonVbox = new VBox(botonEjecutar);
        botonVbox.setAlignment(Pos.CENTER);
        botonVbox.setSpacing(10);
        VBox.setMargin(botonEjecutar, new Insets(0, 0, 10, 0));
        this.setBottom(botonVbox);

        Transicion.fade(this);
    }

    private void inicializarRecursos() {
        HBox hbox = new HBox(40);
        hbox.setAlignment(Pos.CENTER);

        for (String tipo : controlador.getTiposDeRecurso()) {
            Label label = new Label("Recurso: " + tipo);
            label.getStyleClass().add("texto-recurso-hexagono");
            String ruta = "/images/utils/hexagonos/hexagono_" + tipo + ".png";
            ImageView imagen = new ImageView(new Image(getClass().getResourceAsStream(ruta)));
            imagen.setFitWidth(ANCHO);
            imagen.setFitHeight(ALTO);

            BotonGenerico boton = new BotonGenerico("Seleccionar: 0", "botones-progreso", 230, 80);
            botonesPorRecurso.put(tipo, boton);
            boton.setOnAction(e -> controlador.agregarRecurso(tipo));

            VBox vbox = new VBox(10, label, imagen, boton);
            vbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(vbox);
        }

        this.setCenter(hbox);
    }


    public void sumarContador(String recurso){
        BotonGenerico boton = botonesPorRecurso.get(recurso);
        int valor = Integer.parseInt(boton.getText().replace("Seleccionar: ", "").trim());
        int nuevoValor = valor + 1;
        boton.setText("Seleccionar: " + nuevoValor);
    }


    public void desactivarBotones() {
        for (BotonGenerico boton : botonesPorRecurso.values()){
            boton.setDisable(true);
            boton.setOpacity(0);
        }
    }

    public void activarBotonEjecutar() {
        botonEjecutar.setDisable(false);
        botonEjecutar.setOpacity(1);
    }
}
