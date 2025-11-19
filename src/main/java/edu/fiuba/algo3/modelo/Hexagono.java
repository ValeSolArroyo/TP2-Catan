package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.patronHexagono.EstadoConLadron;
import edu.fiuba.algo3.modelo.patronHexagono.EstadoHexagono;
import edu.fiuba.algo3.modelo.patronHexagono.EstadoSinLadron;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Hexagono {
    private final Terreno terreno;
    private final int numeroFicha;
    private final List<Arista> aristas;
    private List<Vertice> vertices;
    private EstadoHexagono estadoActual;


    public Hexagono(Terreno terreno, int numeroFicha) {
        this.terreno = terreno;
        this.numeroFicha = numeroFicha;
        this.vertices = new ArrayList<>();
        this.estadoActual = new EstadoSinLadron(); // Al principio es así
        this.aristas = new ArrayList<>();
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

    public void registrarPropietariosEn(Set<Jugador> jugadores) {
        for (Vertice v : vertices) {
            v.registrarPropietarioEn(jugadores);
        }
    }

    public void producirRecursos(int numero) {
        if (numero == this.numeroFicha) {
            estadoActual.producirRecursos(vertices, terreno);
        }
    }
}
