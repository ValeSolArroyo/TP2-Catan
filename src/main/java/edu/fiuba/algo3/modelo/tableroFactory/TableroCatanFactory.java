package edu.fiuba.algo3.modelo.tableroFactory;

import edu.fiuba.algo3.modelo.comercio.PuertoEspecial;
import edu.fiuba.algo3.modelo.comercio.PuertoGenerico;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.terrenos.*;
import edu.fiuba.algo3.modelo.terrenosVisitor.VisitanteTerreno;

import java.util.*;

//     0   1   2
//   3   4   5   6
// 7   8   9   10  11
//   12  13  14  15
//     16  17  18
// 19 hexágonos, 6 vértices por hexágono PERO HAY COMPARTIDOS
// 19 hexágonos, 54 vértices, 72 aristas TOTAL
//SUPUESTO: Se puede crear poblados en vértices adyacentes al mar → permite el comercio marítimo.

public class TableroCatanFactory implements TableroFactory {
    private Set<Vertice> verticesBorde;

    @Override
    public Tablero crearTablero() {
        this.verticesBorde = new HashSet<>();
        List<Terreno> terrenosAleatorios = generarTerrenosAleatorios();
        List<Integer> fichasAleatorias = generarFichasAleatorias();
        return construirTablero(terrenosAleatorios, fichasAleatorias);
    }

    private Tablero construirTablero(List<Terreno> terrenos, List<Integer> fichas) {
        if (terrenos.size() != 19)
            throw new IllegalArgumentException("Se necesitan exactamente 19 terrenos");
        if (fichas.size() != 18)
            throw new IllegalArgumentException("Se necesitan exactamente 18 fichas");

        List<Hexagono> hexagonos = crearHexagonos(terrenos, fichas);
        List<Integer> cantidadVerticesPorFila = List.of(3, 4, 4, 5, 5, 6, 6, 5, 5, 4, 4, 3);
        Vertice[][] vertices = inicializarVertices(cantidadVerticesPorFila);
        List<Arista> aristas = new ArrayList<>();

        int[] hexagonoPorFila = {3, 4, 5, 4, 3};
        int indice = 0;

        for (int filaHex = 0; filaHex < hexagonoPorFila.length; filaHex++) {
            int cantidadHexagonos = hexagonoPorFila[filaHex];
            int filaVerticeSuperior = filaHex * 2;
            int filaVerticeInferior = filaVerticeSuperior + 1;

            int ajusteColumna = 0;
            if (filaHex == 1) ajusteColumna = 1;
            else if (filaHex == 2) ajusteColumna = 2;
            else if (filaHex == 3) ajusteColumna = 1;

            for (int colHex = 0; colHex < cantidadHexagonos; colHex++) {
                Hexagono hexagono = hexagonos.get(indice++);
                Vertice[] verticesHexagono = obtenerVerticesHexagono(
                        vertices, filaVerticeSuperior, filaVerticeInferior, ajusteColumna, colHex
                );

                for (Vertice vertice : verticesHexagono) {
                    hexagono.agregarVertice(vertice);
                }
                conectarAristas(verticesHexagono, hexagono, aristas);
            }
        }

        identificarVerticesBorde(vertices, cantidadVerticesPorFila);
        asignarPuertos();

        return new Tablero(hexagonos);
    }

    private Vertice[][] inicializarVertices(List<Integer> cantidadVerticesPorFila) {
        Vertice[][] vertices = new Vertice[cantidadVerticesPorFila.size()][];
        for (int fila = 0; fila < cantidadVerticesPorFila.size(); fila++) {
            int columnas = cantidadVerticesPorFila.get(fila);
            vertices[fila] = new Vertice[columnas];
            for (int columna = 0; columna < columnas; columna++) {
                vertices[fila][columna] = new Vertice();
            }
        }
        return vertices;
    }

    private void identificarVerticesBorde(Vertice[][] vertices, List<Integer> cantidadVerticesPorFila) {
        // Filas 0, 1, 10 y 11: TODOS los vértices son de borde
        int[] filasCompletas = {0, 1, 10, 11};
        for (int fila : filasCompletas) {
            for (int col = 0; col < vertices[fila].length; col++) {
                verticesBorde.add(vertices[fila][col]);
            }
        }

        // Filas 2-9: solo primer y último vértice son de borde
        for (int fila = 2; fila < 10; fila++) {
            verticesBorde.add(vertices[fila][0]);

            int ultimaColumna = cantidadVerticesPorFila.get(fila) - 1;
            verticesBorde.add(vertices[fila][ultimaColumna]);
        }
    }

