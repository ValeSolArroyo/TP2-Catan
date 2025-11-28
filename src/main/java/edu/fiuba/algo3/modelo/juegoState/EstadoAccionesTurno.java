package edu.fiuba.algo3.modelo.juegoState;

public class EstadoAccionesTurno implements EstadoJuego {
    @Override
    public boolean puedeColocarPobladoInicial() {return false;}

    @Override
    public boolean puedeLanzarDados() {
        return false;
    }

    @Override
    public boolean puedeUsarLadron() {
        return false;
    }

    @Override
    public boolean puedeConstruir() {
        return true;
    }

    @Override
    public boolean puedeFinalizarTurno() {
        return true;
    }

    @Override
    public boolean puedeComprarCartaDesarrollo() {
        return true;
    }

    @Override
    public boolean puedeJugarCartaDesarrollo() {
        return true;
    }

    @Override
    public boolean puedeComerciar() {
        return true;
    }
}
