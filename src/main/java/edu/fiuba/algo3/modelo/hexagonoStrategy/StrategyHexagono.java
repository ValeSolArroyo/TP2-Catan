package edu.fiuba.algo3.modelo.hexagonoStrategy;

import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

import java.util.List;

public interface StrategyHexagono {
    void producirRecursos(List<Vertice> vertices, Terreno terreno);
}
