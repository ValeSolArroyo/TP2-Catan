package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.TableroControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.InfoJugador;
import edu.fiuba.algo3.vistas.componentes.Transicion;
import edu.fiuba.algo3.vistas.componentes.botones.BotonGenerico;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VistaColocacionesIniciales extends BorderPane {
    private Juego juego;

    public VistaColocacionesIniciales(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo_sin_cartas.jpg"));

        HBox barraJugadores = new HBox(20);
        barraJugadores.setAlignment(Pos.CENTER);

        for (Jugador jugador : juego.getJugadores()) {
            InfoJugador info = new InfoJugador(jugador);
            HBox.setMargin(info, new Insets(0, 10, 0, 10));
            barraJugadores.getChildren().add(info);
        }

        this.setTop(barraJugadores);

        TableroControlador controladorTablero = new TableroControlador(juego.getTablero());
        Pane vistaTablero = controladorTablero.mostrarTablero();
        this.setCenter(vistaTablero);

        ConstruccionControlador construccionControlador = new ConstruccionControlador(juego);

        // Para cada hexágono, agregamos botones de construcción
        for (javafx.scene.Node node : vistaTablero.getChildrenUnmodifiable()) {
            if (node instanceof VBox) {
                VBox fila = (VBox) node;
                for (javafx.scene.Node hexNode : fila.getChildren()) {
                    if (hexNode instanceof VistaHexagono) {
                        ((VistaHexagono) hexNode).agregarBotonesConstruccion(construccionControlador, juego.getJugadorActual());
                    }
                }
            }
        }

        VBox botonesColocar = new VBox(50);
        botonesColocar.setAlignment(Pos.CENTER_LEFT);
        botonesColocar.setPadding(new Insets(100, 0, 0, 20));

        BotonJuego botonColocar = new BotonJuego("Primera colocacion");
        botonesColocar.getChildren().addAll(botonColocar);
        this.setLeft(botonesColocar);

        BotonGenerico botonFinTurno = new BotonGenerico("Finalizar turno", "boton-fin-turno", 230, 45);
        HBox contenedorAbajoIzquierda = new HBox(botonFinTurno);
        contenedorAbajoIzquierda.setPadding(new Insets(0, 0, 20, 95));
        StackPane.setAlignment(botonFinTurno, Pos.BOTTOM_LEFT);
        this.setBottom(contenedorAbajoIzquierda);

        Transicion.fade(this);
    }
}
