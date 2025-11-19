package edu.fiuba.algo3.tests_integracion.entrega_1;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConstruccionIntegacionTest {

    @Test
    public void sePuedeConstruirPobladoEnUnVerticeVacio() {
        Jugador jugador = new Jugador(1, "Pepito");
        Vertice vertice = new Vertice();

        vertice.construirPoblado(jugador);

        assertDoesNotThrow(() -> vertice.construirPoblado(jugador)); // ya que no hay vecinos
    }

    @Test
    public void noSePuedeConstruirEnVerticeAdyacente() {
        Jugador jugador = new Jugador(1, "Juan");
        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();

        v1.agregarVecino(v2);
        v2.agregarVecino(v1);

        v1.construirPoblado(jugador);

        assertThrows(ReglaDeDistanciaError.class, () -> v2.construirPoblado(jugador));
    }

    @Test
    public void sePuedeConstruirPobladoRespetandoDistancia() {
        Jugador jugador = new Jugador(1, "Lucas");
        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();

        v1.agregarVecino(v2);
        v2.agregarVecino(v1);
        v2.agregarVecino(v3);
        v3.agregarVecino(v2);

        v1.construirPoblado(jugador);
        v3.construirPoblado(jugador);

        assertDoesNotThrow(() -> v3.construirPoblado(jugador));
    }
}
