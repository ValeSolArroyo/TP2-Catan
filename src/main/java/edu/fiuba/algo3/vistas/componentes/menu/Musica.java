package edu.fiuba.algo3.vistas.componentes.menu;

import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Musica {
    private MediaPlayer mediaPlayer;

    public void reproducir(String rutaCancion) {
        try {
            // TODO: revisar
            // Para que se resetee y no se ejecuten varios temas en simultáneo
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }

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
