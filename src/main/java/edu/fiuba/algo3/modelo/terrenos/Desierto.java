package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.terrenosVisitor.Visitante;

public class Desierto implements Terreno {
    @Override
    public void producirPara(Construccion construccion) {
        // No produce nada
    }

    public int aceptar(Visitante visitante) {
        return visitante.visitarDesierto(this);
    }
}
