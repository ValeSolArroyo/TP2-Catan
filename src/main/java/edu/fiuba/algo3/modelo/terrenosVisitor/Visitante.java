package edu.fiuba.algo3.modelo.terrenosVisitor;

import edu.fiuba.algo3.modelo.terrenos.*;

public interface Visitante {
    int visitar(Bosque bosque);
    int visitar(Colina colina);
    int visitar(Pastizal pastizal);
    int visitar(Campo campo);
    int visitar(Montaña montaña);
    int visitar(Desierto desierto);
}


