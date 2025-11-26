package edu.fiuba.algo3.controllers;


import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
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
                mostrarError("Todos los jugadores deben tener nombre.");
                return;
            }
            if (nombre.length() > 15) {
                mostrarError("Cada nombre puede máximo 15 caracteres.");
                return;
            }

            listaNombres.add(nombre);
            listaColores.add(color);
        }

        Set<String> chequeoRepetidos = new HashSet<>();
        for (String color : listaColores) {
            if (!chequeoRepetidos.add(color)) {
                mostrarError("Todos los jugadores deben elegir colores distintos.");
                return;
            }
        }

        // TODO: inicializar los jugadores en el modelo!

        System.out.println(listaNombres);
        System.out.println(listaColores);

        VistaJuegoGeneral vistaJuego = new VistaJuegoGeneral(stage, contenedor);
        contenedor.setContenido(vistaJuego);
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error!!");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
