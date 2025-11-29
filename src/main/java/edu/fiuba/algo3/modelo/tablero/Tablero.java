package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.List;

public class Tablero {
    private final List<Hexagono> hexagonos;
    private Hexagono hexagonoOcupadoLadron;

    public Tablero(List<Hexagono> hexagonos) {
        this.hexagonos = hexagonos;
    }

    public void producir(int numero) {
        for (Hexagono hexagono : hexagonos) {
            hexagono.producirRecursos(numero);
        }
    }

    public void darRecursosIniciales(Vertice vertice) {
        for (Hexagono hexagono : hexagonos) {
            hexagono.entregarRecursoInicialA(vertice);
        }
    }

    public void moverLadronA(Hexagono nuevoLugar){
        hexagonoOcupadoLadron.quitarLadron();
        nuevoLugar.ponerLadron();
        this.hexagonoOcupadoLadron = nuevoLugar;
    }
}



