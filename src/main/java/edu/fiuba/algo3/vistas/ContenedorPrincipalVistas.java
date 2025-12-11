package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.MenuGlobalControlador;
import edu.fiuba.algo3.controllers.MusicaControlador;
import edu.fiuba.algo3.vistas.componentes.menu.MenuGlobal;
import edu.fiuba.algo3.vistas.componentes.menu.Musica;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ContenedorPrincipalVistas extends StackPane {
    private Stage stage;
    private BorderPane contenedor;
    private VBox menuGlobal;
    private final MenuGlobalControlador menuGlobalControlador;

    public ContenedorPrincipalVistas(Stage stage) {
        this.stage = stage;

        Musica reproductor = Musica.getInstancia();
        MusicaControlador musicaControlador = new MusicaControlador(reproductor);

        this.menuGlobalControlador = new MenuGlobalControlador(stage);

        contenedor = new BorderPane();
        this.getChildren().add(contenedor);

        menuGlobal = new MenuGlobal(this, menuGlobalControlador, musicaControlador).getMenu();
        StackPane.setAlignment(menuGlobal, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(menuGlobal, new Insets(20));

        menuGlobal.setPickOnBounds(false);
        this.getChildren().add(menuGlobal);
    }

    public void setContenido(Node vista) {
        contenedor.setCenter(vista);
    }

    public Stage getStage() {
        return stage;
    }

    public Node getContenido() {return contenedor.getCenter();}
}
