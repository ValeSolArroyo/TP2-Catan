package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Hexagono {
    private final int id;                     // posición fija en el tablero
    private String tipoTerreno;
    private Integer numeroFicha;
    private List<Integer> hexagonosAdyacentes;   // ids de los hexágonos vecinos
    private List<Vertice> vertices;

    public Hexagono(int id, String tipoTerreno, Integer numeroFicha) {
        this.id = id;
        this.tipoTerreno = tipoTerreno;
        this.numeroFicha = numeroFicha;
        this.hexagonosAdyacentes = new ArrayList<>();
        this.vertices = new ArrayList<>(6); //ta ok?  creo q si?
    }

    public void agregarAdyacente(int idVecino) {
        if (!hexagonosAdyacentes.contains(idVecino)) {
            hexagonosAdyacentes.add(idVecino);
        }
    }

    public void agregarVertice(Vertice vertice) {
        if (!vertices.contains(vertice) && vertices.size() < 6) {
            vertices.add(vertice);
        }
    }

    public int obtenerId() {
        return id;
    }

    public String obtenerTipoTerreno() {
        return tipoTerreno;
    }

    public Integer obtenerNumeroFicha() {
        return numeroFicha;
    }

    public List<Integer> obtenerAdyacentes() {
        return hexagonosAdyacentes;
    }
}