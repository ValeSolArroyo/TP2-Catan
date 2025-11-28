package edu.fiuba.algo3.modelo.comercio;

import edu.fiuba.algo3.modelo.excepciones.ComercioInvalidoError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;

public class Banca implements Comercio {

    @Override
    public void ejecutar(Jugador jugador, Vertice verticePuerto, List<Recurso> recursosEntregados, List<Recurso> recursosDeseados) {
        if (recursosEntregados.size() != 4) {
            throw new ComercioInvalidoError("El comercio con la banca es 4:1");
        }

        Recurso tipoRecurso = recursosEntregados.get(0);

        for (Recurso recurso : recursosEntregados) {
            if (!recurso.equals(tipoRecurso))
                throw new ComercioInvalidoError("Las 4 cartas deben ser iguales");
        }

        jugador.tieneRecursos(tipoRecurso, 4);

        jugador.entregarRecursos(recursosEntregados);
        for (Recurso recurso: recursosDeseados) {
            jugador.recibirRecurso(recurso);
        }
    }
}
