package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.recursos.Lana;
import edu.fiuba.algo3.modelo.construcciones.Construccion;

public class Pastizal implements Terreno {
    @Override
    public void producirPara(Construccion construccion) {
        construccion.producir(new Lana());
    }
}
