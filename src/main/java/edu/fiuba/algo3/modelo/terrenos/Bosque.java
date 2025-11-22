package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.recursos.Madera;
import edu.fiuba.algo3.modelo.construcciones.Construccion;

public class Bosque implements Terreno {
    @Override
    public void producirPara(Construccion construccion) {
        construccion.producir(new Madera());
    }
}
