package edu.fiuba.algo3.vistas.comercio;

import edu.fiuba.algo3.controllers.comercio.ComercioBancaControlador;
import edu.fiuba.algo3.controllers.VolverControlador;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VistaComercioBanca extends BorderPane {
    private BotonGenerico botonConfirmar;
    private List<Button> listaBotonesElegir;
    private List<Button> listaBotonesBanca;

    public VistaComercioBanca(ContenedorPrincipalVistas contenedor, ComercioBancaControlador controlador, List<Recurso> recursos, VistaJuegoGeneral vista, Map<String, Integer> recursosJugadorActual) {
        this.setBackground(FondoPantalla.crearFondo("/images/backgrounds/mar.jpeg"));
        listaBotonesElegir = new ArrayList<>();
        listaBotonesBanca =  new ArrayList<>();

        VBox centro = new VBox(20);
        centro.setPadding(new Insets(40, 20, 0, 20));
        centro.setAlignment(Pos.TOP_CENTER);

        Label titulo = new Label("Intercambio con la Banca: obten 1 recurso por 4 tuyos.");
        titulo.getStyleClass().add("titulo-comercio");

        HBox hboxBanca = new HBox(40);
        hboxBanca.setAlignment(Pos.CENTER);
        VBox.setMargin(hboxBanca, new Insets(0, 0, 40, 0));

        Label textoEntregar = new Label("Elige el recurso que quieres entregar a la Banca:");
        textoEntregar.getStyleClass().add("texto-comercio");

        HBox hboxEntrega = new HBox(40);
        hboxEntrega.setAlignment(Pos.CENTER);

        for (Recurso recurso : recursos) {
            String nombreRecurso = recurso.getNombreRecurso();
            int cantidad = recursosJugadorActual.get(nombreRecurso);
            String ruta = "/images/utils/recursos/" + nombreRecurso + ".png";

            Label textoRecurso = new Label("¡Tienes " + cantidad + "!");
            textoRecurso.getStyleClass().add("texto-tienes-cantidad");

            ListadoRecurso listadoRecurso = new ListadoRecurso(recurso.getNombreRecurso(), ruta);

            BotonGenerico boton = new BotonGenerico("Seleccionar", "botones-comercio", 220, 70);
            boton.setOnAction(e -> controlador.obtenerRecursoAEntregar(recurso));
            boton.setDisable(true);
            listaBotonesElegir.add(boton);

            VBox botonesRecursos = new VBox(10, textoRecurso, listadoRecurso, boton);
            botonesRecursos.setAlignment(Pos.CENTER);
            hboxEntrega.getChildren().add(botonesRecursos);
        }


        Label textoBanca = new Label("Elige el recurso que quieres de la Banca:");
        textoBanca.getStyleClass().add("texto-comercio");

        for (Recurso recurso : recursos) {
            String ruta = "/images/utils/recursos/" + recurso.getNombreRecurso() + ".png";

            ListadoRecurso listadoRecurso = new ListadoRecurso(recurso.getNombreRecurso(), ruta);

            BotonGenerico boton = new BotonGenerico("Seleccionar", "botones-comercio", 220, 70);
            boton.setOnAction(e -> controlador.obtenerRecursoBanca(recurso));
            listaBotonesBanca.add(boton);

            VBox botonesRecursos = new VBox(10, listadoRecurso, boton);
            botonesRecursos.setAlignment(Pos.CENTER);
            hboxBanca.getChildren().add(botonesRecursos);
        }

        centro.getChildren().addAll(titulo, textoBanca, hboxBanca, textoEntregar, hboxEntrega);

        this.setCenter(centro);

        botonConfirmar = new BotonGenerico("Confirmar", "botones-comercio", 150, 50);
        botonConfirmar.setOnAction(e -> controlador.ejecutar());
        desactivarBotonEjecutar();

        BotonGenerico botonVolver = new BotonGenerico("Volver", "botones-comercio", 150, 50);
        botonVolver.setOnAction(new VolverControlador(contenedor, vista));

        HBox botonesInferiores = new HBox(20, botonVolver, botonConfirmar);
        botonesInferiores.setAlignment(Pos.CENTER);
        botonesInferiores.setPadding(new Insets(20));

        this.setBottom(botonesInferiores);
    }

    public void activarBotonEjecutar() {
        botonConfirmar.setDisable(false);
    }

    public void desactivarBotonEjecutar() {
        botonConfirmar.setDisable(true);
    }

    public void activarBotonesElegir() {
        for (Button boton : listaBotonesElegir) {
            boton.setDisable(false);
        }
    }

    public void desactivarBotonesElegir() {
        for (Button boton : listaBotonesElegir) {
            boton.setDisable(true);
        }
    }

    public void activarBotonesBanca() {
        for (Button boton : listaBotonesBanca) {
            boton.setDisable(false);
        }
    }

    public void desactivarBotonesBanca() {
        for (Button boton : listaBotonesBanca) {
            boton.setDisable(true);
        }
    }
}
