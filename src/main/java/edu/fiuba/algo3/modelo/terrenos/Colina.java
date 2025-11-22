package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.recursos.Ladrillo;
import edu.fiuba.algo3.modelo.construcciones.Construccion;

public class Colina implements Terreno {
    @Override
    public void producirPara(Construccion construccion) {
        construccion.producir(new Ladrillo());
    }
}
