package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.recursos.Mineral;
import edu.fiuba.algo3.modelo.construcciones.Construccion;

public class Montaña implements Terreno {
    @Override
    public void producirPara(Construccion construccion) {
        construccion.producir(new Mineral());
    }
}
