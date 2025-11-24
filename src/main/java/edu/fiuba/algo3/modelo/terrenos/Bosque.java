package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.recursos.Madera;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.terrenosVisitor.Visitante;

public class Bosque implements Terreno {
    @Override
    public void producirPara(Construccion construccion) {
        construccion.producir(new Madera());
    }

    public int aceptar(Visitante visitante) {
        return visitante.visitar(this);
    }
}
