package edu.fiuba.algo3.tests_unitarios;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.terrenos.Terreno;
import edu.fiuba.algo3.modelo.terrenos.Bosque;
import edu.fiuba.algo3.modelo.terrenos.Desierto;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HexagonoTest {

    @Test
    public void test01HexagonoProduceRecursosCorrectamente() {
        // Arrange
        Jugador jugador = new Jugador(1, "A");
        Terreno bosque = new Bosque(); // produce madera
        Hexagono hexagono = new Hexagono(10, bosque, 8);

        Vertice vertice = new Vertice(1);
        vertice.asignarConstruccion(new Poblado(jugador));
        hexagono.agregarVertice(vertice);

        // Act
        hexagono.producirRecursos(8);

        // Assert
        assertEquals(1, jugador.obtenerCantidadTotalDeRecursos());
    }

    @Test
    public void test02DesiertoNoDaRecursosIniciales() {
        // Arrange
        Jugador jugador = new Jugador(1, "P");
        Hexagono desierto = new Hexagono(4, new Desierto(), 7);

        Vertice vertice = new Vertice(1);
        vertice.asignarConstruccion(new Poblado(jugador));
        desierto.agregarVertice(vertice);

        // Act
        desierto.entregarRecursoInicialA(jugador);

        // Assert
        assertEquals(0, jugador.obtenerCantidadTotalDeRecursos());
    }
}

