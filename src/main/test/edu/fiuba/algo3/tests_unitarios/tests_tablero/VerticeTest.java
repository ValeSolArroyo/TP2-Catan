package edu.fiuba.algo3.tests_unitarios.tests_tablero;

import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import javafx.scene.paint.Color;
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

        // Act & Assert
        assertDoesNotThrow(() -> vertice1.agregarVecino(vertice2));
        assertDoesNotThrow(() -> vertice1.agregarVecino(vertice3));
        assertDoesNotThrow(() -> vertice1.agregarVecino(vertice4));
    }

    @Test
    public void test02PobladoSePuedeConstruirEnVertice() {
        // Arrange
        Jugador jugador = new Jugador(1, "Carlos", Color.BLUE);
        Vertice vertice = new Vertice();
        Poblado poblado = new Poblado(jugador);

        // Act & Assert
        assertDoesNotThrow(() -> vertice.construirPobladoPrimerasColocaciones(jugador, (Poblado) poblado));
    }

    @Test
    public void test03CiudadSePuedeConstruirEnVertice() {
        // Arrange
        Jugador jugador = new Jugador(2, "Maria", Color.BLUE);
        Vertice vertice = new Vertice();
        Ciudad ciudad = new Ciudad(jugador);
        vertice.construirPobladoPrimerasColocaciones(jugador, new Poblado(jugador));

        // Act & Assert
        assertDoesNotThrow(() -> vertice.construirCiudad(jugador, ciudad));
    }

    @Test
    public void test04CarreteraNoSePuedeConstruirEnVertice() {
        // Arrange
        Jugador jugador = new Jugador(3, "Juan", Color.BLUE);
        Vertice vertice = new Vertice();
        Carretera carretera = new Carretera(jugador);

        // Act & Assert
        assertThrows(ConstruccionInvalidaError.class, () ->
                vertice.construirCarretera(jugador, carretera)
        );
    }
}

