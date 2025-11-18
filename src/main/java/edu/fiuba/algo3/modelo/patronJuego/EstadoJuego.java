package edu.fiuba.algo3.modelo.patronJuego;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Vertice;

import java.util.List;


public interface EstadoJuego {
    default void colocarPobladoInicial(Juego juego, Jugador jugador, int verticeId) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }

    default void darRecursosIniciales(Juego juego, Jugador j, Vertice v) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }


    default int lanzarDados(Juego juego) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }


    default void verificarDescartesPorLadron(Juego juego) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }

    default List<Jugador> moverLadron(Juego juego, int hexagonoId) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }

    default void robarCartaDe(Juego juego, Jugador victima) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }


    default void construirPoblado(Juego juego, int verticeId) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }

    default void construirCiudad(Juego juego, int verticeId) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }

    default void construirCarretera(Juego juego, int aristaId) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }

    default void finalizarTurno(Juego juego) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }
}

