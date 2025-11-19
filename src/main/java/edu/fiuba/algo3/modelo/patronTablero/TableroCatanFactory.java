package edu.fiuba.algo3.modelo.patronTablero;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.terrenos.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableroCatanFactory implements TableroFactory {
    public Tablero crearTableroAleatorio() {
        List<Terreno> terrenos = generarTerrenosAleatorios();
        List<Integer> fichas = generarFichasAleatorias();
        return crearTablero(terrenos, fichas);
    }

    @Override
    public Tablero crearTablero(List<Terreno> terrenos, List<Integer> fichas) {
        if (terrenos.size() != 19) {
            throw new IllegalArgumentException("Se necesitan exactamente 19 terrenos");
        }
        if (fichas.size() != 18) {
            throw new IllegalArgumentException("Se necesitan exactamente 18 fichas");
        }

        List<Hexagono> hexagonos = crearHexagonos(terrenos, fichas);
        return new Tablero(hexagonos);
    }

    private List<Hexagono> crearHexagonos(List<Terreno> terrenos, List<Integer> fichas) {
        List<Hexagono> hexagonos = new ArrayList<>();
        int fichaIndex = 0;

        for (Terreno terreno : terrenos) {
            int numeroFicha;
            if (terreno instanceof Desierto) {
                numeroFicha = 0;
            } else {
                numeroFicha = fichas.get(fichaIndex++);
            }
            hexagonos.add(new Hexagono(terreno, numeroFicha));
        }

        return hexagonos;
    }

    public static List<Terreno> generarTerrenosAleatorios() {
        List<Terreno> terrenos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            terrenos.add(new Bosque());
        }
        for (int i = 0; i < 3; i++) {
            terrenos.add(new Colina());
        }
        for (int i = 0; i < 4; i++) {
            terrenos.add(new Pastizal());
        }
        for (int i = 0; i < 4; i++) {
            terrenos.add(new Campo());
        }
        for (int i = 0; i < 3; i++) {
            terrenos.add(new Montaña());
        }
        terrenos.add(new Desierto());

        Collections.shuffle(terrenos);
        return terrenos;
    }

    public static List<Integer> generarFichasAleatorias() {
        List<Integer> fichas = new ArrayList<>(List.of(
                2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12
        ));
        Collections.shuffle(fichas);
        return fichas;
    }
}
