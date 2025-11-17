package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.recursos.Lana;
import edu.fiuba.algo3.modelo.construcciones.Construccion;

public class Pastizal {
    @Override
    public void producirPara(Construccion construccion, Jugador jugador) {
        construccion.producir(jugador, new Lana());
    }
}
