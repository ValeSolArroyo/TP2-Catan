package edu.fiuba.algo3.vistas.componentes.popups;

import edu.fiuba.algo3.vistas.componentes.botones.BotonAgregarTema;
import edu.fiuba.algo3.vistas.componentes.botones.BotonGenerico;
import edu.fiuba.algo3.vistas.componentes.menu.Musica;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUpMusica {
    public static void mostrar(Musica musica) {
        Stage ventana = new Stage();
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle("Elegir Tema");

        VBox temas = new VBox(10);
        Image imagen = new Image(PopUpMusica.class.getResource("/images/backgrounds/mar.jpeg").toExternalForm());
        BackgroundSize tamanio = new BackgroundSize(0, 0, false, false, true, true);
        BackgroundImage fondo = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, tamanio);
        temas.setBackground(new Background(fondo));
        temas.setAlignment(Pos.CENTER);

        BotonAgregarTema tema1 = new BotonAgregarTema("Tema principal", "", "/music/tema_principal.wav", musica, 150, 50);

        BotonAgregarTema tema2 = new BotonAgregarTema("Spring (It's a Big World Outside)", "(Stardew Valley)", "/music/stardew_big_world.wav", musica, 280, 50);

        BotonAgregarTema tema3 = new BotonAgregarTema("Megalovania", "(Undertale)", "/music/megalovania.wav", musica, 140, 50);

        BotonAgregarTema tema4 = new BotonAgregarTema("Minecraft", "(Minecraft)", "/music/minecraft.wav", musica, 140, 50);

        BotonGenerico salir = new BotonGenerico("Salir", "botones-musica-salir", 80, 30);
        salir.setOnAction(e -> ventana.close());

        VBox.setMargin(tema1, new Insets(10, 0, 0, 0));
        VBox.setMargin(tema2, new Insets(10, 0, 0, 0));
        VBox.setMargin(tema3, new Insets(10, 0, 0, 0));
        VBox.setMargin(tema4, new Insets(10, 0, 0, 0));
        VBox.setMargin(salir, new Insets(30, 0, 0, 0));

        temas.getChildren().addAll(tema1, tema2, tema3, tema4, salir);

        Scene scene = new Scene(temas, 500, 500);
        scene.getStylesheets().add(PopUpMusica.class.getResource("/styles/styles.css").toExternalForm());
        ventana.setScene(scene);
        ventana.showAndWait();
    }
}
