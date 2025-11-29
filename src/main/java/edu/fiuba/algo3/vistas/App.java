package edu.fiuba.algo3.vistas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * JavaFX App
 */
public class App extends Application {
    public static final int ANCHO = 1280;
    public static final int ALTO = 720;

    @Override
    public void start(Stage stage) {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Minecraft.ttf"), 20);
        // TODO: agregar icono app!! está en resources/images pero no lo pude poner
        ContenedorPrincipalVistas contenedor = new ContenedorPrincipalVistas(stage);

        VistaTituloJuego tituloJuego = new VistaTituloJuego(stage, contenedor);
        contenedor.setContenido(tituloJuego);

        Scene scene = new Scene(contenedor, ANCHO, ALTO);
        scene.getStylesheets().add(App.class.getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Catán - El juego");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}