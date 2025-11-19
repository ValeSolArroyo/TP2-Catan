package edu.fiuba.algo3.modelo;

import java.util.List;
import java.util.Map;

public class Tablero {
    private final List<Hexagono> hexagonos;
    private final Map<Integer, Vertice> verticesPorId;
    private final Map<Integer, Hexagono> hexagonosPorId;
    private final Map<Integer, Arista> aristasPorId;

    public Tablero(List<Hexagono> hexagonos, Map<Integer, Vertice> verticesPorId, Map<Integer, Hexagono> hexagonosPorId, Map<Integer, Arista> aristasPorId) {
        this.hexagonos = hexagonos;
        this.verticesPorId = verticesPorId;
        this.hexagonosPorId = hexagonosPorId;
        this.aristasPorId = aristasPorId;
    }

    public void producir(int numero) {
        for (Hexagono hexagono : hexagonos) {
                hexagono.producirRecursos(numero);
        }
    }
    public Vertice encontrarVertice(int verticeId) {
        Vertice vertice = verticesPorId.get(verticeId);
        if (vertice == null) {
            return new NullVertice();
        }
        return vertice;
    }

    public Hexagono obtenerHexagono(int hexagonoId) {
        Hexagono hexagono = hexagonosPorId.get(hexagonoId);
        if (hexagono == null) {
            return new NullHexagono();
        }
        return hexagono;
    }

    public Arista encontrarArista(int aristaId) { // <--- NUEVO MÉTODO
        Arista arista = aristasPorId.get(aristaId);
        if (arista == null) {
            // Usar patrón Null Object despues
            throw new IllegalArgumentException("Arista con ID " + aristaId + " no encontrada.");
        }
        return arista;
    }

    public void darRecursosIniciales(Jugador jugador, Vertice vertice) {
        for (Hexagono hexagono : hexagonos) {
            if (hexagono.contieneVertice(vertice)) {
                hexagono.entregarRecursoInicialA(jugador);
            }
        }
    }

    public List<Hexagono> obtenerHexagonos() {
        return hexagonos;
    }

}



