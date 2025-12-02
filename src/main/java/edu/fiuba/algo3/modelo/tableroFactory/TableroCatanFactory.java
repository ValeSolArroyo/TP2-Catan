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

public class TableroCatanFactory implements TableroFactory {
    private Set<Vertice> verticesBorde;
    private List<Vertice> todosLosVertices;

    @Override
    public Tablero crearTablero() {
        verticesBorde = new HashSet<>();
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
        todosLosVertices = inicializarVertices();

        VerticeCatan verticeCatan = new VerticeCatan(todosLosVertices);
        verticeCatan.asignarVertices(hexagonos);
        conectarAristas(hexagonos);

        verticesBorde = verticeCatan.identificarVerticesBorde();
        asignarPuertos();

        return new Tablero(hexagonos);
    }

    private List<Vertice> inicializarVertices() {
        List<Vertice> vertices = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            Vertice vertice = new Vertice();
            vertices.add(vertice);
        }
        return vertices;
    }

    private List<Arista> conectarAristas(List<Hexagono> hexagonos) {
        List<Arista> aristas = new ArrayList<>();

        for (Hexagono hexagono : hexagonos) {
            List<Vertice> verticesHexagono = new ArrayList<>(hexagono.getVertices());

            for (int i = 0; i < 6; i++) {
                Vertice actual = verticesHexagono.get(i);
                Vertice siguiente = verticesHexagono.get((i + 1) % 6);

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
        return aristas;
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

    public List<Hexagono> crearHexagonos(List<Terreno> terrenos, List<Integer> fichas) {
        List<Hexagono> hexagonos = new ArrayList<>();
        VisitanteTerreno visitante = new VisitanteTerreno(fichas);

        for (Terreno terrenoActual : terrenos) {
            int numeroFicha = terrenoActual.aceptar(visitante);
            Hexagono hexagono = (new Hexagono(terrenoActual, numeroFicha));
            terrenoActual.colocarLadron(hexagono);
            hexagonos.add(hexagono);
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
