package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;

import java.util.List;

public class EstadoActivarLadron implements EstadoJuego {

    @Override
    public void verificarDescartesPorLadron(Juego juego, List<Jugador> jugadores) {
        for (Jugador jugador : jugadores){
            jugador.descartar();
        }
    }

    @Override
    public List<Jugador> moverLadron(Juego juego, Hexagono hexagono) {
        return juego.moverLadronInterno(hexagono);
    }

    @Override
    public void robarCartaDe(Juego juego, Jugador victima, Jugador ladron) {
        this.robarCartaDeInterno(victima);
        juego.setEstado(new EstadoAccionesTurno());
    }

    @Override
    public void colocarPobladoInicial(Juego juego,Vertice vertice, Arista arista) {
        throw new IllegalStateException("No se puede realizar la colocación inicial en la fase de acciones del turno.");
    }

    public void robarCartaDeInterno(Jugador victima, Jugador ladron) {
        ladron.robarCarta(victima);
    }
}