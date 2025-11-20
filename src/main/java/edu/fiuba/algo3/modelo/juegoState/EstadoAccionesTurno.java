package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;

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

    @Override
    public void colocarPobladoInicial(Juego juego, Jugador jugador, int verticeID, int aristaID) {
        throw new IllegalStateException("No se puede realizar la colocación inicial en la fase de acciones del turno.");
    }
}
