package edu.fiuba.algo3.tests_integracion.entrega_1;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.terrenos.Bosque;
import edu.fiuba.algo3.modelo.terrenos.Terreno;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TerrenoLadronTest {

    @Test
    public void test01HexagonoNoProduceSiEstaElLadron() {
        // Arrange y Act
        Terreno bosque = new Bosque();
        Hexagono hexagono = new Hexagono(1, bosque, 8);

        hexagono.ponerLadron();

        Vertice vertice = new Vertice(1);
        hexagono.agregarVertice(vertice);

        hexagono.producirRecursos(8);

        // Assert
        assertTrue(vertice.obtenerConstruccion() instanceof NullConstruccion,
                "El hexágono no debería producir recursos cuando el ladrón está presente");
    }

    @Test
    public void test02HexagonoProduceRecursoCorrectoSiElLadronNoEsta() {
        // Arrange
        Jugador jugador = new Jugador(1, "A");
        Terreno bosque = new Bosque();
        Hexagono hexagono = new Hexagono(1, bosque, 8);

        Vertice vertice = new Vertice(1);

        // Act
        vertice.asignarConstruccion(new Poblado(jugador));

        hexagono.agregarVertice(vertice);

        int recursosAntes = jugador.obtenerCantidadTotalDeRecursos();

        hexagono.producirRecursos(8);

        int recursosDespues = jugador.obtenerCantidadTotalDeRecursos();

        // Assert
        assertEquals(1, recursosDespues - recursosAntes,
                "El hexágono debería producir 1 recurso para el poblado");
    }
}