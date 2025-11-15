package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.construcciones.Construccion;
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

    private final List<Vertice> vertices;
    private final List<Arista> aristas;
    private List<Hexagono> hexagonos;
    private Ladron ladron;

    // CONSTRUCTORES
    public Tablero() {
        this.vertices = new ArrayList<>();
        this.aristas = new ArrayList<>();
        this.hexagonos = new ArrayList<>();
        this.ladron = new Ladron();
        inicializarHexagonos();
        definirAdyacencias();
    }

    private void crearHexagono(int id, String tipoTerreno, Integer numeroFicha) {
        hexagonos.add(new Hexagono(id, tipoTerreno, numeroFicha));
    }

    public void inicializarHexagonos() {
        List<String> terrenos = new ArrayList<>(List.of(
                "Bosque", "Bosque", "Bosque", "Bosque", // Madera
                "Colina", "Colina", "Colina", // Ladrillo
                "Pastizal", "Pastizal", "Pastizal", "Pastizal", // Lana
                "Campo", "Campo", "Campo", "Campo", // Grano
                "Montaña", "Montaña", "Montaña", // Mineral
                "Desierto" // Nada
        ));

        List<Integer> fichas = new ArrayList<>(List.of(
                2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12
        ));
        Collections.shuffle(terrenos);
        Collections.shuffle(fichas);

        // Creamos los hexagonos y asignamos una ficha a los que NO son Desierto
        int fichaI = 0;
        for (int i = 0; i < terrenos.size(); i++) {
            String tipo = terrenos.get(i);
            Integer numero = tipo.equals("Desierto") ? null : fichas.get(fichaI++);
            crearHexagono(i, tipo, numero);
        }
    }

    private void agregarVecinos(int baseId, List<Integer> vecinos) {
        Hexagono base = obtenerHexagono(baseId);
        for (int idVecino : vecinos) {
            Hexagono vecino = obtenerHexagono(idVecino);
            base.agregarAdyacente(idVecino);
            vecino.agregarAdyacente(baseId);
        }
    }

    private void definirAdyacencias() {
        agregarVecinos(0, List.of(1, 3, 4));
        agregarVecinos(1, List.of(2, 4, 5));
        agregarVecinos(2, List.of(5, 6));
        agregarVecinos(3, List.of(4, 7, 8));
        agregarVecinos(4, List.of(5, 8, 9));
        agregarVecinos(5, List.of(6, 9, 10));
        agregarVecinos(6, List.of(10, 11));
        agregarVecinos(7, List.of(8, 12));
        agregarVecinos(8, List.of(9, 12, 13));
        agregarVecinos(9, List.of(10, 13, 14));
        agregarVecinos(10, List.of(11, 14, 15));
        agregarVecinos(11, List.of(15));
        agregarVecinos(12, List.of(13, 16));
        agregarVecinos(13, List.of(14, 16, 17));
        agregarVecinos(14, List.of(15, 17, 18));
        agregarVecinos(15, List.of(18));
        agregarVecinos(16, List.of(17));
        agregarVecinos(17, List.of(18));
    }

    public Vertice encontrarVertice(int verticeId) {
        for (Vertice vertice : vertices) {
            if (vertice.tieneId(verticeId)) {
                return vertice;
            }
        }
        throw new VerticeInexistenteError("No existe ese vértice");
    }

    public void producirRecursos(int numeroLanzado) {
        for (Hexagono hexagono : hexagonos) {
            Integer numero = hexagono.obtenerNumeroFicha();
            if (numero != null && numero == numeroLanzado && !ladron.estaEn(hexagono)) {
                for (Vertice vertice : hexagono.obtenerVertices()) {
                    Construccion construccion = vertice.obtenerConstruccion();
                    if (construccion != null) {
                        Jugador jugador = construccion.obtenerPropietario();
                        jugador.agregarRecursos(hexagono.obtenerTipoTerreno(), construccion.recursosProducidos());
                    }
                }
            }
        }
    }

    public Hexagono obtenerHexagono(int id) {
        for (Hexagono hexagono : hexagonos) {
            if (hexagono.obtenerId() == id) {
                return hexagono;
            }
        }
        throw new HexagonoInexistenteError("No existe ese hexágono");
    }

    public List<Hexagono> obtenerHexagonos() {
        return hexagonos;
    }
}



