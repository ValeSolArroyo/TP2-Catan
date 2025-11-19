package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.excepciones.HexagonoInexistenteError;
import edu.fiuba.algo3.modelo.excepciones.VerticeInexistenteError;

import java.util.Collections;

public class Tablero {
    //     0   1   2
    //   3   4   5   6
    // 7   8   9   10  11
    //   12  13  14  15
    //     16  17  18
    // 19 hexágonos, 6 vértices por hexágono PERO HAY COMPARTIDOS
    // 19 hexágonos, 54 vértices, 72 aristas TOTAL
    //SUPUESTO: Se puede crear poblados en vértices adyacentes al mar → permite el comercio marítimo.

    private List<Hexagono> hexagonos;

    public Tablero(List<Hexagono> hexagonos) {
        this.hexagonos = hexagonos;
    }

    public void producir(int numero) {
        for (Hexagono hexagono : hexagonos) {
                hexagono.producirRecursos(numero);
        }
    }

}



