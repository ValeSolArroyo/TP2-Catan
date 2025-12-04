package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.vistas.componentes.*;
import edu.fiuba.algo3.vistas.componentes.botones.BotonGenerico;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VistaJuegoGeneral extends BorderPane {
    private Juego juego;
    private VistaTablero vistaTablero;

    public VistaJuegoGeneral(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego, VistaTablero vistaTablero){
        this.juego = juego;
        this.vistaTablero = vistaTablero;

        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo.jpg"));

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
        HBox.setMargin(this.vistaTablero, new Insets(0, 0, 7, 3));
        this.setCenter(tableroContenedor);

        VBox botonesDerecha = new VBox(50);
        botonesDerecha.setAlignment(Pos.CENTER_RIGHT);
        botonesDerecha.setPadding(new Insets(100, 20, 0, 0));

        BotonJuego botonComerciar = new BotonJuego("Comerciar");
        BotonJuego botonConstruir = new BotonJuego("Construir");
        BotonJuego botonComprarCartas = new BotonJuego("Comprar cartas");

        botonesDerecha.getChildren().addAll(botonComerciar, botonConstruir, botonComprarCartas);

        this.setRight(botonesDerecha);

        BotonJuego botonJugarCarta =  new BotonJuego("Jugar carta");
        HBox contenedorArribaIzquierda = new HBox(botonJugarCarta);
        //TODO: ver si cambiamos el padding una vez agregamos botones ladron..
        contenedorArribaIzquierda.setPadding(new Insets(30, 0, 0, 110));
        contenedorArribaIzquierda.setAlignment(Pos.CENTER_LEFT);

        this.setLeft(contenedorArribaIzquierda);

        CambioTurnoControlador cambioTurno = new CambioTurnoControlador(stage, contenedor, juego);
        cambioTurno.agregarTablero(this.vistaTablero);
        VistaTurnoActual vistaTurno = new VistaTurnoActual(cambioTurno);

        BotonGenerico botonFinTurno = new BotonGenerico("Finalizar turno", "boton-fin-turno", 230, 45);

        HBox contenedorAbajo = new HBox(20);
        contenedorAbajo.setAlignment(Pos.CENTER_LEFT);
        contenedorAbajo.setPadding(new Insets(0, 0, 20, 95));
        contenedorAbajo.getChildren().addAll(botonFinTurno, vistaTurno);

        HBox.setMargin(vistaTurno, new Insets(0, 0, 0, 200));

        this.setBottom(contenedorAbajo);

        botonFinTurno.setOnAction(e -> {
            cambioTurno.activarAccionFinTurno();
        });

        Transicion.fade(this);
    }
}
