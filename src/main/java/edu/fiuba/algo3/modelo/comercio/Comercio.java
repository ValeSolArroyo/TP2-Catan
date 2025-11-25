package edu.fiuba.algo3.modelo.comercio;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import java.util.List;

public interface Comercio {

    void validar(Jugador jugador, List<Recurso> recursosEntregados);

    void ejecutar(Jugador jugador, List<Recurso> recursosEntregados, Recurso recursoDeseado);

}
