package edu.fiuba.algo3.tests_unitarios;

import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VerticeTest {

    @Test
    public void test01VerticePermiteMultiplesVecinos() {
        // Arrange
        Vertice vertice1 = new Vertice();
        Vertice vertice2 = new Vertice();
        Vertice vertice3 = new Vertice();
        Vertice vertice4 = new Vertice();

        // Act y Assert
        assertDoesNotThrow(() -> vertice1.agregarVecino(vertice2));
        assertDoesNotThrow(() -> vertice1.agregarVecino(vertice3));
        assertDoesNotThrow(() -> vertice1.agregarVecino(vertice4));
    }

    @Test
    public void test02PobladoSePuedeAsignarAVertice() {
        // Arrange
        Jugador jugador = new Jugador(1, "Carlos", "verde");
        Vertice vertice = new Vertice();
        Poblado poblado = new Poblado(jugador);

        // Act y Assert
        assertDoesNotThrow(() -> {
            vertice.asignarConstruccion(poblado);
        });
    }

    @Test
    public void test03CiudadSePuedeAsignarAVertice() {
        // Arrange
        Jugador jugador = new Jugador(2, "Maria", "azul");
        Vertice vertice = new Vertice();
        Ciudad ciudad = new Ciudad(jugador);

        // Act y Assert
        assertDoesNotThrow(() -> {
            vertice.asignarConstruccion(ciudad);
        });
    }

    @Test
    public void test04CarreteraNoSePuedeConstruirEnVertice() {
        // Arrange
        Jugador jugador = new Jugador(3, "Juan", "rojo");
        Vertice vertice = new Vertice();

        // Act y Assert
        assertThrows(ConstruccionInvalidaError.class, () -> {
            vertice.validarCarretera(jugador);
        });
    }
}

