package edu.fiuba.algo3.vistas.componentes;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Musica {
    File tema_principal = new File("src/main/resources/music/tema_principal.mp3");
    String ruta_tema_principal = "file:///"+tema_principal.getAbsolutePath();
    MediaPlayer mediaPlayer;

    public void sonidoFondo(){
        ruta_tema_principal = ruta_tema_principal.replace("\\", "/");
        Media musicFile = new Media(ruta_tema_principal);
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.play();
    }
}
