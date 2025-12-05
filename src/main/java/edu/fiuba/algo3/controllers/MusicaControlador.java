package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.componentes.menu.Musica;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpMusica;

public class MusicaControlador {
    private final Musica reproductor;

    public MusicaControlador(Musica reproductor) {
        this.reproductor = reproductor;
    }

    public void elegirTema() {
        PopUpMusica.mostrar(reproductor);
    }

    public void silenciar() {
        reproductor.silenciar();
    }
}
