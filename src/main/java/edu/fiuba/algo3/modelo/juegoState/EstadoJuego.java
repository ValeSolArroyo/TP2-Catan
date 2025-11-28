package edu.fiuba.algo3.modelo.juegoState;

public interface EstadoJuego {
    boolean puedeColocarPobladoInicial();
    boolean puedeLanzarDados();
    boolean puedeUsarLadron();
    boolean puedeConstruir();
    boolean puedeFinalizarTurno();
    boolean puedeComprarCartaDesarrollo();
    boolean puedeJugarCartaDesarrollo();
    boolean puedeComerciar();
}
