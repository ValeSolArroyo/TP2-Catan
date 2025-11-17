package edu.fiuba.algo3.modelo.patronHexagono;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

public class EstadoConLadron implements EstadoHexagono {
    @Override
    public void producirRecursos(Hexagono hexagono, Terreno terreno) {
        // Bloqueado: no hace nada
    }
}
