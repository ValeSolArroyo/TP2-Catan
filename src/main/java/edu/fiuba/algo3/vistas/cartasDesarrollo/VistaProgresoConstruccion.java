package edu.fiuba.algo3.vistas.cartasDesarrollo;

import edu.fiuba.algo3.controllers.cartasDesarrollo.ProgresoConstruccionControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.InfoJugador;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import edu.fiuba.algo3.vistas.componentes.botones.BotonGenerico;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class VistaProgresoConstruccion extends BorderPane {

    private Juego juego;
    private BotonJuego botonEjecutar;
    private BotonGenerico botonSeleccionar;
    private BotonJuego botonFinalizar;
    private VistaTablero vistaTablero;
    private ProgresoConstruccionControlador controlador;


    public VistaProgresoConstruccion(Juego juego, VistaTablero vistaTablero,  ProgresoConstruccionControlador controlador){
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.controlador = controlador;
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo_puertos_sin_cartas.jpg"));

        HBox barraJugadores = new HBox(20);
        barraJugadores.setAlignment(Pos.CENTER);

        for (Jugador jugador : juego.getJugadores()) {
            InfoJugador info = new InfoJugador(jugador, juego);
            HBox.setMargin(info, new Insets(0, 10, 0, 10));
            barraJugadores.getChildren().add(info);
        }

        this.setTop(barraJugadores);

        HBox tableroContenedor = new HBox(20);
        tableroContenedor.setAlignment(Pos.CENTER);
        tableroContenedor.getChildren().add(this.vistaTablero);
        HBox.setMargin(this.vistaTablero, new Insets(0, 0, 70, 350));
        this.setCenter(tableroContenedor);

        inicializarBotones();

        Transicion.fade(this);
    }

    private void inicializarBotones() {
        VBox botonDerecha = new VBox(50);
        botonDerecha.setAlignment(Pos.CENTER_RIGHT);
        botonDerecha.setPadding(new Insets(100, 10, 0, 0));

        botonSeleccionar = new BotonGenerico("Elegir Carretera", "botones-derecha", 250, 70);
        botonEjecutar = new BotonJuego("Construir");
        botonFinalizar = new BotonJuego("Finalizar");

        botonDerecha.getChildren().addAll(botonSeleccionar, botonEjecutar, botonFinalizar);

        this.setRight(botonDerecha);

        botonSeleccionar.setOnAction(e -> controlador.activarAristas());
        botonEjecutar.setOnAction(e -> controlador.ejecutar());
        botonFinalizar.setOnAction(e -> controlador.finalizarCarta());

        activarBotonElegir();
    }

    public void activarBotonElegir(){
        botonSeleccionar.setDisable(false);
        botonSeleccionar.setOpacity(1);
        desactivarBotonEjecutar();
        desactivarBotonFinalizar();
    }

    public void desactivarBotonElegir(){
        botonSeleccionar.setDisable(true);
        botonSeleccionar.setOpacity(0);
    }

    public void activarBotonEjecutar(){
        botonEjecutar.setDisable(false);
        botonEjecutar.setOpacity(1);
        desactivarBotonFinalizar();
    }

    public void desactivarBotonEjecutar(){
        botonEjecutar.setDisable(true);
        botonEjecutar.setOpacity(0);
    }

    public void activarBotonFinalizar(){
        botonFinalizar.setDisable(false);
        botonFinalizar.setOpacity(1);
    }

    public void desactivarBotonFinalizar(){
        botonFinalizar.setDisable(true);
        botonFinalizar.setOpacity(0);
    }
}
