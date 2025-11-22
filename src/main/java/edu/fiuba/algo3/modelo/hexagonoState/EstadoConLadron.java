package edu.fiuba.algo3.modelo.hexagonoState;

import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

import java.util.List;

public class EstadoConLadron implements EstadoHexagono {
    @Override
    public void producirRecursos(List<Vertice> vertices, Terreno terreno) {
        // Bloqueado: no hace nada
    }
}
