package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.CambioTurnoControlador;
import edu.fiuba.algo3.controllers.fasesJuego.DadosControlador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.tablero.Tablero;
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

    public VistaLanzarDados(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego) {
        this.juego = juego;
        this.stage = stage;
        this.contenedor = contenedor;

        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/fondo_sin_cartas.jpg"));

        HBox barraJugadores = new HBox(20);
        barraJugadores.setAlignment(Pos.CENTER);
        for (var jugador : juego.getJugadores()) {
            InfoJugador info = new InfoJugador(jugador);
            HBox.setMargin(info, new Insets(0, 10, 0, 10));
            barraJugadores.getChildren().add(info);
        }
        this.setTop(barraJugadores);

        HBox tableroContenedor = new HBox(20);
        tableroContenedor.setAlignment(Pos.CENTER);
        Tablero tablero = juego.getTablero();
        VistaTablero vistaTablero = new VistaTablero(tablero);
        tableroContenedor.getChildren().add(vistaTablero);
        HBox.setMargin(vistaTablero, new Insets(7, 0, 0, 325));
        this.setCenter(tableroContenedor);

        CambioTurnoControlador cambioTurno = new CambioTurnoControlador(stage, contenedor, juego);
        VistaTurnoActual vistaTurno = new VistaTurnoActual(cambioTurno);

        HBox turnoContenedor = new HBox(vistaTurno);
        turnoContenedor.setAlignment(Pos.CENTER);
        turnoContenedor.setPadding(new Insets(20));
        this.setBottom(turnoContenedor);

        VBox derecha = new VBox(30);
        derecha.setAlignment(Pos.TOP_CENTER);
        derecha.setPadding(new Insets(150, 20, 0, 0));

        VistaDados vistaDados = new VistaDados(juego.getDado());
        BotonJuego botonLanzarDados = new BotonJuego("Lanzar dados");

        derecha.getChildren().addAll(vistaDados, botonLanzarDados);
        this.setRight(derecha);

        DadosControlador dadosControlador = new DadosControlador(stage, juego, contenedor);

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
