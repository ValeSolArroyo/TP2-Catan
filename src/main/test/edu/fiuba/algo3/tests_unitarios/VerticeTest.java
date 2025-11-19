package edu.fiuba.algo3.tests_unitarios;

import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VerticeTest {

    @Test
    public void test01NoPermiteConstruirPobladoSiHayVecinoOcupado() {
        Jugador jugador = new Jugador(1, "A");
        Jugador otro = new Jugador(2, "B");

        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);

        v1.agregarVecino(v2);

        v2.asignarConstruccion(new Poblado(otro));

        assertThrows(ReglaDeDistanciaError.class, () -> v1.construirPoblado(jugador));
    }

    @Test
    public void test02PermiteConstruirPobladoSiEstaLibre() {
        Jugador jugador = new Jugador(1, "A");
        Vertice v = new Vertice(1);

        v.construirPoblado(jugador);

        assertTrue(v.obtenerConstruccion() instanceof Poblado);
    }
}
