//     0   1   2
//   3   4   5   6
// 7   8   9   10  11
//   12  13  14  15
//     16  17  18
// 19 hexágonos, 6 vértices por hexágono PERO HAY COMPARTIDOS
// 19 hexágonos, 54 vértices, 72 aristas TOTAL
//SUPUESTO: Se puede crear poblados en vértices adyacentes al mar → permite el comercio marítimo.
package edu.fiuba.algo3.modelo.tableroFactory;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.comercio.*;
import edu.fiuba.algo3.modelo.terrenos.*;

import java.util.*;

public class TableroCatanFactory implements TableroFactory {

    @Override
    public Tablero crearTablero() {
        List<Terreno> terrenosAleatorios = generarTerrenosAleatorios();
        List<Integer> fichasAleatorias = generarFichasAleatorias();
        return generarTablero(terrenosAleatorios, fichasAleatorias);
    }

    private Tablero generarTablero(List<Terreno> terrenos, List<Integer> fichas) {
        if (terrenos.size() != 19)
            throw new IllegalArgumentException("Se necesitan exactamente 19 terrenos");
        if (fichas.size() != 18)
            throw new IllegalArgumentException("Se necesitan exactamente 18 fichas");

        Map<Integer, Hexagono> hexagonosPorId = crearHexagonosConMapa(terrenos, fichas);
        List<Hexagono> hexagonos = new ArrayList<>(hexagonosPorId.values());
        int[] cantidadVerticesPorFila = {4, 5, 6, 6, 6, 6, 6, 5, 5, 4, 4, 3};

        Vertice[][] vertices = new Vertice[12][];
        Map<Integer, Vertice> verticesPorId = new HashMap<>();
        int verticeId = 1;

        for (int fila = 0; fila < 12; fila++) {
            vertices[fila] = new Vertice[cantidadVerticesPorFila[fila]];
            for (int columna = 0; columna < cantidadVerticesPorFila[fila]; columna++) {
                Vertice vertice = new Vertice(verticeId++);
                vertices[fila][columna] = vertice;
                verticesPorId.put(vertice.obtenerId(), vertice);
            }
        }

        Map<Integer, Arista> aristasPorId = new HashMap<>();
        Map<String, Arista> aristasUnicas = new HashMap<>();
        int aristaId = 1;

        int[] hexagonoPorFila = {3, 4, 5, 4, 3};
        int indice = 0;

        for (int fila = 0; fila < hexagonoPorFila.length; fila++) {
            int cantidadHexagonos = hexagonoPorFila[fila];

            for (int columna = 0; columna < cantidadHexagonos; columna++) {
                Hexagono hexagono = hexagonos.get(indice++);
                int filaVerticeSuperior = fila;
                if (fila >= 2) {
                    filaVerticeSuperior = fila + (fila < 2 ? 0 : fila - 1);
                }

                int filaVerticeInferior = filaVerticeSuperior + 1;
                int columnaSuperior1 = columna;
                int columnaSuperior2 = columna + 1;

                Vertice v1 = vertices[filaVerticeSuperior][columnaSuperior1];
                Vertice v2 = vertices[filaVerticeSuperior][columnaSuperior2];

                Vertice v3 = vertices[filaVerticeInferior][columnaSuperior2];
                Vertice v4 = vertices[filaVerticeInferior + 1][columnaSuperior2];
                Vertice v5 = vertices[filaVerticeInferior + 1][columnaSuperior1];
                Vertice v6 = vertices[filaVerticeInferior][columnaSuperior1];
                Vertice[] verticesHexagono = {v1, v2, v3, v4, v5, v6};

                for (Vertice vertice : verticesHexagono) {
                    hexagono.agregarVertice(vertice);
                }
                for (int i = 0; i < 6; i++) {
                    Vertice actual = verticesHexagono[i];
                    Vertice siguiente = verticesHexagono[(i + 1) % 6];
                    int idMenor = Math.min(actual.obtenerId(), siguiente.obtenerId());
                    int idMayor = Math.max(actual.obtenerId(), siguiente.obtenerId());
                    String claveUnica = idMenor + "-" + idMayor;

                    Arista arista;
                    if (!aristasUnicas.containsKey(claveUnica)) {
                        arista = new Arista(aristaId++, actual, siguiente);
                        aristasUnicas.put(claveUnica, arista);
                        aristasPorId.put(arista.obtenerId(), arista);
                        actual.agregarVecino(siguiente);
                        siguiente.agregarVecino(actual);
                    } else {
                        arista = aristasUnicas.get(claveUnica);
                    }
                    actual.agregarVecino(siguiente);
                    siguiente.agregarVecino(actual);
                    hexagono.agregarArista(arista);
                }
            }
        }
        
        asignarPuertos(verticesPorId);
        return new Tablero(hexagonos, verticesPorId, hexagonosPorId, aristasPorId);
    }

    private void asignarPuertos(Map<Integer, Vertice> mapa) {
        // Asignamos los puertos a los IDs correspondientes del mapa de 54 vértices.
        // Esto hace que vertice.encontrarVertice(1) tenga realmente un Puerto 3:1.

        asignarPuerto(mapa, 1, 2, new PuertoTresUno());
        asignarPuerto(mapa, 4, 8, new PuertoDosUnoMadera());
        asignarPuerto(mapa, 15, 21, new PuertoDosUnoLadrillo());
        asignarPuerto(mapa, 26, 32, new PuertoTresUno());
        asignarPuerto(mapa, 43, 48, new PuertoDosUnoLana());
        asignarPuerto(mapa, 50, 53, new PuertoTresUno());
        asignarPuerto(mapa, 39, 44, new PuertoTresUno());
        asignarPuerto(mapa, 22, 28, new PuertoDosUnoGrano());
        asignarPuerto(mapa, 7, 12, new PuertoDosUnoMineral());
    }

    private void asignarPuerto(Map<Integer, Vertice> mapa, int id1, int id2, Puerto puerto) {
        if (mapa.containsKey(id1)) mapa.get(id1).asignarPuerto(puerto);
        if (mapa.containsKey(id2)) mapa.get(id2).asignarPuerto(puerto);
    }

    private Map<Integer, Hexagono> crearHexagonosConMapa(List<Terreno> terrenos, List<Integer> fichas) {
        Map<Integer, Hexagono> hexagonosPorId = new HashMap<>();
        int indiceFicha = 0;
        int hexagonoId = 1;
        for (Terreno terrenoActual : terrenos) {
            int numeroFicha;
            if (terrenoActual instanceof Desierto) numeroFicha = 0;
            else numeroFicha = fichas.get(indiceFicha++);
            Hexagono hexagono = new Hexagono(hexagonoId++, terrenoActual, numeroFicha);
            hexagonosPorId.put(hexagono.obtenerId(), hexagono);
        }
        return hexagonosPorId;
    }

    private List<Terreno> generarTerrenosAleatorios() {
        List<Terreno> terrenos = new ArrayList<>();
        for (int i = 0; i < 4; i++) terrenos.add(new Bosque());
        for (int i = 0; i < 3; i++) terrenos.add(new Colina());
        for (int i = 0; i < 4; i++) terrenos.add(new Pastizal());
        for (int i = 0; i < 4; i++) terrenos.add(new Campo());
        for (int i = 0; i < 3; i++) terrenos.add(new Montaña());
        terrenos.add(new Desierto());
        Collections.shuffle(terrenos);
        return terrenos;
    }

    private List<Integer> generarFichasAleatorias() {
        List<Integer> fichas = new ArrayList<>(List.of(
                2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12
        ));
        Collections.shuffle(fichas);
        return fichas;
    }
}


