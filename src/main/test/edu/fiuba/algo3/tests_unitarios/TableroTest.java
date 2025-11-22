package edu.fiuba.algo3.tests_unitarios;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.terrenos.Bosque;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {

    @Test
    public void test01EncuentraVerticePorIdCorrectamente() {
        // Arrange
        Vertice vertice5 = new Vertice(5);

        Map<Integer, Vertice> mapa = new HashMap<>();
        mapa.put(5, vertice5);

        Tablero tablero = new Tablero(new ArrayList<>(), mapa, new HashMap<>(), new HashMap<>());

        // Act & Assert
        assertEquals(vertice5, tablero.encontrarVertice(5));
    }

    @Test
    public void test02ProducirActivaHexagonosCorrectos() {
        // Arrange
        Jugador jugador = new Jugador(7, "X");
        Vertice vertice = new Vertice(1);
        vertice.asignarConstruccion(new Poblado(jugador));

        Hexagono hexagono = new Hexagono(1, new Bosque(), 4);
        hexagono.agregarVertice(vertice);

        List<Hexagono> hexagonos = List.of(hexagono);

        Tablero tablero = new Tablero(hexagonos, new HashMap<>(), new HashMap<>(), new HashMap<>());

        // Act
        tablero.producir(4);

        // Assert
        assertEquals(1, jugador.obtenerCantidadTotalDeRecursos());
    }
}
