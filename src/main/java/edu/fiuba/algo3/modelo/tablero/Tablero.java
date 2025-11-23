package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.List;

public class Tablero {
    private final List<Hexagono> hexagonos;

    public Tablero(List<Hexagono> hexagonos) {
        this.hexagonos = hexagonos;
    }

    public void producir(int numero) {
        for (Hexagono hexagono : hexagonos) {
            hexagono.producirRecursos(numero);
        }
    }

    public void darRecursosIniciales(Jugador jugador, Vertice vertice) {
        for (Hexagono hexagono : hexagonos) {
            if (hexagono.contieneVertice(vertice)) {
                hexagono.entregarRecursoInicialA(jugador);
            }
        }
    }
}



