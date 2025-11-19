package edu.fiuba.algo3.modelo.patronHexagono;

import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

import java.util.List;

public interface EstadoHexagono {
    void producirRecursos(List<Vertice> vertices, Terreno terreno);
}
