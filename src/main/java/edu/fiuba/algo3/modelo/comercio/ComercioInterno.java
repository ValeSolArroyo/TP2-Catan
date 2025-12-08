package edu.fiuba.algo3.modelo.comercio;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;

public class ComercioInterno implements ComercioJugador {
    private final Jugador oferente;
    private List<Recurso> recursosAEntregar;
    private List<Recurso> recursosDeseados;

    public ComercioInterno(Jugador oferente, List<Recurso> recursosAEntregar, List<Recurso> recursosDeseados) {
        this.oferente = oferente;
        this.recursosAEntregar = recursosAEntregar;
        this.recursosDeseados = recursosDeseados;
    }

    public void ejecutar(Jugador aceptante) {
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
