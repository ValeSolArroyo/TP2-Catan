package edu.fiuba.algo3.modelo.comercio;

import edu.fiuba.algo3.modelo.excepciones.ComercioInvalidoError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;

public class PuertoEspecial implements Comercio {

    private final Recurso tipoEspecial;

    public PuertoEspecial(Recurso tipoEspecial) {
        this.tipoEspecial = tipoEspecial;
    }

    @Override
    public void ejecutar(Jugador jugador, Vertice verticePuerto, List<Recurso> recursosEntregados, List<Recurso> recursosDeseados) {
        if (recursosEntregados.size() != 2)
            throw new ComercioInvalidoError("Este puerto especial es 2:1");

        jugador.tieneConstruccionEn(verticePuerto);

        for (Recurso recurso : recursosEntregados) {
            if (!recurso.equals(tipoEspecial))
                throw new ComercioInvalidoError("Este puerto solo acepta recursos del tipo: " + tipoEspecial);
        }

        jugador.tieneRecursos(tipoEspecial, 2);

        jugador.entregarRecursos(recursosEntregados);
        for (Recurso recurso: recursosDeseados){
            jugador.recibirRecurso(recurso);
        }
    }
}

