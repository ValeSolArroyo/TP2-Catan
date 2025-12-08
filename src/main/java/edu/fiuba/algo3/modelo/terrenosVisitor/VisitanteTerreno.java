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

    @Override
    public int visitarTerrenoProductor(Terreno terreno) {
        return obtenerSiguienteFicha();
    }

    @Override
    // Comportamiento especial para Desierto
    public int visitarDesierto(Desierto desierto) {
        return 0;
    }
}
