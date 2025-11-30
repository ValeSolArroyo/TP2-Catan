package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.recursos.Mineral;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.terrenosVisitor.Visitante;

public class Montaña implements Terreno {
    @Override
    public void producirPara(Construccion construccion) {
         construccion.producir(new Mineral());
    }

    public int aceptar(Visitante visitante) {
        return visitante.visitarTerrenoProductor(this);
    }

    @Override
    public void colocarLadron(Hexagono hexagono) {

    }
}
