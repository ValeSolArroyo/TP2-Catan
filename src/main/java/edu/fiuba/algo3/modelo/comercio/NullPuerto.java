package edu.fiuba.algo3.modelo.comercio;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;

public class NullPuerto implements Comercio {
    @Override
    public void ejecutar(Jugador jugador, Vertice verticePuerto, List<Recurso> recursosEntregados, List<Recurso> recursosDeseados) {
        // TODO: agregamos exepción sí o no --> ver con interfaz...
    }
}
