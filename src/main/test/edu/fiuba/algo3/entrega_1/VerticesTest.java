package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VerticesTest {

    @Test
    public void test01sePuedeConstruirPobladoEnUnVerticeVacio() {
        // Arrange
        Jugador jugador = new Jugador(1, "Pepito");
        Vertice vertice = new Vertice(1);

        // Act
        vertice.construirPoblado(jugador);

        // Assert
        assertTrue(vertice.yaTieneConstruccion(),
                "El vértice debería tener un poblado después de construirlo");
    }

    @Test
    public void test02noSePuedeConstruirEnUnVerticeAdyacente() {
        // Arrange
        Jugador jugador = new Jugador(1, "Juan");
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);

        // Act
            // v1 <--> v2
        v1.agregarVerticeAdyacente(v2);
        v2.agregarVerticeAdyacente(v1);

        v1.construirPoblado(jugador);

        // Assert
        assertThrows(ReglaDeDistanciaError.class, () -> {
            v2.construirPoblado(jugador);
        }, "No se puede construir un poblado en un vértice adyacente a otro con construcción");
    }

    @Test
    public void test03sePuedeConstruirPobladoCumpliendoReglaDeDistancia() {
        // Arrange
        Jugador jugador = new Jugador(1, "Lucas");
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);

        // Act
            // v1 <--> v2 <--> v3
        v1.agregarVerticeAdyacente(v2);
        v2.agregarVerticeAdyacente(v1);
        v2.agregarVerticeAdyacente(v3);
        v3.agregarVerticeAdyacente(v2);

        v1.construirPoblado(jugador);
        v3.construirPoblado(jugador);

        // Assert
        assertTrue(v3.yaTieneConstruccion(),
                "El vértice 3 debería tener un poblado válido (cumple la regla de distancia)");
    }
}
