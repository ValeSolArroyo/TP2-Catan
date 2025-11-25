package edu.fiuba.algo3.modelo.comercio;

import edu.fiuba.algo3.modelo.excepciones.ComercioInvalidoError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;

public class PuertoGenerico implements Comercio {

    private final Vertice puerto;

    public PuertoGenerico(Vertice puerto) {
        this.puerto = puerto;
    }

    @Override
    public void validar(Jugador jugador, List<Recurso> recursosEntregados) {
        if (recursosEntregados.size() != 3)
            throw new ComercioInvalidoError("El puerto genérico exige 3:1");

        jugador.tieneConstruccionEn(puerto);
        Recurso tipoRecurso = recursosEntregados.get(0);


        for (Recurso recurso : recursosEntregados) {
            if (!recurso.equals(tipoRecurso))
                throw new ComercioInvalidoError("Las 3 cartas deben ser iguales");
        }

        jugador.tieneRecursos(tipoRecurso, 3);
    }

    @Override
    public void ejecutar(Jugador jugador, List<Recurso> entregados, Recurso deseado) {
        jugador.entregarRecursos(entregados);
        jugador.recibirRecurso(deseado);
    }
}
