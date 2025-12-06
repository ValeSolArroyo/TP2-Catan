package edu.fiuba.algo3.modelo.tablero;

import java.util.*;
import java.util.stream.Collectors;

import edu.fiuba.algo3.modelo.jugador.Jugador;

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

    private int dfsMasLarga(Arista actual, Set<Arista> visitadas,
                       Map<Arista, Set<Arista>> vecinos) {
    
        Set<Arista> aristasVisitadas = new HashSet<>();
        return dfsAux(actual, aristasVisitadas, vecinos);
    }


    private int dfsAux(Arista actual, Set<Arista> visitadas, Map<Arista, Set<Arista>> vecinos) {
        visitadas.add(actual);
        int mejor = 1;
        for (Arista vecino : vecinos.getOrDefault(actual, Collections.emptySet())) {
            if (!visitadas.contains(vecino)) {
                int largo = 1 + dfsAux(vecino, visitadas, vecinos);
                if (largo > mejor) mejor = largo;
            }
        }
        visitadas.remove(actual);
        return mejor;
    }


    public int conseguirRutaMasLarga(Jugador jugador) {
        Set<Arista> aristasJugador = new HashSet<>();
        for (Hexagono hexagono : hexagonos) {
            for (Arista arista : hexagono.getAristas()) {
                if (arista.validarCarreteraPropia(jugador)) {
                    aristasJugador.add(arista);
                }
            }
        }

        if (aristasJugador.isEmpty()) return 0;

        Map<Arista, Set<Arista>> vecinos = new HashMap<>();
        for (Arista arista : aristasJugador) {
            vecinos.put(arista, new HashSet<>());
        }

        for (Vertice vertice : getVertices().values()) {
            List<Arista> aristasEnVertice = vertice.getAristas();
            List<Arista> filtradas = aristasEnVertice.stream()
                    .filter(arista -> aristasJugador.contains(arista))
                    .collect(Collectors.toList());

        
            for (int i = 0; i < filtradas.size(); i++) {
                for (int j = i + 1; j < filtradas.size(); j++) {
                    Arista a = filtradas.get(i);
                    Arista b = filtradas.get(j);
                    vecinos.get(a).add(b);
                    vecinos.get(b).add(a);
                }
            }
        }

        int rutaMasLarga = 0;
        Set<Arista> visitadas = new HashSet<>();
        for (Arista inicio : aristasJugador) {
            int largo = dfsMasLarga(inicio, visitadas, vecinos);
            if (largo > rutaMasLarga) rutaMasLarga = largo;
        }

        return rutaMasLarga;
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



