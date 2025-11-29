package edu.fiuba.algo3.modelo.terrenosVisitor;

import edu.fiuba.algo3.modelo.terrenos.*;

public interface Visitante {
    int visitarTerrenoProductor(Terreno terreno);
    int visitarDesierto(Desierto desierto);
}



