package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.recursos.Grano;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.terrenosVisitor.Visitante;

public class Campo implements Terreno {
    @Override
    public void producirPara(Construccion construccion) {
        construccion.producir(new Grano());
    }

    public int aceptar(Visitante visitante) {
        return visitante.visitarTerrenoProductor(this);
    }
}
