package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.cartasBonificacion.CartaBonificacion;
import edu.fiuba.algo3.modelo.cartasBonificacion.GranCaballeria;
import edu.fiuba.algo3.modelo.cartasBonificacion.GranRutaComercial;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Map;
import edu.fiuba.algo3.modelo.observer.Observador;
import javafx.scene.paint.Color;

public class InfoJugador extends VBox implements Observador {
    private Jugador jugador;
    private Label nombreLabel;
    private Label puntosLabel;
    private Label recursosLabel;
    private Juego juego;
    private HBox bonificaciones;
    private ImageView caballeria;
    private ImageView rutaComercial;

    public InfoJugador(Jugador jugador, Juego juego) {
        this.jugador = jugador;
        this.juego = juego;
        jugador.agregarObservador(this);

        this.setPadding(new Insets(0, 20, 10, 20));
        this.getStyleClass().add("info-jugador");

        Color colorJugador = jugador.getColor();
        this.setBorder(new Border(new BorderStroke(colorJugador, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));

        nombreLabel = new Label(jugador.getNombre());
        puntosLabel = new Label("");
        Label recursosTituloLabel = new Label("Recursos:");
        recursosLabel = new Label("");

        nombreLabel.getStyleClass().add("nombre-jugador");
        puntosLabel.getStyleClass().add("pv-jugador");
        recursosTituloLabel.getStyleClass().add("titulo-recursos-jugador");
        recursosLabel.getStyleClass().add("recursos-jugador");

        HBox filaNombreYPV = new HBox();
        filaNombreYPV.setSpacing(60);
        filaNombreYPV.getChildren().addAll(nombreLabel, puntosLabel);

        bonificaciones = new HBox();
        bonificaciones.setAlignment(Pos.CENTER_RIGHT);

        caballeria = new ImageView(getClass().getResource("/images/utils/bonificacion/caballero.png").toExternalForm());
        caballeria.setFitHeight(25);
        caballeria.setPreserveRatio(true);

        rutaComercial = new ImageView(getClass().getResource("/images/utils/bonificacion/carretera.png").toExternalForm());
        rutaComercial.setFitHeight(25);
        rutaComercial.setPreserveRatio(true);

        bonificaciones.getChildren().setAll(caballeria, rutaComercial);

        HBox filaRecursos = new HBox();
        filaRecursos.setAlignment(Pos.CENTER_LEFT);
        filaRecursos.getChildren().addAll(recursosLabel, bonificaciones);

        this.getChildren().addAll(filaNombreYPV, recursosTituloLabel, filaRecursos);

        ocultarImagenCaballeria();
        ocultarImagenRutaComercial();
        actualizar();
    }

    private String formatearRecursos() {
        Map<String, Integer> recursos = jugador.getRecursosInventario();
        String resultado = "";
        int contador = 0;

        for (String nombre : recursos.keySet()) {
            int cantidad = recursos.get(nombre);
            resultado += nombre + ": " + cantidad;
            contador++;

            if (contador % 3 == 0) {
                resultado += "\n";
            } else if (contador < recursos.size()) {
                resultado += ", ";
            }
        }

        return resultado;
    }

    public void actualizar() {
        if (this.jugador == juego.jugadorActual()) {
            if (jugador.getPuntosVictoriaCartas() > 0) {
                puntosLabel.setText("PV: " + jugador.getPuntosVictoria() + " (+" + jugador.getPuntosVictoriaCartas() + ")");
            } else {
                puntosLabel.setText("PV: " + jugador.getPuntosVictoria());
            }
            recursosLabel.setText(formatearRecursos());
        } else {
            puntosLabel.setText("PV: " + jugador.getPuntosVictoria());
            String recursosTexto = formatearRecursos();
            String espacios = recursosTexto.replaceAll(".", "-");
            recursosLabel.setText(espacios);
        }

        for (CartaBonificacion carta : jugador.getCartasBonificacion()) {
            if (carta.getClass() == GranCaballeria.class) {
                mostrarImagenCaballeria();
            } else {
                ocultarImagenCaballeria();
            }

            if (carta.getClass() == GranRutaComercial.class) {
                mostrarImagenRutaComercial();
            } else {
                ocultarImagenRutaComercial();
            }
        }
    }

    public void ocultarImagenCaballeria() {
        this.caballeria.setOpacity(0);
    }

    public void mostrarImagenCaballeria() {
        this.caballeria.setOpacity(1);
    }

    public void ocultarImagenRutaComercial() {
        this.rutaComercial.setOpacity(0);
    }

    public void mostrarImagenRutaComercial() {
        this.rutaComercial.setOpacity(1);
    }

}
