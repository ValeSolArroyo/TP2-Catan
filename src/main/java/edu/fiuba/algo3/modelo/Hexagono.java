package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.patronHexagono.EstadoConLadron;
import edu.fiuba.algo3.modelo.patronHexagono.EstadoHexagono;
import edu.fiuba.algo3.modelo.patronHexagono.EstadoSinLadron;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

import java.util.ArrayList;
import java.util.List;

public class Hexagono {
    private final Terreno terreno;
    private final int numeroFicha;
    private List<Vertice> vertices;
    private EstadoHexagono estadoActual;


    public Hexagono(Terreno terreno, int numeroFicha) {
        this.terreno = terreno;
        this.numeroFicha = numeroFicha;
        this.vertices = new ArrayList<>();
        this.estadoActual = new EstadoSinLadron(); // Al principio es así
    }

    public void ponerLadron() {
        this.estadoActual = new EstadoConLadron();
    }

    public void quitarLadron() {
        this.estadoActual = new EstadoSinLadron();
    }

    public void agregarVertice(Vertice vertice) {
        if (!vertices.contains(vertice) && vertices.size() < 6) {
            vertices.add(vertice);
        }
    }

    public void producirRecursos() {
        estadoActual.producirRecursos(this, terreno);
    }

    public Integer obtenerNumeroFicha() {
        return numeroFicha;
    }

    public List<Vertice> obtenerVertices() {
        return vertices;
    }

}
