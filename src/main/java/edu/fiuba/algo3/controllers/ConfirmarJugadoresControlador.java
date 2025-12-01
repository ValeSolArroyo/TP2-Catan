package edu.fiuba.algo3.controllers;


import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public class ConfirmarJugadoresControlador implements EventHandler<ActionEvent> {
    private Stage stage;
    private ContenedorPrincipalVistas contenedor;
    private List<TextField> nombres;
    private List<ComboBox<String>> colores;

    public ConfirmarJugadoresControlador(Stage stage, ContenedorPrincipalVistas contenedor, List<TextField> nombres, List<ComboBox<String>> colores) {
        this.stage = stage;
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
                PopUpError.mostrar("Todos los jugadores deben elegir colores distintos.");
                return;
            }
        }

        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < listaNombres.size(); i++) {
            Jugador jugador = new Jugador(i, listaNombres.get(i), listaColores.get(i));
            jugadores.add(jugador);
        }

        IniciarJuegoControlador iniciar = new IniciarJuegoControlador();
        Juego juego = iniciar.crearNuevaPartida(jugadores);

        VistaJuegoGeneral vistaJuego = new VistaJuegoGeneral(stage, contenedor, juego);
        contenedor.setContenido(vistaJuego);
    }
}
