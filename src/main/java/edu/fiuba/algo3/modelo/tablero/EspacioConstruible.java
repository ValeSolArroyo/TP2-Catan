package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public interface EspacioConstruible {
    void validarPoblado(Jugador jugador);
    void validarCiudad(Jugador jugador);
    void validarCarretera(Jugador jugador);
    void asignarConstruccion(Construccion construccion);
    public boolean contieneConstruccionDeTipo(Construccion construccion);

}
