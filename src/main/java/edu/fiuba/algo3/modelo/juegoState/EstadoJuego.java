package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

import java.util.List;


public interface EstadoJuego {

    void colocarPobladoInicial(Juego juego, Jugador jugador, int verticeID, int aristaID);

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

    default void comerciarConBanco(Juego juego, Recurso entregado, Recurso recibido) {
        throw new IllegalStateException("No puedes comerciar con el banco en este momento.");
    }

    default void intercambiar(Juego juego, Jugador otroJugador, Recurso entregado, Recurso recibido) {
        throw new IllegalStateException("No puedes realizar intercambios en este momento.");
    }
}

