package edu.fiuba.algo3.modelo.patronHexagono;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

public interface EstadoHexagono {
    void producirRecursos(Hexagono hexagono, Terreno terreno);
}
