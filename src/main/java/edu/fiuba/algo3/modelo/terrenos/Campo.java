package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.recursos.Grano;
import edu.fiuba.algo3.modelo.construcciones.Construccion;

public class Campo {
    @Override
    public void producirPara(Construccion construccion, Jugador jugador) {
        construccion.producir(jugador, new Grano());
    }
}
