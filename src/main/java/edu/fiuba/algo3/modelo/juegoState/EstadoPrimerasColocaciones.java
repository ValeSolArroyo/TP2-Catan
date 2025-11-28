package edu.fiuba.algo3.modelo.juegoState;

public class EstadoPrimerasColocaciones implements EstadoJuego{
    @Override
    public boolean puedeColocarPobladoInicial() {
        return true;
    }

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
        return false;
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
