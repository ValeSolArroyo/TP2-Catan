package edu.fiuba.algo3.modelo.comercio;

import edu.fiuba.algo3.modelo.excepciones.ComercioInvalidoError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;

public class PuertoEspecial implements Comercio {

    private final Recurso tipoEspecial;
    private final Vertice puerto;

    public PuertoEspecial(Vertice puerto, Recurso tipoEspecial) {
        this.tipoEspecial = tipoEspecial;
        this.puerto = puerto;
    }

    @Override
    public void validar(Jugador jugador, List<Recurso> recursosEntregados) {
        if (recursosEntregados.size() != 2)
            throw new ComercioInvalidoError("Este puerto especial es 2:1");

        jugador.tieneConstruccionEn(puerto);

        for (Recurso recurso : recursosEntregados) {
            if (!recurso.equals(tipoEspecial))
                throw new ComercioInvalidoError("Este puerto solo acepta recursos del tipo: " + tipoEspecial);
        }

        jugador.tieneRecursos(tipoEspecial, 2);
    }

    @Override
    public void ejecutar(Jugador jugador, List<Recurso> recursosEntregados, Recurso recursoDeseado) {
        jugador.entregarRecursos(recursosEntregados);
        jugador.recibirRecurso(recursoDeseado);
    }
}

