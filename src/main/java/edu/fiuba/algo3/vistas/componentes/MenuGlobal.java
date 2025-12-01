package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.controllers.BotonAyudaControlador;
import edu.fiuba.algo3.controllers.MenuGlobalControlador;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.componentes.botones.BotonGenerico;
import edu.fiuba.algo3.vistas.componentes.botones.BotonMenu;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpAcercaDe;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuGlobal {
    private final VBox contenidoMenu;

    public MenuGlobal(ContenedorPrincipalVistas contenedor, MenuGlobalControlador controlador) {
        BotonMenu botonMenu = new BotonMenu("Menu");
        botonMenu.setTranslateY(120);

        HBox menuDesplegable = new HBox(10);
        menuDesplegable.setAlignment(Pos.CENTER_RIGHT);
        menuDesplegable.setVisible(false);

        BotonMenu botonMusica = new BotonMenu("Musica");
        BotonMenu botonOpciones = new BotonMenu("Opciones");
        BotonMenu botonAyuda = new BotonMenu("Ayuda");
        botonAyuda.setOnAction(new BotonAyudaControlador(contenedor));

        BotonMenu botonAcerca = new BotonMenu("Acerca de");
        botonAcerca.setOnAction(e -> PopUpAcercaDe.mostrar());

        menuDesplegable.getChildren().addAll(botonMusica, botonOpciones, botonAyuda, botonAcerca);

        VBox submenuOpciones = new VBox(5);
        submenuOpciones.setAlignment(Pos.BOTTOM_RIGHT);
        submenuOpciones.setVisible(false);

        BotonGenerico botonPantallaCompleta = new BotonGenerico("Pantalla Completa", "boton-menu", 180, 28);
        botonPantallaCompleta.setTranslateX(40);
        botonPantallaCompleta.setOnAction(e -> controlador.activarPantallaCompleta());

        BotonMenu botonSalir = new BotonMenu("Salir");
        botonSalir.setOnAction(e -> controlador.salir());

        submenuOpciones.getChildren().addAll(botonSalir, botonPantallaCompleta);
        submenuOpciones.setTranslateX(-220);

        // TODO: mejorar estos events feos
        botonOpciones.setOnAction(e -> {
            boolean estabaAbierto = submenuOpciones.isVisible();
            submenuOpciones.setVisible(!estabaAbierto);
            if (!estabaAbierto) {
                botonOpciones.setText("Cerrar");
            } else {
                botonOpciones.setText("Opciones");
            }
        });

        botonMenu.setOnAction(e -> {
            boolean estabaAbierto = menuDesplegable.isVisible();
            menuDesplegable.setVisible(!estabaAbierto);
            submenuOpciones.setVisible(false);
            botonOpciones.setText("Opciones");
            if (!estabaAbierto) {
                botonMenu.setText("Cerrar");
                botonMenu.setTranslateY(60);
            } else {
                botonMenu.setText("Menu");
                botonMenu.setTranslateY(120);
            }
        });

        contenidoMenu = new VBox(5, botonMenu, submenuOpciones, menuDesplegable);
        contenidoMenu.setAlignment(Pos.BOTTOM_RIGHT);
    }

    public VBox getMenu() {
        return contenidoMenu;
    }
}
