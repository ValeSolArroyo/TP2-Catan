package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.recursos.Madera;
import edu.fiuba.algo3.modelo.construcciones.Construccion;

public class Bosque {
    @Override
    public void producirPara(Construccion construccion, Jugador jugador) {
        construccion.producir(jugador, new Madera());
    }
}
