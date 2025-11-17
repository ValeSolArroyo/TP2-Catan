package edu.fiuba.algo3.modelo.patronHexagono;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.modelo.terrenos.Terreno;
import edu.fiuba.algo3.modelo.construcciones.Construccion;

import java.util.List;

public class EstadoSinLadron implements EstadoHexagono {
    @Override
    public void producirRecursos(Hexagono hexagono) {
        for (Vertice vertice : hexagono.obtenerVertices()) {
            Construccion construccion = vertice.obtenerConstruccion();
            hexagono.obtenerTipoTerreno().producirPara(construccion);
        }
    }
}
