package edu.fiuba.algo3.modelo.juegoState;

public class EstadoLadron implements EstadoJuego{
    @Override
    public boolean puedeColocarPobladoInicial() {return false;}

    @Override
    public boolean puedeLanzarDados() {
        return false;
    }

    @Override
    public boolean puedeUsarLadron() {
        return true;
    }

    @Override
    public boolean puedeConstruir() {
        return true;
    }

    @Override
    public boolean puedeFinalizarTurno() {
        return false;
    }

    @Override
    public boolean puedeComprarCartaDesarrollo() {
        return false;
    }

    @Override
    public boolean puedeJugarCartaDesarrollo() {
        return false;
    }

    @Override
    public boolean puedeComerciar() {
        return false;
    }
}
