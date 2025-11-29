package edu.fiuba.algo3.modelo.comercio;

import edu.fiuba.algo3.modelo.excepciones.ComercioInvalidoError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;

public class PuertoGenerico implements Comercio {

    public PuertoGenerico() {
    }

    @Override
    public void ejecutar(Jugador jugador, Vertice verticePuerto, List<Recurso> recursosEntregados, List<Recurso> recursosDeseados) {
        if (recursosEntregados.size() != 3) {
            throw new ComercioInvalidoError("El puerto genérico exige 3:1");
        }

        jugador.tieneConstruccionEn(verticePuerto);

        Recurso tipoRecurso = recursosEntregados.get(0);

        for (Recurso recurso : recursosEntregados) {
            if (!recurso.equals(tipoRecurso)) {
                throw new ComercioInvalidoError("Las 3 cartas deben ser iguales");
            }
        }

        jugador.tieneRecursos(tipoRecurso, 3);

        jugador.entregarRecursos(recursosEntregados);
        for (Recurso recurso: recursosDeseados) {
            jugador.recibirRecurso(recurso);
        }
    }
}
