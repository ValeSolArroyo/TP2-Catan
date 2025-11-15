package edu.fiuba.algo3.modelo;

public class Arista {

    private final Vertice vertice1;
    private final Vertice vertice2;

    public Arista(Vertice vertice1, Vertice vertice2) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
    }

    public Vertice obtenerV1() {
        return vertice1;
    }
    public Vertice obtenerV2() {
        return vertice2;
    }
}
