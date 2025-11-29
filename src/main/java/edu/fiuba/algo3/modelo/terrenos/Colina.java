package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.recursos.Ladrillo;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.terrenosVisitor.Visitante;

public class Colina implements Terreno {
    @Override
    public void producirPara(Construccion construccion) {
        construccion.producir(new Ladrillo());
    }

    public int aceptar(Visitante visitante) {
        return visitante.visitarTerrenoProductor(this);
    }
}
