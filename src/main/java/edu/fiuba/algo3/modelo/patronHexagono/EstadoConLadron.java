package edu.fiuba.algo3.modelo.patronHexagono;

import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

import java.util.List;

public class EstadoConLadron implements EstadoHexagono {
    @Override
    public void producirRecursos(List<Vertice> vertices, Terreno terreno) {
        // Bloqueado: no hace nada
    }
}
