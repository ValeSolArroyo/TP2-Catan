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
    
    // PRODUCCION 
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

    // GETTERS NECESARIOS PARA EL CONTROLADOR/USUARIO, DE OTRA MANERA DESDE EL EXTERIOR NO PUEDO COMUNICARME!

    public Vertice encontrarVertice(int verticeId) {
        Vertice vertice = verticesPorId.get(verticeId);
        if (vertice == null) {
            throw new IllegalArgumentException("Error fatal: ID de Vértice " + verticeId + " no existe. La UI debe garantizar la validez.");
        }
        return vertice;
    }

    public Hexagono obtenerHexagono(int hexagonoId) {
        Hexagono hexagono = hexagonosPorId.get(hexagonoId);
        if (hexagono == null) {
            throw new IllegalArgumentException("Error fatal: ID de Hexágono " + hexagonoId + " no existe.");
        }
        return hexagono;
    }

    public Arista encontrarArista(int aristaId) {
        Arista arista = aristasPorId.get(aristaId);
        if (arista == null) {
            throw new IllegalArgumentException("Error fatal: ID de Arista " + aristaId + " no existe.");
        }
        return arista;
    }

    public List<Hexagono> obtenerHexagonos() {
        return hexagonos;
    }

}



