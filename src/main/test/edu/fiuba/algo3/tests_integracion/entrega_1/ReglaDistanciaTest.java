package edu.fiuba.algo3.tests_integracion.entrega_1;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReglaDistanciaTest {

    @Test
    public void test01SePuedeConstruirPobladoEnUnVerticeVacio() {
        // Arrange
        Jugador jugador = new Jugador(1, "A");
        Vertice vertice = new Vertice(1);

        // Act
        vertice.construirPoblado(jugador);

        // Assert
        assertTrue(vertice.esPropiedadDe(jugador),
                "El vértice debería tener un poblado después de construirlo");
    }

    @Test
    public void test02NoSePuedeConstruirEnUnVerticeAdyacente() {
        // Arrange
        Jugador jugador = new Jugador(1, "J");
        Vertice vertice1 = new Vertice(1);
        Vertice vertice2 = new Vertice(2);

        // Act
        vertice1.agregarVecino(vertice2);
        vertice2.agregarVecino(vertice1);
        vertice1.construirPoblado(jugador);

        // Assert
        assertThrows(ReglaDeDistanciaError.class, () -> {
            vertice2.construirPoblado(jugador);
        }, "No se puede construir un poblado en un vértice adyacente a otro con construcción");
    }

    @Test
    public void test03SePuedeConstruirPobladoCumpliendoReglaDeDistancia() {
        // Arrange
        Jugador jugador = new Jugador(1, "L");
        Vertice vertice1 = new Vertice(1);
        Vertice vertice2 = new Vertice(2);
        Vertice vertice3 = new Vertice(3);

        // Act
        vertice1.agregarVecino(vertice2);
        vertice2.agregarVecino(vertice1);
        vertice2.agregarVecino(vertice3);
        vertice3.agregarVecino(vertice2);
        vertice1.construirPoblado(jugador);
        vertice3.construirPoblado(jugador);

        // Assert
        assertTrue(vertice3.esPropiedadDe(jugador),
                "El vértice debería tener un poblado válido (cumple la regla de distancia)");
    }

    @Test
    public void test04TresVerticesEnLineaRespetanReglaDeDistancia() {
        // Arrange
        Jugador jugador = new Jugador(1, "A");
        Vertice vertice1 = new Vertice(1);
        Vertice vertice2 = new Vertice(2);
        Vertice vertice3 = new Vertice(3);
        Vertice vertice4 = new Vertice(4);

        // Act
        vertice1.agregarVecino(vertice2);
        vertice2.agregarVecino(vertice1);
        vertice2.agregarVecino(vertice3);
        vertice3.agregarVecino(vertice2);
        vertice3.agregarVecino(vertice4);
        vertice4.agregarVecino(vertice3);

        vertice1.construirPoblado(jugador);
        vertice3.construirPoblado(jugador);

        // Assert
        assertThrows(ReglaDeDistanciaError.class, () -> vertice2.construirPoblado(jugador),
                "No debería permitir construir entre dos poblados adyacentes");
    }
}