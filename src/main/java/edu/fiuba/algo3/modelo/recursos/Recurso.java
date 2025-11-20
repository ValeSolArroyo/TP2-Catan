package edu.fiuba.algo3.modelo.recursos;

import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;

public interface Recurso {
    void asignarA(Jugador jugador);
    void agregarseA(Inventario inventario);
    void restarseDe(Inventario inventario, int cantidad);
}
