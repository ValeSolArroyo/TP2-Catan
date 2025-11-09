package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Tablero {
    private final List<Vertice> vertices = new ArrayList<>();
    private final List<Arista> aristas = new ArrayList<>();
    private List<Hexagono> hexagonos = new ArrayList<>();

    //     0   1   2
    //   3   4   5   6
    // 7   8   9   10  11
    //   12  13  14  15
    //     16  17  18
    // 19 hexágonos, 6 vértices por hexágono PERO HAY COMPARTIDOS
    // 19 hexágonos, 54 vértices, 72 aristas TOTAL
    // NO se puede construir en los bordes

    //             (0,0)  (1,0)  (2,0)
    //         (-1,1)  (0,1)  (1,1)  (2,1)
    //    (-2,2)  (-1,2)  (0,2)  (1,2)  (2,2)
    //         (-1,3)  (0,3)  (1,3)  (2,3)
    //            (0,4)  (1,4)  (2,4)
    //Acá se lo piensa como una pseudo matriz

    public Tablero(){
        inicializarHexagonos();
        definirAdyacencias();
    }

    public void crearHexagono(int id, String tipoTerreno, Integer numeroFicha) {
        Hexagono hexagono = new Hexagono(id, tipoTerreno, numeroFicha);
        hexagonos.add(hexagono);
    }

    private Hexagono obtenerHexagono(int id) {
        for (Hexagono h : hexagonos) {
            if (h.obtenerId() == id) {
                return h;
            }
        }
        throw new RuntimeException("No existe ese hexágono"); // TODO: poner excepción personalizada
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

    private void agregarVecinos(int baseId, int... vecinos) {
        Hexagono base = obtenerHexagono(baseId);
        for (int idVecino : vecinos) {
            Hexagono vecino = obtenerHexagono(idVecino);
            base.agregarAdyacente(idVecino);
            vecino.agregarAdyacente(baseId);
        }
    }

    private void definirAdyacencias() {
        agregarVecinos(0, 1, 3, 4);
        agregarVecinos(1, 2, 4, 5);
        agregarVecinos(2, 5, 6);
        agregarVecinos(3, 4, 7, 8);
        agregarVecinos(4, 5, 8, 9);
        agregarVecinos(5, 6, 9, 10);
        agregarVecinos(6, 10, 11);
        agregarVecinos(7, 8, 12);
        agregarVecinos(8, 9, 12, 13);
        agregarVecinos(9, 10, 13, 14);
        agregarVecinos(10, 11, 14, 15);
        agregarVecinos(11, 15);
        agregarVecinos(12, 13, 16);
        agregarVecinos(13, 14, 16, 17);
        agregarVecinos(14, 15, 17, 18);
        agregarVecinos(15, 18);
        agregarVecinos(16, 17);
        agregarVecinos(17, 18);
    }

    // Para el test de ahora
    public List<Hexagono> obtenerHexagonos() {
        return hexagonos;
    }

}



