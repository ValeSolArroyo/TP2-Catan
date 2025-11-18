package edu.fiuba.algo3.modelo.patronJuego;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;

public class EstadoActivarLadron implements EstadoJuego {

    @Override
    public void verificarDescartesPorLadron(Juego juego) {
        juego.verificarDescartesPorLadronInterno();
    }

    @Override
    public List<Jugador> moverLadron(Juego juego, int hexId) {
        return juego.moverLadronInterno(hexId);
    }

    @Override
    public void robarCartaDe(Juego juego, Jugador victima) {
        juego.robarCartaDeInterno(victima);
        juego.setEstado(new EstadoAccionesTurno());
    }
}