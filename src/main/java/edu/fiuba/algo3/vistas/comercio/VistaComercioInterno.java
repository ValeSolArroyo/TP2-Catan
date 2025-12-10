package edu.fiuba.algo3.vistas.comercio;

import edu.fiuba.algo3.controllers.VolverControlador;
import edu.fiuba.algo3.controllers.comercio.ComercioInternoControlador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.FondoPantalla;
import edu.fiuba.algo3.vistas.componentes.ListadoRecurso;
import edu.fiuba.algo3.vistas.componentes.botones.BotonGenerico;
import edu.fiuba.algo3.vistas.componentes.botones.BotonJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaComercioInterno extends BorderPane {
    private ComercioInternoControlador controlador;
    private List<Recurso> recursos;
    private BotonJuego botonConfirmar;
    private Map<Recurso, BotonGenerico> botonesPorRecursoAEntregar;
    private Map<Recurso, BotonGenerico> botonesPorRecursoARecibir;
    private Map<String, Integer> recursosJugadorActual;

    public VistaComercioInterno(ContenedorPrincipalVistas contenedor, ComercioInternoControlador controlador, List<Recurso> recursos, VistaJuegoGeneral vista, Map<String, Integer> recursosJugadorActual) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        this.controlador = controlador;
        this.recursos = recursos;
        this.recursosJugadorActual = recursosJugadorActual;
        botonesPorRecursoAEntregar = new HashMap<>();
        botonesPorRecursoARecibir = new HashMap<>();

        VBox centro = new VBox(20);
        centro.setPadding(new Insets(40, 20, 40, 20));
        centro.setAlignment(Pos.TOP_CENTER);

        Label titulo = new Label("Intercambio con otro jugador: haz tu oferta.");

        HBox hboxBanca = new HBox(40);
        hboxBanca.setAlignment(Pos.CENTER);

        Label textoEntregar = new Label("Elige el recurso que quieres entregar en el intercambio:");

        HBox hboxEntrega = new HBox(40);
        hboxEntrega.setAlignment(Pos.CENTER);

        for (Recurso recurso : recursos) {
            String nombreRecurso = recurso.getNombreRecurso();
            int cantidad = recursosJugadorActual.get(nombreRecurso);
            String ruta = "/images/utils/recursos/" + nombreRecurso + ".png";

            Label textoRecurso = new Label("¡Tienes " + cantidad + "!");

            ListadoRecurso listadoRecurso = new ListadoRecurso(recurso.getNombreRecurso(), ruta);

            BotonGenerico boton = new BotonGenerico("Cantidad: 0", "botones-progreso", 230, 80);
            boton.setOnAction(e -> controlador.obtenerRecursosAEntregar(recurso));
            boton.setDisable(true);
            botonesPorRecursoAEntregar.put(recurso, boton);

            VBox botonesRecursos = new VBox(10, textoRecurso, listadoRecurso, boton);
            botonesRecursos.setAlignment(Pos.CENTER);
            hboxEntrega.getChildren().add(botonesRecursos);
        }


        Label textoBanca = new Label("Elige el recurso que quieres recibir en el intercambio:");

        for (Recurso recurso : this.recursos) {
            String ruta = "/images/utils/recursos/" + recurso.getNombreRecurso() + ".png";

            ListadoRecurso listadoRecurso = new ListadoRecurso(recurso.getNombreRecurso(), ruta);

            BotonGenerico boton = new BotonGenerico("Cantidad: 0", "botones-progreso", 230, 80);
            boton.setOnAction(e -> controlador.obtenerRecursosARecibir(recurso));
            botonesPorRecursoARecibir.put(recurso, boton);

            VBox botonesRecursos = new VBox(10, listadoRecurso, boton);
            botonesRecursos.setAlignment(Pos.CENTER);
            hboxBanca.getChildren().add(botonesRecursos);
        }

        centro.getChildren().addAll(titulo, textoBanca, hboxBanca, textoEntregar, hboxEntrega);

        this.setCenter(centro);

        botonConfirmar = new BotonJuego("Confirmar");
        botonConfirmar.setOnAction(e -> controlador.anunciarOferta());
        desactivarBotonEjecutar();

        BotonJuego botonVolver = new BotonJuego("Volver");
        botonVolver.setOnAction(new VolverControlador(contenedor, vista));

        HBox botonesInferiores = new HBox(20, botonVolver, botonConfirmar);
        botonesInferiores.setAlignment(Pos.CENTER);
        botonesInferiores.setPadding(new Insets(20));

        this.setBottom(botonesInferiores);
    }

    public void sumarContadorEntregar(Recurso recurso) {
        BotonGenerico boton = botonesPorRecursoAEntregar.get(recurso);
        int valor = Integer.parseInt(boton.getText().replace("Cantidad: ", "").trim());
        int nuevoValor = valor + 1;
        boton.setText("Cantidad: " + nuevoValor);
    }

    public void sumarContadorRecibir(Recurso recurso) {
        BotonGenerico boton = botonesPorRecursoARecibir.get(recurso);
        int valor = Integer.parseInt(boton.getText().replace("Cantidad: ", "").trim());
        int nuevoValor = valor + 1;
        boton.setText("Cantidad: " + nuevoValor);
    }

    public void activarBotonesARecibir() {
        for (Button botonRecibir : botonesPorRecursoARecibir.values()) {
            botonRecibir.setDisable(false);
        }
    }

    public void desactivarBotonesARecibir() {
        for (Button botonRecibir : botonesPorRecursoARecibir.values()) {
            botonRecibir.setDisable(true);
        }
    }

    public void activarBotonesAEntregar() {
        for (Button botonRecibir : botonesPorRecursoAEntregar.values()) {
            botonRecibir.setDisable(false);
        }
    }

    public void desactivarBotonesAEntregar() {
        for (Button botonRecibir : botonesPorRecursoAEntregar.values()) {
            botonRecibir.setDisable(true);
        }
    }

    public void activarBotonEjecutar() {
        botonConfirmar.setDisable(false);
    }

    public void desactivarBotonEjecutar() {
        botonConfirmar.setDisable(true);
    }
}
