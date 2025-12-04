package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public interface EspacioConstruible {
    void construirPoblado(Jugador jugador, Construccion construccion);
    void construirCiudad(Jugador jugador, Construccion construccion);
    void construirCarretera(Jugador jugador, Construccion construccion);
}
