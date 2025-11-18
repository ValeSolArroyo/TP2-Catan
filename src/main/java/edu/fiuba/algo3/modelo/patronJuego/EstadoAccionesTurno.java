package edu.fiuba.algo3.modelo.patronJuego;

import edu.fiuba.algo3.modelo.Juego;

public class EstadoAccionesTurno implements EstadoJuego {

    @Override
    public void construirPoblado(Juego juego, int verticeId) {
        juego.construirPobladoInterno(verticeId);
    }

    @Override
    public void finalizarTurno(Juego juego) {
        juego.pasarAlSiguienteJugador();
        juego.setEstado(new EstadoTirarDados());
    }
}
