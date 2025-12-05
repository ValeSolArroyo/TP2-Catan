package edu.fiuba.algo3.vistas.componentes.popups;

import edu.fiuba.algo3.vistas.componentes.menu.Musica;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUpMusica {
    // TODO: hacerlo aesthetic!!!
    public static void mostrar(Musica musica) {
        Stage ventana = new Stage();
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle("Elegir Tema");

        VBox temas = new VBox(10);
        temas.setAlignment(Pos.CENTER);

        Button tema1 = new Button("Tema principal");
        tema1.setOnAction(e -> { musica.reproducir("/music/tema_principal.wav");
            ventana.close();
        });

        Button tema2 = new Button("Spring (It's a Big World Outside)\n(Stardew Valley)");
        tema2.setOnAction(e -> { musica.reproducir("/music/stardew_big_world.wav");
            ventana.close();
        });

        Button tema3 = new Button("Megalovania\n(Undertale)");
        tema3.setOnAction(e -> { musica.reproducir("/music/megalovania.wav");
            ventana.close();
        });

        Button tema4 = new Button("Minecraft\n(Minecraft)");
        tema4.setOnAction(e -> { musica.reproducir("/music/minecraft.wav");
            ventana.close();
        });

        Button salir = new Button("Salir");
        salir.setOnAction(e -> ventana.close());
        temas.getChildren().addAll(tema1, tema2, tema3, tema4, salir);

        Scene scene = new Scene(temas, 500, 500);
        ventana.setScene(scene);
        ventana.showAndWait();
    }
}
