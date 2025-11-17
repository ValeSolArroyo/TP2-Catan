package edu.fiuba.algo3.modelo.patronHexagono;

import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.modelo.terrenos.Terreno;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.construcciones.Construccion;

import java.util.List;

public class EstadoSinLadron implements EstadoHexagono {
    private List<Vertice> vertices;
    private Terreno terreno;

    @Override
    public void producirRecursos(Hexagono hexagono) {

        for (Vertice vertice : vertices) {
            Construccion construccion = vertice.obtenerConstruccion();
            Terreno terreno = this.terreno;
            terreno.producirPara(construccion, vertice.getJugadorDuenio());
        }
    }

}
