package edu.fiuba.algo3.controllers.musica;

import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Musica {
    private static Musica instancia;
    private MediaPlayer mediaPlayer;

    private Musica() {
        try {
            String ruta = getClass().getResource("/music/tema_principal.wav").toExternalForm();
            Media media = new Media(ruta);
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            PopUpError.mostrar("No se pudo cargar el tema principal.");
        }
    }

    public static Musica getInstancia() {
        if (instancia == null) {
            instancia = new Musica();
        }
        return instancia;
    }

    public void reproducir(String rutaCancion) {
        try {
            mediaPlayer.stop();
            mediaPlayer.dispose();

            String ruta = getClass().getResource(rutaCancion).toExternalForm();
            Media media = new Media(ruta);
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            PopUpError.mostrar("Hubo un error al querer reproducir el tema...");
        }
    }

    public void silenciar() {
        mediaPlayer.setMute(!mediaPlayer.isMute());
    }
}
