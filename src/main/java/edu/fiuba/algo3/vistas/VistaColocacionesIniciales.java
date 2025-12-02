package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.TableroControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.InfoJugador;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import edu.fiuba.algo3.vistas.componentes.botones.BotonGenerico;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VistaColocacionesIniciales extends BorderPane  {
    public VistaColocacionesIniciales(Juego juego) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo_sin_cartas.jpg"));
        HBox barraJugadores = new HBox(20);
        barraJugadores.setAlignment(Pos.CENTER);

        for (Jugador jugador : juego.getJugadores()) {
            InfoJugador info = new InfoJugador(jugador);
            HBox.setMargin(info, new Insets(0, 10, 0, 10));
            barraJugadores.getChildren().add(info);
        }

        this.setTop(barraJugadores);

        Tablero tablero = juego.getTablero();
        VistaTablero vistaTablero = new VistaTablero(tablero);
        TableroControlador controlador = new TableroControlador(tablero, vistaTablero);

        HBox contenedorCentro = new HBox(vistaTablero);
        contenedorCentro.setPadding(new Insets(0, 0, 0, 425));

        this.setCenter(contenedorCentro);

        VBox botonesDerecha = new VBox(50);
        botonesDerecha.setAlignment(Pos.CENTER_RIGHT);
        botonesDerecha.setPadding(new Insets(100, 20, 0, 0));

        BotonJuego botonPoblado = new BotonJuego("Colocar poblado");
        BotonJuego botonCarretera = new BotonJuego("Colocar carretera");

        botonesDerecha.getChildren().addAll(botonPoblado, botonCarretera);
        this.setRight(botonesDerecha);

        BotonGenerico botonFinTurno = new BotonGenerico("Finalizar turno", "boton-fin-turno", 230, 45);

        HBox contenedorAbajoIzquierda = new HBox(botonFinTurno);
        contenedorAbajoIzquierda.setPadding(new Insets(0, 0, 20, 95));

        this.setBottom(contenedorAbajoIzquierda);

        botonPoblado.setOnAction(e -> controlador.activarVertices());
        botonCarretera.setOnAction(e -> controlador.activarAristas());
        botonFinTurno.setOnAction(e -> controlador.desactivarTodo());

        Transicion.fade(this);
    }
}
