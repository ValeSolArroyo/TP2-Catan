package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.controllers.fasesJuego.DadosControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.vistas.componentes.*;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaLanzarDados extends BorderPane {
    private Juego juego;
    private Stage stage;
    private ContenedorPrincipalVistas contenedor;
    private VistaTablero vistaTablero;

    public VistaLanzarDados(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego, VistaTablero vistaTablero) {
        this.juego = juego;
        this.stage = stage;
        this.contenedor = contenedor;
        this.vistaTablero = vistaTablero;

        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo_sin_cartas.jpg"));

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

        CambioTurnoControlador cambioTurno = new CambioTurnoControlador(stage, contenedor, juego);
        VistaTurnoActual vistaTurno = new VistaTurnoActual(cambioTurno);

        HBox turnoContenedor = new HBox(vistaTurno);
        turnoContenedor.setAlignment(Pos.CENTER);
        turnoContenedor.setPadding(new Insets(20));
        this.setBottom(turnoContenedor);
        HBox.setMargin(vistaTurno, new Insets(0, 0, 40, -80));

        VBox derecha = new VBox(30);
        derecha.setAlignment(Pos.TOP_CENTER);
        derecha.setPadding(new Insets(100, 20, 0, 0));

        VistaDados vistaDados = new VistaDados(juego.getDado());
        BotonJuego botonLanzarDados = new BotonJuego("Lanzar dados");

        derecha.getChildren().addAll(vistaDados, botonLanzarDados);
        this.setRight(derecha);

        DadosControlador dadosControlador = new DadosControlador(stage, juego, contenedor, this.vistaTablero);

        botonLanzarDados.setOnAction(e -> {
            dadosControlador.lanzarDados();
            vistaDados.actualizar();

            if (dadosControlador.seLanzaronDados()) {
                botonLanzarDados.setText("Continuar");
            }
        });
        Transicion.fade(this);
    }
}