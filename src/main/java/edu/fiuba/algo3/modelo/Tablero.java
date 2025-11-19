package edu.fiuba.algo3.modelo;

import java.util.List;
import java.util.Map;

public class Tablero {
    private final List<Hexagono> hexagonos;
    private final Map<Integer, Vertice> verticesPorId;
    private final Map<Integer, Hexagono> hexagonosPorId;

    public Tablero(List<Hexagono> hexagonos, Map<Integer, Vertice> verticesPorId, Map<Integer, Hexagono> hexagonosPorId) {
        this.hexagonos = hexagonos;
        this.verticesPorId = verticesPorId;
        this.hexagonosPorId = hexagonosPorId;
    }

    public void producir(int numero) {
        for (Hexagono hexagono : hexagonos) {
                hexagono.producirRecursos(numero);
        }
    }
    public Vertice encontrarVertice(int verticeId) {
        Vertice vertice = verticesPorId.get(verticeId);
        if (vertice == null) {
            throw new IllegalArgumentException("Vértice con ID " + verticeId + " no encontrado.");
        }
        return vertice;
    }

    public Hexagono obtenerHexagono(int hexagonoId) {
        Hexagono hexagono = hexagonosPorId.get(hexagonoId);
        if (hexagono == null) {
            throw new IllegalArgumentException("Hexágono con ID " + hexagonoId + " no encontrado.");
        }
        return hexagono;
    }


}



