package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.controllers.BotonAyudaControlador;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuGlobal {
    private final VBox contenidoMenu;

    public MenuGlobal(ContenedorPrincipalVistas contenedor) {
        BotonMenu botonMenu = new BotonMenu("Menu");
        botonMenu.setTranslateY(50);

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

        botonMenu.setOnAction(e -> {
            boolean estabaAbierto = menuDesplegable.isVisible();
            menuDesplegable.setVisible(!estabaAbierto);
            if (!estabaAbierto) {
                botonMenu.setText("Cerrar");
                botonMenu.setTranslateY(-15);
            } else {
                botonMenu.setText("Menu");
                botonMenu.setTranslateY(50);
            }
        });

        contenidoMenu = new VBox(5, botonMenu, menuDesplegable);
        contenidoMenu.setAlignment(Pos.BOTTOM_RIGHT);
    }

    public VBox getMenu() {
        return contenidoMenu;
    }
}
