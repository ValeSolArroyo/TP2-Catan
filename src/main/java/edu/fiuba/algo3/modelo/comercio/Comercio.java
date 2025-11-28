package edu.fiuba.algo3.modelo.comercio;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;

public interface Comercio {
    void ejecutar(Jugador jugador, Vertice verticePuerto, List<Recurso> recursosEntregados, List<Recurso> recursosDeseados);
}
