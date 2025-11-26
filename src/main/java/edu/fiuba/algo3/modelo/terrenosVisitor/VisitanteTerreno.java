package edu.fiuba.algo3.modelo.terrenosVisitor;

import edu.fiuba.algo3.modelo.terrenos.*;
import java.util.List;

public class VisitanteTerreno implements Visitante {
    private final List<Integer> fichas;
    private int indiceFicha = 0;

    public VisitanteTerreno(List<Integer> fichas) {
        this.fichas = fichas;
    }

    private int obtenerSiguienteFicha() {
        return fichas.get(indiceFicha++);
    }

    public int visitar(Bosque bosque) { return obtenerSiguienteFicha(); }
    public int visitar(Colina colina) { return obtenerSiguienteFicha(); }
    public int visitar(Pastizal pastizal) { return obtenerSiguienteFicha(); }
    public int visitar(Campo campo) { return obtenerSiguienteFicha(); }
    public int visitar(Montaña montaña) { return obtenerSiguienteFicha(); }

    // Comportamiento especial para Desierto
    public int visitar(Desierto desierto) { return 0; }
}
