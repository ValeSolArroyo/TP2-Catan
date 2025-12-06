package edu.fiuba.algo3.modelo.tablero;

import java.util.*;

public class Tablero {
    private final List<Hexagono> hexagonos;
    private Hexagono hexagonoOcupadoLadron;
    private List<Vertice> vertices;
    private List<Arista> aristas;

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

    public List<Hexagono> getHexagonos() {
        return this.hexagonos;
    }

    public Map<Integer, Vertice> getVertices() {
        Map<Integer, Vertice> vertices = new HashMap<>();

        for (Hexagono hexagono : hexagonos) {
            for (Vertice vertice : hexagono.getVertices()) {
                if (vertices.containsKey(vertice.getId())) {
                    continue;
                }
                vertices.put(vertice.getId(), vertice);
            }
        }
        return vertices;
    }

    public Map<Integer, Arista> getAristas() {
        Map<Integer, Arista> aristas = new HashMap<>();

        for (Hexagono hexagono : hexagonos) {
            for (Arista arista : hexagono.getAristas()) {
                aristas.put(arista.getId(), arista);
            }
        }
        return aristas;
    }

    public List<Arista> getListaAristas(){
        return aristas; }
}



