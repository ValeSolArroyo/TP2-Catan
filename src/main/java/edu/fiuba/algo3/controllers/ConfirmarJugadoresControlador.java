package edu.fiuba.algo3.controllers;


import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaColocacionesIniciales;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.VistaLanzarDados;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public class ConfirmarJugadoresControlador implements EventHandler<ActionEvent> {
    private ContenedorPrincipalVistas contenedor;
    private List<TextField> nombres;
    private List<ComboBox<String>> colores;
    private static final Map<String, Color> opcionesColores = Map.of("Amarillo", Color.YELLOW,
            "Magenta", Color.MAGENTA, "Azul", Color.BLUE, "Rosa", Color.PINK,
            "Rojo", Color.RED, "Naranja", Color.ORANGE);

    public ConfirmarJugadoresControlador(ContenedorPrincipalVistas contenedor, List<TextField> nombres, List<ComboBox<String>> colores) {
        this.contenedor = contenedor;
        this.nombres = nombres;
        this.colores = colores;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        List<String> listaNombres = new ArrayList<>();
        List<String> listaColores = new ArrayList<>();

        for (int i = 0; i < nombres.size(); i++) {
            String nombre = nombres.get(i).getText().trim();
            String color = colores.get(i).getValue();
            if (nombre.isEmpty()) {
                PopUpError.mostrar("Todos los jugadores deben tener nombre.");
                return;
            }
            if (nombre.length() > 10) {
                PopUpError.mostrar("Máximo 10 caracteres por nombre.");
                return;
            }

            listaNombres.add(nombre);
            listaColores.add(color);
        }

        Set<String> chequeoRepetidos = new HashSet<>();
        for (String color : listaColores) {
            if (!chequeoRepetidos.add(color)) {
                PopUpError.mostrar("Deben elegirse colores distintos.");
                return;
            }
        }

        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < listaNombres.size(); i++) {
            String nombre = listaNombres.get(i);
            Color color = opcionesColores.get(listaColores.get(i));
            Jugador jugador = new Jugador(i, nombre, color);
            jugadores.add(jugador);
        }

        IniciarJuegoControlador iniciar = new IniciarJuegoControlador();
        Juego juego = iniciar.crearNuevaPartida(jugadores);

        CambioTurnoControlador cambioTurno = new CambioTurnoControlador( contenedor, juego);
        Tablero tablero = juego.getTablero();
        VistaTablero vistaTablero = new VistaTablero(tablero);
        VistaColocacionesIniciales vistaJuego = new VistaColocacionesIniciales(contenedor,juego, cambioTurno, vistaTablero);
        contenedor.setContenido(vistaJuego);
    }
}
