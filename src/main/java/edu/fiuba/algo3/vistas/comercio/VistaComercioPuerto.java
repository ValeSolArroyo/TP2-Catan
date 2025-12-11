package edu.fiuba.algo3.vistas.comercio;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.controllers.VolverControlador;
import edu.fiuba.algo3.controllers.comercio.ComercioPuertoControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.*;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Map;

public class VistaComercioPuerto extends BorderPane {
    private Juego juego;
    private ContenedorPrincipalVistas contenedor;
    private ComercioPuertoControlador controlador;
    private VistaTablero vistaTablero;
    private VistaJuegoGeneral vistaJuego;
    private Label textoPuerto;
    private BotonJuego botonConfirmar;
    private BotonJuego botonElegirPuerto;
    private BotonJuego botonVolver;

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
        HBox.setMargin(this.vistaTablero, new Insets(8, 0, 0, 323));

        textoPuerto = new Label("");
        textoPuerto.getStyleClass().add("texto-puerto");

        HBox texto = new HBox(textoPuerto);
        texto.setAlignment(Pos.CENTER_LEFT);
        texto.setPadding(new Insets(0, 0, 0, 40));
        texto.setMouseTransparent(true);
        texto.setPickOnBounds(false);

        StackPane tableroYTexto = new StackPane(tableroContenedor, texto);

        this.setCenter(tableroYTexto);


        CambioTurnoControlador cambioTurno = new CambioTurnoControlador(contenedor, juego);
        VistaTurnoActual vistaTurno = new VistaTurnoActual(cambioTurno);

        HBox turnoContenedor = new HBox(vistaTurno);
        turnoContenedor.setAlignment(Pos.CENTER);
        turnoContenedor.setPadding(new Insets(20));
        this.setBottom(turnoContenedor);
        HBox.setMargin(vistaTurno, new Insets(0, 0, 36, -78));

        VBox derecha = new VBox(30);
        derecha.setAlignment(Pos.TOP_CENTER);
        derecha.setPadding(new Insets(100, 20, 0, 0));
        botonElegirPuerto = new BotonJuego("Elegir puerto");
        botonElegirPuerto.setOnAction(e -> controlador.elegirPuerto());

        botonConfirmar = new BotonJuego("Confirmar");

        botonVolver = new BotonJuego("Volver");
        botonVolver.setOnAction(e -> {
            vistaTablero.ocultarVertices();
            VistaJuegoGeneral nuevaVistaJuego = new VistaJuegoGeneral(contenedor, juego, this.vistaTablero);
            contenedor.setContenido(nuevaVistaJuego);
        });
        derecha.getChildren().addAll(botonElegirPuerto, botonConfirmar, botonVolver);
        botonConfirmar.setDisable(true);
        botonConfirmar.setOnAction(e -> controlador.confirmarPuerto());

        this.setRight(derecha);

        Transicion.fade(this);
    }

    public void actualizarTexto(String texto) {
        textoPuerto.setText(texto);
        textoPuerto.setPadding(new Insets(8, 12, 8, 12));
    }

    public void activarBotonConfirmar(){
        this.botonConfirmar.setDisable(false);
    }

    public void desactivarBotonConfirmar(){
        this.botonConfirmar.setDisable(true);
    }

    public void activarBotonElegir() {
        this.botonElegirPuerto.setDisable(false);
    }

    public void desactivarBotonElegir() {
        this.botonElegirPuerto.setDisable(true);
    }

    public void desactivarTexto() {
        this.textoPuerto.setOpacity(0);
        this.textoPuerto.setText("");
        textoPuerto.setPadding(new Insets(0));
    }

    public void activarTexto() {
        this.textoPuerto.setOpacity(1);
    }
}
