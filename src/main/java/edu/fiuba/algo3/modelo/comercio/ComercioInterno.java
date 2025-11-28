package edu.fiuba.algo3.modelo.comercio;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;

public class ComercioInterno implements Comercio {

    private final Jugador oferente;

    public ComercioInterno(Jugador oferente) {
        this.oferente = oferente;
    }

    @Override
    public void ejecutar(Jugador aceptante, Vertice verticePuerto, List<Recurso> recursosAEntregar, List<Recurso> recursosDeseados) {
        aceptante.tieneRecursos(recursosDeseados.get(0), recursosDeseados.size());
        oferente.tieneRecursos(recursosAEntregar.get(0), recursosAEntregar.size());

        aceptante.entregarRecursos(recursosDeseados);
        for (Recurso recurso: recursosDeseados) {
            oferente.recibirRecurso(recurso);
        }

        oferente.entregarRecursos(recursosAEntregar);
        for (Recurso recurso: recursosAEntregar) {
            aceptante.recibirRecurso(recurso);
        }
    }
}
