package edu.fiuba.algo3.modelo.recursos;

import edu.fiuba.algo3.modelo.jugador.Inventario;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public interface Recurso {
    void asignarA(Jugador jugador);
    void eliminarDe(Inventario inventario);
    boolean brinda(String recurso);
}
