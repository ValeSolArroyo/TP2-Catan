package edu.fiuba.algo3.tests_integracion.entrega_1;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.terrenos.Bosque;
import edu.fiuba.algo3.modelo.terrenos.Terreno;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TerrenoLadronTest {

    @Test
    public void test01HexagonoNoProduceSiEstaElLadron() {
        Terreno bosque = new Bosque();
        Hexagono hexagono = new Hexagono(1, bosque, 8);

        hexagono.ponerLadron();

        Vertice vertice = new Vertice(1);
        hexagono.agregarVertice(vertice);

        hexagono.producirRecursos(8);

        assertTrue(vertice.obtenerConstruccion() instanceof NullConstruccion,
                "El hexágono no debería producir recursos cuando el ladrón está presente");
    }

    @Test
    public void test02HexagonoProduceRecursoCorrectoSiElLadronNoEsta() {
        Jugador jugador = new Jugador(1, "A");
        Terreno bosque = new Bosque();
        Hexagono hexagono = new Hexagono(1, bosque, 8);

        Vertice vertice = new Vertice(1);
        vertice.asignarConstruccion(new Poblado(jugador));

        hexagono.agregarVertice(vertice);

        int recursosAntes = jugador.obtenerCantidadTotalDeRecursos();

        hexagono.producirRecursos(8);

        int recursosDespues = jugador.obtenerCantidadTotalDeRecursos();
        assertEquals(1, recursosDespues - recursosAntes,
                "El hexágono debería producir 1 recurso para el poblado");
    }
}