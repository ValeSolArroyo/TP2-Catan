package edu.fiuba.algo3.tests_unitarios.tests_tablero;

import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AristaTest {

    @Test
    public void test01AristaConectaCorrectamenteDosVertices() {
        // Arrange
        Vertice vertice1 = new Vertice();
        Vertice vertice2 = new Vertice();
        Arista arista = new Arista(vertice1, vertice2);

        // Act & Assert
        assertTrue(arista.conectaVertices(vertice1, vertice2));
    }

    @Test
    public void test02AristaNoConectaVerticesNoRelacionados() {
        // Arrange
        Vertice vertice1 = new Vertice();
        Vertice vertice2 = new Vertice();
        Vertice vertice3 = new Vertice();
        Arista arista = new Arista(vertice1, vertice2);

        // Act & Assert
        assertFalse(arista.conectaVertices(vertice1, vertice3));
        assertFalse(arista.conectaVertices(vertice2, vertice3));
    }

    @Test
    public void test03AristaConectaAmbosVerticesEnAmbasDirecciones() {
        // Arrange
        Vertice vertice1 = new Vertice();
        Vertice vertice2 = new Vertice();
        Arista arista = new Arista(vertice1, vertice2);

        // Act & Assert
        assertTrue(arista.conectaVertices(vertice1, vertice2));
        assertTrue(arista.conectaVertices(vertice2, vertice1));
    }

    @Test
    public void test04PobladoNoSePuedeConstruirEnArista() {
        // Arrange
        Jugador jugador = new Jugador(1, "Carlos", Color.BLUE);
        Vertice vertice1 = new Vertice();
        Vertice vertice2 = new Vertice();
        Arista arista = new Arista(vertice1, vertice2);
        Poblado poblado = new Poblado(jugador);

        // Act & Assert
        assertThrows(ConstruccionInvalidaError.class, () -> {
            arista.construirPoblado(jugador, poblado);
        });
    }

    @Test
    public void test05CiudadNoSePuedeConstruirEnArista() {
        // Arrange
        Jugador jugador = new Jugador(2, "Maria", Color.BLUE);
        Vertice vertice1 = new Vertice();
        Vertice vertice2 = new Vertice();
        Arista arista = new Arista(vertice1, vertice2);
        Ciudad ciudad = new Ciudad(jugador);

        // Act & Assert
        assertThrows(ConstruccionInvalidaError.class, () -> {
            arista.construirCiudad(jugador, ciudad);
        });
    }

    @Test
    public void test06CarreteraSePuedeConstruirEnAristaConPobladoDelMismoJugador() {
        // Arrange
        Jugador jugador = new Jugador(3, "Juan", Color.RED);
        Vertice vertice1 = new Vertice();
        Vertice vertice2 = new Vertice();
        Poblado poblado = new Poblado(jugador);
        vertice1.construirPobladoPrimerasColocaciones(jugador, poblado);
        Arista arista = new Arista(vertice1, vertice2);
        Carretera carretera = new Carretera(jugador);

        // Act & Assert
        assertDoesNotThrow(() -> {
            arista.construirCarretera(jugador, carretera);
        });
    }
}