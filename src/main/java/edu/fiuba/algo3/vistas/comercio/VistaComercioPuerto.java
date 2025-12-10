package edu.fiuba.algo3.vistas.comercio;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.controllers.comercio.ComercioPuertoControlador;
import edu.fiuba.algo3.controllers.VolverControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.*;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;

public class VistaComercioPuerto extends BorderPane {
    private Juego juego;
    private ContenedorPrincipalVistas contenedor;
    private ComercioPuertoControlador controlador;
    private VistaTablero vistaTablero;
    private VistaJuegoGeneral vistaJuego;

    public VistaComercioPuerto(ComercioPuertoControlador controlador, ContenedorPrincipalVistas contenedor, Juego juego, VistaTablero vistaTablero, VistaJuegoGeneral vistaJuego) {
        this.juego = juego;
        this.contenedor = contenedor;
        this.controlador = controlador;
        this.vistaTablero = vistaTablero;
        this.vistaJuego = vistaJuego;

        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo_puertos_sin_cartas.jpg"));

        HBox barraJugadores = new HBox(20);
        barraJugadores.setAlignment(Pos.CENTER);
        barraJugadores.setPadding(new Insets(40, 0, 0, 0));
        for (var jugador : juego.getJugadores()) {
            InfoJugador info = new InfoJugador(jugador, juego);
            HBox.setMargin(info, new Insets(2, 10, 2, 10));
            barraJugadores.getChildren().add(info);
        }
        this.setTop(barraJugadores);

        HBox tableroContenedor = new HBox(15);
        tableroContenedor.setAlignment(Pos.CENTER);
        tableroContenedor.getChildren().add(this.vistaTablero);
        HBox.setMargin(this.vistaTablero, new Insets(4, 0, 0, 325));
        this.setCenter(tableroContenedor);

        CambioTurnoControlador cambioTurno = new CambioTurnoControlador(contenedor, juego);
        VistaTurnoActual vistaTurno = new VistaTurnoActual(cambioTurno);

        HBox turnoContenedor = new HBox(vistaTurno);
        turnoContenedor.setAlignment(Pos.CENTER);
        turnoContenedor.setPadding(new Insets(20));
        this.setBottom(turnoContenedor);
        HBox.setMargin(vistaTurno, new Insets(0, 0, 40, -80));

        VBox derecha = new VBox(30);
        derecha.setAlignment(Pos.TOP_CENTER);
        derecha.setPadding(new Insets(100, 20, 0, 0));
        BotonJuego botonElegirPuerto = new BotonJuego("Elegir puerto");
        BotonJuego botonComerciar = new BotonJuego("Comerciar");
        derecha.getChildren().addAll(botonElegirPuerto, botonComerciar);
        this.setRight(derecha);

        Transicion.fade(this);
    }
}
