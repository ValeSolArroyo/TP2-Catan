package edu.fiuba.algo3.tests_integracion.entrega_1;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VerticeIntegracionTest {

    @Test
    public void test01SePuedeConstruirPobladoEnUnVerticeVacio() {
        Jugador jugador = new Jugador(1, "A");
        Vertice vertice = new Vertice(1);

        vertice.construirPoblado(jugador);

        assertTrue(vertice.esPropiedadDe(jugador),
                "El vértice debería tener un poblado después de construirlo");
    }

    @Test
    public void test02NoSePuedeConstruirEnUnVerticeAdyacente() {
        Jugador jugador = new Jugador(1, "J");
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);

        v1.agregarVecino(v2);
        v2.agregarVecino(v1);
        v1.construirPoblado(jugador);

        assertThrows(ReglaDeDistanciaError.class, () -> {
            v2.construirPoblado(jugador);
        }, "No se puede construir un poblado en un vértice adyacente a otro con construcción");
    }

    @Test
    public void test03SePuedeConstruirPobladoCumpliendoReglaDeDistancia() {
        Jugador jugador = new Jugador(1, "L");
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);

        v1.agregarVecino(v2);
        v2.agregarVecino(v1);
        v2.agregarVecino(v3);
        v3.agregarVecino(v2);
        v1.construirPoblado(jugador);
        v3.construirPoblado(jugador);

        assertTrue(v3.esPropiedadDe(jugador),
                "El vértice debería tener un poblado válido (cumple la regla de distancia)");
    }

    @Test
    public void test04TresVerticesEnLineaRespetanReglaDeDistancia() {
        Jugador jugador = new Jugador(1, "A");
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);

        v1.agregarVecino(v2);
        v2.agregarVecino(v1);
        v2.agregarVecino(v3);
        v3.agregarVecino(v2);
        v3.agregarVecino(v4);
        v4.agregarVecino(v3);

        v1.construirPoblado(jugador);
        v3.construirPoblado(jugador);

        assertThrows(ReglaDeDistanciaError.class, () -> v2.construirPoblado(jugador),
                "No debería permitir construir entre dos poblados adyacentes");
    }
}