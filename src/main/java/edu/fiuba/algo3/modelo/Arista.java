package edu.fiuba.algo3.modelo;

public class Arista {
    private final Vertice v1;
    private final Vertice v2;

    public Arista(Vertice v1, Vertice v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertice obtenerV1() {
        return v1;
    }
    public Vertice obtenerV2() {
        return v2;
    }
}
