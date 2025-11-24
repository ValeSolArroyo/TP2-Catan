package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.terrenosVisitor.Visitante;

public interface Terreno {
    void producirPara(Construccion construccion);
    int aceptar(Visitante visitante);
}
