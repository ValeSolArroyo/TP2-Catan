package edu.fiuba.algo3.tests_unitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.terrenos.Terreno;
import edu.fiuba.algo3.modelo.terrenos.Bosque;
import edu.fiuba.algo3.modelo.terrenos.Desierto;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HexagonoTest {

    @Test
    public void test01HexagonoProduceRecursosCorrectamente() {
        Jugador jugador = new Jugador(1, "A");
        Terreno bosque = new Bosque(); // produce madera
        Hexagono hex = new Hexagono(10, bosque, 8);

        Vertice v = new Vertice(1);
        v.asignarConstruccion(new Poblado(jugador));

        hex.agregarVertice(v);
        hex.producirRecursos(8);

        assertEquals(1, jugador.obtenerCantidadTotalDeRecursos());
    }

    @Test
    public void test02DesiertoNoDaRecursosIniciales() {
        Jugador jugador = new Jugador(1, "P");
        Hexagono desierto = new Hexagono(4, new Desierto(), 7);

        Vertice v = new Vertice(1);
        v.asignarConstruccion(new Poblado(jugador));

        desierto.agregarVertice(v);
        desierto.entregarRecursoInicialA(jugador);

        assertEquals(0, jugador.obtenerCantidadTotalDeRecursos());
    }
}

