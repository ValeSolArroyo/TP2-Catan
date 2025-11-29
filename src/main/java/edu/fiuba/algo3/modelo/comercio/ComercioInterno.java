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

        for (Recurso recurso: recursosDeseados) {
            aceptante.darRecursos(recurso, 1);
            oferente.recibirRecurso(recurso);
        }

        for (Recurso recurso: recursosAEntregar) {
            oferente.darRecursos(recurso, 1);
            aceptante.recibirRecurso(recurso);
        }
    }
}
