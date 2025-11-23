package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;

public class EstadoAccionesTurno implements EstadoJuego {

    @Override
    public void construirPoblado(Juego juego, Vertice vertice, Jugador jugador) {
        this.construirPobladoInterno(vertice, jugador);
    }

    @Override
    public void finalizarTurno(Juego juego) {
        juego.pasarAlSiguienteJugador();
        juego.setEstado(new EstadoTirarDados());
    }

    public void construirPobladoInterno(Vertice vertice, Jugador jugador) {
        jugador.construirPobladoInicialEn(vertice);
    }
}
