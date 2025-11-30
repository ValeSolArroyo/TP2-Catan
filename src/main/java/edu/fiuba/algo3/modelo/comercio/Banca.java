package edu.fiuba.algo3.modelo.comercio;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Banca implements ComercioJugador {
    private Recurso recursoAEntregar;
    private Recurso recursoDeseado;

    public Banca(Recurso recursoAEntregar, Recurso recursoDeseado) {
        this.recursoAEntregar = recursoAEntregar;
        this.recursoDeseado = recursoDeseado;
    }

    public void ejecutar(Jugador jugador) {
        List<Recurso> listaAEntregar = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            listaAEntregar.add(recursoAEntregar);
        }
        jugador.entregarRecursos(listaAEntregar);
        jugador.recibirRecurso(recursoDeseado);

    }
}
