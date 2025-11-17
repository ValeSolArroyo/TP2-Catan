package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.recursos.Mineral;
import edu.fiuba.algo3.modelo.construcciones.Construccion;

public class Montaña {
    @Override
    public void producirPara(Construccion construccion, Jugador jugador) {
        construccion.producir(jugador, new Mineral());
    }
}
