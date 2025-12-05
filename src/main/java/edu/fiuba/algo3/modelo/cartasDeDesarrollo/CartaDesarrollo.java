package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public interface CartaDesarrollo extends Accion {
    void ejecutarAlGuardar(Jugador jugador);
    String getCarta();
}