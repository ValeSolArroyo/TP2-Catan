package edu.fiuba.algo3.tests_unitarios.tests_tablero;

import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.terrenos.Bosque;
import edu.fiuba.algo3.modelo.terrenos.Campo;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Madera;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HexagonoTest {

    @Test
    public void test01EntregarRecursoInicialAVerticeEnHexagono() {
        // Arrange
        Jugador jugador = new Jugador(4, "Pedro", "amarillo");
        Vertice vertice = new Vertice();
        Poblado poblado = new Poblado(jugador);
        vertice.construirPoblado(jugador, poblado);

        Hexagono hexagono = new Hexagono(new Campo(), 5);
        hexagono.agregarVertice(vertice);

        // Act & Assert
        assertDoesNotThrow(() -> hexagono.entregarRecursoInicialA(vertice));
    }

    @Test
    public void test02ProducirSinLadronNoBloqueaProduccion() {
        // Arrange
        Jugador jugador = new Jugador(3, "Juan", "rojo");
        Vertice vertice = new Vertice();
        vertice.construirPoblado(jugador, new Poblado(jugador));

        Hexagono hexagono = new Hexagono(new Bosque(), 6);
        hexagono.agregarVertice(vertice);

        // Act
        hexagono.producirRecursos(6);
        Madera madera = new Madera();

        // Assert
        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(madera)));
    }

    @Test
    public void test03ProducirConLadronBloquearProduccion() {
        // Arrange
        Jugador jugador = new Jugador(4, "Pedro", "amarillo");
        Vertice vertice = new Vertice();
        vertice.construirPoblado(jugador, new Poblado(jugador));

        Hexagono hexagono = new Hexagono(new Bosque(), 6);
        hexagono.agregarVertice(vertice);
        hexagono.ponerLadron();

        // Act
        hexagono.producirRecursos(6);
        Madera madera = new Madera();

        // Assert
        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(madera)));
    }

    @Test
    public void test04QuitarLadronReactivaProduccion() {
        // Arrange
        Jugador jugador = new Jugador(5, "Ana", "blanco");
        Vertice vertice = new Vertice();
        vertice.construirPoblado(jugador, new Poblado(jugador));

        Hexagono hexagono = new Hexagono(new Bosque(), 8);
        hexagono.agregarVertice(vertice);
        hexagono.ponerLadron();
        hexagono.quitarLadron();

        // Act
        hexagono.producirRecursos(8);
        Madera madera = new Madera();

        // Assert
        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(madera)));
    }
}