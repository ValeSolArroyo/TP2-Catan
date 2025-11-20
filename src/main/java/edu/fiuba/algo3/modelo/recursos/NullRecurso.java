package edu.fiuba.algo3.modelo.recursos;

import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;

public class NullRecurso implements Recurso {
    @Override
    public void asignarA(Jugador jugador) {
        // No hace nada
    }

    @Override
    public void agregarseA(Inventario inventario) {
        // No hace nada
    }

    @Override
    public void restarseDe(Inventario inventario, int cantidad) {
        // No hace nada
    }
}
