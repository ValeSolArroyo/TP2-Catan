package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.construcciones.Construccion;

public interface Terreno {
    void producirPara(Construccion construccion, Jugador jugador);
}
