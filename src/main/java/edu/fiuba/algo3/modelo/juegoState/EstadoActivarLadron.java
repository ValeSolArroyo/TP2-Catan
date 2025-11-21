package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.modelo.Arista;
import edu.fiuba.algo3.modelo.Hexagono;

import java.util.List;

public class EstadoActivarLadron implements EstadoJuego {

    @Override
    public void verificarDescartesPorLadron(Juego juego) {
        juego.verificarDescartesPorLadronInterno();
    }

    @Override
    public List<Jugador> moverLadron(Juego juego, Hexagono hexagono) {
        return juego.moverLadronInterno(hexagono);
    }

    @Override
    public void robarCartaDe(Juego juego, Jugador victima) {
        juego.robarCartaDeInterno(victima);
        juego.setEstado(new EstadoAccionesTurno());
    }

    @Override
    public void colocarPobladoInicial(Juego juego,Vertice vertice, Arista arista) {
        throw new IllegalStateException("No se puede realizar la colocación inicial en la fase de acciones del turno.");
    }
}