    private void asignarPuertos() {
        List<Vertice> verticesDisponibles = new ArrayList<>(verticesBorde);
        Collections.shuffle(verticesDisponibles);

        // 4 puertos genéricos
        for (int i = 0; i < 4; i++) {
            Vertice vertice = verticesDisponibles.get(i);
            vertice.asignarPuerto(new PuertoGenerico());
        }

        // 5 puertos especiales (uno de cada recurso)
        List<Recurso> recursosEspeciales = List.of(
                new Madera(),
                new Ladrillo(),
                new Lana(),
                new Grano(),
                new Mineral()
        );

        for (int i = 0; i < 5; i++) {
            Vertice vertice = verticesDisponibles.get(4 + i);
            Recurso recursoEspecial = recursosEspeciales.get(i);
            vertice.asignarPuerto(new PuertoEspecial(recursoEspecial));
        }
    }

    private Vertice[] obtenerVerticesHexagono(Vertice[][] vertices,
                                              int filaVerticeSuperior, int filaVerticeInferior,
                                              int ajusteColumna, int colHex) {
        int colVerticeBase = colHex * 2 + ajusteColumna;

        int maxColSuperior = vertices[filaVerticeSuperior].length - 1;
        int maxColInferior = vertices[filaVerticeInferior].length - 1;
        int maxColInferiorMas1 = vertices[filaVerticeInferior + 1].length - 1;

        int colV1 = Math.min(colVerticeBase, maxColSuperior);
        int colV2 = Math.min(colVerticeBase + 1, maxColSuperior);
        int colV3 = Math.min(colVerticeBase + 1, maxColInferior);
        int colV4 = Math.min(colVerticeBase, maxColInferiorMas1);
        int colV5 = Math.max(0, Math.min(colVerticeBase - 1, maxColInferior));
        int colV6 = Math.min(colVerticeBase, maxColInferior);

        Vertice v1 = vertices[filaVerticeSuperior][colV1];
        Vertice v2 = vertices[filaVerticeSuperior][colV2];
        Vertice v3 = vertices[filaVerticeInferior][colV3];
        Vertice v4 = vertices[filaVerticeInferior + 1][colV4];
        Vertice v5 = vertices[filaVerticeInferior][colV5];
        Vertice v6 = vertices[filaVerticeInferior][colV6];

        return new Vertice[]{v1, v2, v3, v4, v5, v6};
    }

    private void conectarAristas(Vertice[] verticesHexagono, Hexagono hexagono, List<Arista> aristas) {
        for (int i = 0; i < 6; i++) {
            Vertice actual = verticesHexagono[i];
            Vertice siguiente = verticesHexagono[(i + 1) % 6];

            // Verificar si ya existe una arista entre estos dos vértices
            boolean aristaExistente = false;
            for (Arista arista : aristas) {
                if (arista.conectaVertices(actual, siguiente)) {
                    aristaExistente = true;
                    hexagono.agregarArista(arista);
                    break;
                }
            }

            if (!aristaExistente) {
                Arista arista = new Arista(actual, siguiente);
                aristas.add(arista);
                actual.agregarVecino(siguiente);
                siguiente.agregarVecino(actual);
                hexagono.agregarArista(arista);
            }
        }
    }

    public List<Hexagono> crearHexagonos(List<Terreno> terrenos, List<Integer> fichas) {
        List<Hexagono> hexagonos = new ArrayList<>();
        VisitanteTerreno visitante = new VisitanteTerreno(fichas);

        for (Terreno terrenoActual : terrenos) {
            int numeroFicha = terrenoActual.aceptar(visitante);
            hexagonos.add(new Hexagono(terrenoActual, numeroFicha));
        }
        return hexagonos;
    }

    public List<Terreno> generarTerrenosAleatorios() {
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

    public List<Integer> generarFichasAleatorias() {
        List<Integer> fichas = new ArrayList<>(List.of(
                2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12
        ));
        Collections.shuffle(fichas);
        return fichas;
    }
}


