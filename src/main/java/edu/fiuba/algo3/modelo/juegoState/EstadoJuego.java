package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;


public interface EstadoJuego {

    void colocarPobladoInicial(Juego juego, Vertice vertice, Arista arista);

    default int lanzarDados(Juego juego) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }

    default void verificarDescartesPorLadron(Juego juego) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }

    default List<Jugador> moverLadron(Juego juego, Hexagono hexagono) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }

    default void robarCartaDe(Juego juego, Jugador victima) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }


    default void construirPoblado(Juego juego, Vertice vertice) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }

    default void construirCiudad(Juego juego, Vertice vertice) {
        throw new IllegalStateException("Acción no permitida en este estado.");
    }

    default void construirCarretera(Juego juego, Arista arista) {
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

