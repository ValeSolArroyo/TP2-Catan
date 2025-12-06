package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.cartasDesarrollo.ProgresoConstruccionControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.InfoJugador;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class VistaProgresoConstruccion extends BorderPane {

    private Juego juego;
    private BotonJuego botonEjecutar;
    private BotonJuego botonAristas;
    private VistaTablero vistaTablero;
    private ProgresoConstruccionControlador controlador;


    public VistaProgresoConstruccion(Juego juego, VistaTablero vistaTablero,  ProgresoConstruccionControlador controlador){
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.controlador = controlador;
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo_sin_cartas.jpg"));

        HBox barraJugadores = new HBox(20);
        barraJugadores.setAlignment(Pos.CENTER);

        for (Jugador jugador : juego.getJugadores()) {
            InfoJugador info = new InfoJugador(jugador);
            HBox.setMargin(info, new Insets(0, 10, 0, 10));
            barraJugadores.getChildren().add(info);
        }

        this.setTop(barraJugadores);

        HBox tableroContenedor = new HBox(20);
        tableroContenedor.setAlignment(Pos.CENTER);
        tableroContenedor.getChildren().add(this.vistaTablero);
        HBox.setMargin(this.vistaTablero, new Insets(0, 0, 7, 100));
        this.setCenter(tableroContenedor);

        inicializarBotones();

        Transicion.fade(this);


    }

    private void inicializarBotones() {
        VBox botonDerecha = new VBox(50);
        botonDerecha.setAlignment(Pos.CENTER_RIGHT);
        botonDerecha.setPadding(new Insets(100, 20, 0, 0));
        botonAristas = new BotonJuego("Elegir Carreteras");
        botonEjecutar = new BotonJuego("Ejecutar");

        botonDerecha.getChildren().addAll(botonAristas, botonEjecutar);

        this.setRight(botonDerecha);

        botonAristas.setOnAction(e -> controlador.activarAristas());
        botonEjecutar.setOnAction(e -> controlador.ejecutar());

        botonAristas.setDisable(false);
        botonEjecutar.setDisable(true);
        botonEjecutar.setOpacity(0);
    }

    public void desactivarBoton(){
        botonAristas.setDisable(true);
        botonAristas.setOpacity(0);
    }

    public void activarBotonEjecutar(){
        botonEjecutar.setDisable(false);
        botonEjecutar.setOpacity(1);
    }

}
