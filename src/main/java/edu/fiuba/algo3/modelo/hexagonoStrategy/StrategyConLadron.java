package edu.fiuba.algo3.modelo.hexagonoStrategy;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

import java.util.List;

public class StrategyConLadron implements StrategyHexagono {
    @Override
    public void producirRecursos(List<Vertice> vertices, Terreno terreno) {}

    @Override
    public void registrarEnTablero(Tablero tablero, Hexagono hexagono) {
        tablero.registrarHexagonoConLadron(hexagono);
    }
}
