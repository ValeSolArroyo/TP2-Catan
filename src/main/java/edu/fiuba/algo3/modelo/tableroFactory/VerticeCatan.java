package edu.fiuba.algo3.modelo.tableroFactory;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

// Configuracion específica para esta versión de Catan

public class VerticeCatan {

    private final List<Vertice> todosLosVertices;

    public VerticeCatan(List<Vertice> todosLosVertices) {
        this.todosLosVertices = todosLosVertices;
    }

    public void asignarVertices(List<Hexagono> hexagonos) {
        // Hexágono 0
        hexagonos.get(0).agregarVertice(todosLosVertices.get(0));
        hexagonos.get(0).agregarVertice(todosLosVertices.get(1));
        hexagonos.get(0).agregarVertice(todosLosVertices.get(2));
        hexagonos.get(0).agregarVertice(todosLosVertices.get(3));
        hexagonos.get(0).agregarVertice(todosLosVertices.get(4));
        hexagonos.get(0).agregarVertice(todosLosVertices.get(5));

        // Hexágono 1
        hexagonos.get(1).agregarVertice(todosLosVertices.get(6));
        hexagonos.get(1).agregarVertice(todosLosVertices.get(7));
        hexagonos.get(1).agregarVertice(todosLosVertices.get(8));
        hexagonos.get(1).agregarVertice(todosLosVertices.get(9));
        hexagonos.get(1).agregarVertice(todosLosVertices.get(2));
        hexagonos.get(1).agregarVertice(todosLosVertices.get(1));

        // Hexágono 2
        hexagonos.get(2).agregarVertice(todosLosVertices.get(10));
        hexagonos.get(2).agregarVertice(todosLosVertices.get(11));
        hexagonos.get(2).agregarVertice(todosLosVertices.get(12));
        hexagonos.get(2).agregarVertice(todosLosVertices.get(13));
        hexagonos.get(2).agregarVertice(todosLosVertices.get(8));
        hexagonos.get(2).agregarVertice(todosLosVertices.get(7));

        // Hexágono 3
        hexagonos.get(3).agregarVertice(todosLosVertices.get(4));
        hexagonos.get(3).agregarVertice(todosLosVertices.get(3));
        hexagonos.get(3).agregarVertice(todosLosVertices.get(17));
        hexagonos.get(3).agregarVertice(todosLosVertices.get(16));
        hexagonos.get(3).agregarVertice(todosLosVertices.get(15));
        hexagonos.get(3).agregarVertice(todosLosVertices.get(14));

        // Hexágono 4
        hexagonos.get(4).agregarVertice(todosLosVertices.get(2));
        hexagonos.get(4).agregarVertice(todosLosVertices.get(9));
        hexagonos.get(4).agregarVertice(todosLosVertices.get(19));
        hexagonos.get(4).agregarVertice(todosLosVertices.get(18));
        hexagonos.get(4).agregarVertice(todosLosVertices.get(17));
        hexagonos.get(4).agregarVertice(todosLosVertices.get(3));

        // Hexágono 5
        hexagonos.get(5).agregarVertice(todosLosVertices.get(8));
        hexagonos.get(5).agregarVertice(todosLosVertices.get(13));
        hexagonos.get(5).agregarVertice(todosLosVertices.get(21));
        hexagonos.get(5).agregarVertice(todosLosVertices.get(20));
        hexagonos.get(5).agregarVertice(todosLosVertices.get(19));
        hexagonos.get(5).agregarVertice(todosLosVertices.get(9));

        // Hexágono 6
        hexagonos.get(6).agregarVertice(todosLosVertices.get(12));
        hexagonos.get(6).agregarVertice(todosLosVertices.get(24));
        hexagonos.get(6).agregarVertice(todosLosVertices.get(23));
        hexagonos.get(6).agregarVertice(todosLosVertices.get(22));
        hexagonos.get(6).agregarVertice(todosLosVertices.get(21));
        hexagonos.get(6).agregarVertice(todosLosVertices.get(13));

        // Hexágono 7
        hexagonos.get(7).agregarVertice(todosLosVertices.get(15));
        hexagonos.get(7).agregarVertice(todosLosVertices.get(16));
        hexagonos.get(7).agregarVertice(todosLosVertices.get(28));
        hexagonos.get(7).agregarVertice(todosLosVertices.get(27));
        hexagonos.get(7).agregarVertice(todosLosVertices.get(26));
        hexagonos.get(7).agregarVertice(todosLosVertices.get(25));

        // Hexágono 8
        hexagonos.get(8).agregarVertice(todosLosVertices.get(17));
        hexagonos.get(8).agregarVertice(todosLosVertices.get(18));
        hexagonos.get(8).agregarVertice(todosLosVertices.get(30));
        hexagonos.get(8).agregarVertice(todosLosVertices.get(29));
        hexagonos.get(8).agregarVertice(todosLosVertices.get(28));
        hexagonos.get(8).agregarVertice(todosLosVertices.get(16));

        // Hexágono 9
        hexagonos.get(9).agregarVertice(todosLosVertices.get(19));
        hexagonos.get(9).agregarVertice(todosLosVertices.get(20));
        hexagonos.get(9).agregarVertice(todosLosVertices.get(32));
        hexagonos.get(9).agregarVertice(todosLosVertices.get(31));
        hexagonos.get(9).agregarVertice(todosLosVertices.get(30));
        hexagonos.get(9).agregarVertice(todosLosVertices.get(18));

        // Hexágono 10
        hexagonos.get(10).agregarVertice(todosLosVertices.get(21));
        hexagonos.get(10).agregarVertice(todosLosVertices.get(22));
        hexagonos.get(10).agregarVertice(todosLosVertices.get(34));
        hexagonos.get(10).agregarVertice(todosLosVertices.get(33));
        hexagonos.get(10).agregarVertice(todosLosVertices.get(32));
        hexagonos.get(10).agregarVertice(todosLosVertices.get(20));

        // Hexágono 11
        hexagonos.get(11).agregarVertice(todosLosVertices.get(23));
        hexagonos.get(11).agregarVertice(todosLosVertices.get(37));
        hexagonos.get(11).agregarVertice(todosLosVertices.get(36));
        hexagonos.get(11).agregarVertice(todosLosVertices.get(35));
        hexagonos.get(11).agregarVertice(todosLosVertices.get(34));
        hexagonos.get(11).agregarVertice(todosLosVertices.get(22));

        // Hexágono 12
        hexagonos.get(12).agregarVertice(todosLosVertices.get(28));
        hexagonos.get(12).agregarVertice(todosLosVertices.get(29));
        hexagonos.get(12).agregarVertice(todosLosVertices.get(40));
        hexagonos.get(12).agregarVertice(todosLosVertices.get(39));
        hexagonos.get(12).agregarVertice(todosLosVertices.get(38));
        hexagonos.get(12).agregarVertice(todosLosVertices.get(27));

        // Hexágono 13
        hexagonos.get(13).agregarVertice(todosLosVertices.get(30));
        hexagonos.get(13).agregarVertice(todosLosVertices.get(31));
        hexagonos.get(13).agregarVertice(todosLosVertices.get(42));
        hexagonos.get(13).agregarVertice(todosLosVertices.get(41));
        hexagonos.get(13).agregarVertice(todosLosVertices.get(40));
        hexagonos.get(13).agregarVertice(todosLosVertices.get(29));

        // Hexágono 14
        hexagonos.get(14).agregarVertice(todosLosVertices.get(32));
        hexagonos.get(14).agregarVertice(todosLosVertices.get(33));
        hexagonos.get(14).agregarVertice(todosLosVertices.get(44));
        hexagonos.get(14).agregarVertice(todosLosVertices.get(43));
        hexagonos.get(14).agregarVertice(todosLosVertices.get(42));
        hexagonos.get(14).agregarVertice(todosLosVertices.get(31));

        // Hexágono 15
        hexagonos.get(15).agregarVertice(todosLosVertices.get(34));
        hexagonos.get(15).agregarVertice(todosLosVertices.get(35));
        hexagonos.get(15).agregarVertice(todosLosVertices.get(46));
        hexagonos.get(15).agregarVertice(todosLosVertices.get(45));
        hexagonos.get(15).agregarVertice(todosLosVertices.get(44));
        hexagonos.get(15).agregarVertice(todosLosVertices.get(33));

        // Hexágono 16
        hexagonos.get(16).agregarVertice(todosLosVertices.get(40));
        hexagonos.get(16).agregarVertice(todosLosVertices.get(41));
        hexagonos.get(16).agregarVertice(todosLosVertices.get(49));
        hexagonos.get(16).agregarVertice(todosLosVertices.get(48));
        hexagonos.get(16).agregarVertice(todosLosVertices.get(47));
        hexagonos.get(16).agregarVertice(todosLosVertices.get(39));

        // Hexágono 17
        hexagonos.get(17).agregarVertice(todosLosVertices.get(42));
        hexagonos.get(17).agregarVertice(todosLosVertices.get(43));
        hexagonos.get(17).agregarVertice(todosLosVertices.get(51));
        hexagonos.get(17).agregarVertice(todosLosVertices.get(50));
        hexagonos.get(17).agregarVertice(todosLosVertices.get(49));
        hexagonos.get(17).agregarVertice(todosLosVertices.get(41));

        // Hexágono 18
        hexagonos.get(18).agregarVertice(todosLosVertices.get(44));
        hexagonos.get(18).agregarVertice(todosLosVertices.get(45));
        hexagonos.get(18).agregarVertice(todosLosVertices.get(53));
        hexagonos.get(18).agregarVertice(todosLosVertices.get(52));
        hexagonos.get(18).agregarVertice(todosLosVertices.get(51));
        hexagonos.get(18).agregarVertice(todosLosVertices.get(43));
    }

    public Set<Vertice> identificarVerticesBorde() {
        Set<Vertice> verticesBorde = new HashSet<>();

        List<Integer> idsBorde = List.of(0, 6, 10, 5, 1, 7, 11,
                4, 12, 14, 24, 15, 23, 25, 37, 26, 36, 27, 35, 38, 46, 39, 45,
                47, 49, 51, 53, 48, 50, 52
        );

        for (int id : idsBorde) {
            if (id >= 0 && id < todosLosVertices.size()) {
                verticesBorde.add(todosLosVertices.get(id));
            }
        }
        return verticesBorde;
    }
}
