package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.cartasDesarrollo.ProgresoDescubrimientoControlador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.Transicion;
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
            "Bosque", "/images/utils/hexagonos/hexagono_bosque.png",
            "Colina", "/images/utils/hexagonos/hexagono_colina.png",
            "Pastizal", "/images/utils/hexagonos/hexagono_pasto.png",
            "Campo", "/images/utils/hexagonos/hexagono_campo.png",
            "Montaña", "/images/utils/hexagonos/hexagono_montaña.png"
    );

    // TODO: mejorar este código, además quiero que el botón del número sea tipo:
    // Seleccionar
    // Y quizás abajo te puede aparecer: Recursos elegidos: Tipo, Tipo???

    // Sino que sea Seleccionar: 0
    // y si lo seleccionas pasa a 1?
    private Map<String, BotonJuego> botonesPorRecurso = new HashMap<>();
    private ProgresoDescubrimientoControlador controlador;
    private BotonJuego botonEjecutar;

    public VistaProgresoDescubrimiento(ProgresoDescubrimientoControlador controlador){
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        this.controlador = controlador;
        inicializarRecursos();

        botonEjecutar = new BotonJuego("Ejecutar");
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

        HBox hexagonosHBox = new HBox(40);
        hexagonosHBox.setAlignment(Pos.CENTER);

        // Madera
        Label labelMadera = new Label("Recurso: Madera");
        labelMadera.getStyleClass().add("texto-recurso-hexagono");

        ImageView imagenMadera = new ImageView(new Image(getClass().getResourceAsStream(rutasImagenes.get("Bosque"))));
        imagenMadera.setFitWidth(ANCHO);
        imagenMadera.setFitHeight(ALTO);
        imagenMadera.setPreserveRatio(false);

        BotonJuego botonMadera = new BotonJuego("0");
        botonesPorRecurso.put("Madera", botonMadera);
        botonMadera.setPrefSize(40, 40);

        botonMadera.setOnAction(e -> controlador.agregarMadera());

        VBox vboxMadera = new VBox(10, labelMadera, imagenMadera, botonMadera);
        vboxMadera.setAlignment(Pos.CENTER);

        hexagonosHBox.getChildren().addAll(vboxMadera);


        //Lana
        Label labelLana = new Label("Recurso:  Lana");
        labelLana.getStyleClass().add("texto-recurso-hexagono");

        ImageView imagenLana = new ImageView(new Image(getClass().getResourceAsStream(rutasImagenes.get("Pastizal"))));
        imagenLana.setFitWidth(ANCHO);
        imagenLana.setFitHeight(ALTO);
        imagenLana.setPreserveRatio(false);

        BotonJuego botonLana = new BotonJuego("0");
        botonesPorRecurso.put("Lana", botonLana);
        botonLana.setPrefSize(40, 40);

        botonLana.setOnAction(e -> controlador.agregarLana());

        VBox vboxLana = new VBox(10, labelLana, imagenLana, botonLana);
        vboxLana.setAlignment(Pos.CENTER);

        hexagonosHBox.getChildren().addAll(vboxLana);


        //Grano
        Label labelGrano = new Label("Recurso:  Grano");
        labelGrano.getStyleClass().add("texto-recurso-hexagono");

        ImageView imagenGrano = new ImageView(new Image(getClass().getResourceAsStream(rutasImagenes.get("Campo"))));
        imagenGrano.setFitWidth(ANCHO);
        imagenGrano.setFitHeight(ALTO);
        imagenGrano.setPreserveRatio(false);

        BotonJuego botonGrano = new BotonJuego("0");
        botonesPorRecurso.put("Grano", botonGrano);
        botonGrano.setPrefSize(40, 40);

        botonGrano.setOnAction(e -> controlador.agregarGrano());

        VBox vboxGrano = new VBox(10, labelGrano, imagenGrano, botonGrano);
        vboxGrano.setAlignment(Pos.CENTER);

        this.getChildren().add(vboxGrano);

        hexagonosHBox.getChildren().addAll(vboxGrano);



        //Mineral
        Label labelMineral = new Label("Recurso:  Mineral");
        labelMineral.getStyleClass().add("texto-recurso-hexagono");

        ImageView imagenMineral = new ImageView(new Image(getClass().getResourceAsStream(rutasImagenes.get("Montaña"))));
        imagenMineral.setFitWidth(ANCHO);
        imagenMineral.setFitHeight(ALTO);
        imagenMineral.setPreserveRatio(false);

        BotonJuego botonMineral = new BotonJuego("0");
        botonesPorRecurso.put("Mineral", botonMineral);
        botonMineral.setPrefSize(40, 40);

        botonMineral.setOnAction(e -> controlador.agregarMineral());

        VBox vboxMineral = new VBox(10, labelMineral, imagenMineral, botonMineral);
        vboxMineral.setAlignment(Pos.CENTER);

        this.getChildren().add(vboxMineral);

        hexagonosHBox.getChildren().addAll(vboxMineral);


        // Ladrillo
        Label labelLadrillo = new Label("Recurso:  Ladrillo");
        labelLadrillo.getStyleClass().add("texto-recurso-hexagono");

        ImageView imagenLadrillo = new ImageView(new Image(getClass().getResourceAsStream(rutasImagenes.get("Colina"))));
        imagenLadrillo.setFitWidth(ANCHO);
        imagenLadrillo.setFitHeight(ALTO);
        imagenLadrillo.setPreserveRatio(false);

        BotonJuego botonLadrillo = new BotonJuego("0");
        botonesPorRecurso.put("Ladrillo", botonLadrillo);
        botonLadrillo.setPrefSize(40, 40);

        botonLadrillo.setOnAction(e -> controlador.agregarLadrillo());

        VBox vboxLadrillo = new VBox(10, labelLadrillo, imagenLadrillo, botonLadrillo);
        vboxLadrillo.setAlignment(Pos.CENTER);

        hexagonosHBox.getChildren().addAll(vboxLadrillo);

        this.setCenter(hexagonosHBox);
    }


    public void sumarContador(String recurso){
        BotonJuego boton = botonesPorRecurso.get(recurso);
        int valor = Integer.parseInt(boton.getText());
        int nuevoValor = valor + 1;
        boton.setText(" " + nuevoValor + " ");
    }


    public void desactivarBotones() {
        for (BotonJuego boton : botonesPorRecurso.values()){
            boton.setDisable(true);
            boton.setOpacity(0);
        }
    }

    public void activarBotonEjecutar() {
        botonEjecutar.setDisable(false);
        botonEjecutar.setOpacity(1);
    }
}
