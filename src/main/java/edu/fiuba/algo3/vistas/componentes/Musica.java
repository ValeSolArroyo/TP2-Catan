package edu.fiuba.algo3.vistas.componentes;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Musica {
    private MediaPlayer mediaPlayer;

    public void sonidoFondo() {
        String ruta = getClass().getResource("/music/tema_principal.wav").toExternalForm();
        Media musicFile = new Media(ruta);
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}
