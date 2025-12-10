package edu.fiuba.algo3.modelo.cartasBonificacion;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;


public interface CartaBonificacion {
    boolean evaluarCartaBonificacion(Jugador jugador, Tablero tablero);
}
