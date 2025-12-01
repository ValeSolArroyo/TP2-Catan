package edu.fiuba.algo3.tests_unitarios;

import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.terrenos.*;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tableroFactory.TableroCatanFactory;
import edu.fiuba.algo3.modelo.recursos.Madera;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {

    @Test
    public void test01GenerarTerrenosAleatoriasDevuelve19Terrenos() {
        // Arrange
        TableroCatanFactory factory = new TableroCatanFactory();

        // Act
        List<Terreno> terrenos = factory.generarTerrenosAleatorios();

        // Assert
        assertEquals(19, terrenos.size());
    }

    @Test
    public void test02GenerarFichasAleatoriasDevuelve18Fichas() {
        // Arrange
        TableroCatanFactory factory = new TableroCatanFactory();

        // Act
        List<Integer> fichas = factory.generarFichasAleatorias();

        // Assert
        assertEquals(18, fichas.size());
    }

    @Test
    public void test03ProducirActivaHexagonoConFichaValida() {
        // Arrange
        Jugador jugador = new Jugador(1, "Carlos", "verde");
        Vertice vertice = new Vertice();
        Poblado poblado = new Poblado(jugador);
        vertice.construirPoblado(jugador, poblado);

        Hexagono hexagono = new Hexagono(new Bosque(), 4);
        hexagono.agregarVertice(vertice);

        Tablero tablero = new Tablero(List.of(hexagono));

        // Act
        tablero.producir(4);

        Madera madera = new Madera();
        jugador.recibirRecurso(madera);

        // Assert
        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(madera)));
    }

    @Test
    public void test04ProducirNoActivaHexagonoConFichaInvalida() {
        // Arrange
        Jugador jugador = new Jugador(2, "Maria", "azul");
        Vertice vertice = new Vertice();
        Poblado poblado = new Poblado(jugador);
        vertice.construirPoblado(jugador, poblado);

        Hexagono hexagono = new Hexagono(new Colina(), 5);
        hexagono.agregarVertice(vertice);

        Tablero tablero = new Tablero(List.of(hexagono));

        // Act
        tablero.producir(4);

        Madera madera = new Madera();

        // Assert
        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(madera)));
    }

    @Test
    public void test05HexagonoConLadronNoProduceRecursos() {
        // Arrange
        Jugador jugador = new Jugador(3, "Juan", "rojo");
        Vertice vertice = new Vertice();
        vertice.construirPoblado(jugador, new Poblado(jugador));

        Hexagono hexagono = new Hexagono(new Pastizal(), 6);
        hexagono.agregarVertice(vertice);
        hexagono.ponerLadron();

        Tablero tablero = new Tablero(List.of(hexagono));

        // Act
        tablero.producir(6);

        Madera madera = new Madera();

        // Assert
        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(madera)));
    }

    @Test
    public void test06HexagonoSinLadronProduceRecursos() {
        // Arrange
        Jugador jugador = new Jugador(4, "Pedro", "amarillo");
        Vertice vertice = new Vertice();
        vertice.construirPoblado(jugador, new Poblado(jugador));

        Hexagono hexagono = new Hexagono(new Campo(), 8);
        hexagono.agregarVertice(vertice);
        hexagono.quitarLadron();

        Tablero tablero = new Tablero(List.of(hexagono));

        // Act
        tablero.producir(8);

        Madera madera = new Madera();
        jugador.recibirRecurso(madera);

        // Assert
        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(madera)));
    }

    @Test
    public void test07MultiplesHexagonosProducenRecursos() {
        // Arrange
        Jugador jugador = new Jugador(6, "Luis", "naranja");

        Vertice vertice1 = new Vertice();
        vertice1.construirPoblado(jugador, new Poblado(jugador));
        Hexagono hexagono1 = new Hexagono(new Bosque(), 6);
        hexagono1.agregarVertice(vertice1);

        Vertice vertice2 = new Vertice();
        vertice2.construirPoblado(jugador, new Poblado(jugador));
        Hexagono hexagono2 = new Hexagono(new Colina(), 6);
        hexagono2.agregarVertice(vertice2);

        Tablero tablero = new Tablero(List.of(hexagono1, hexagono2));

        // Act
        tablero.producir(6);

        Madera madera1 = new Madera();
        Madera madera2 = new Madera();
        jugador.recibirRecurso(madera1);
        jugador.recibirRecurso(madera2);

        // Assert
        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(madera1, madera2)));
    }

    @Test
    public void test08DarRecursosInicialesAVertice() {
        // Arrange
        Jugador jugador = new Jugador(7, "Sofia", "verde_oscuro");
        Vertice vertice = new Vertice();
        vertice.construirPoblado(jugador, new Poblado(jugador));

        Hexagono hexagono = new Hexagono(new Campo(), 5);
        hexagono.agregarVertice(vertice);

        Tablero tablero = new Tablero(List.of(hexagono));

        // Act
        tablero.darRecursosIniciales(vertice);

        Madera madera = new Madera();
        jugador.recibirRecurso(madera);

        // Assert
        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(madera)));
    }

    @Test
    public void test09DesiertoNoProduceRecursosAunConFichaValida() {
        // Arrange
        Jugador jugador = new Jugador(8, "Diego", "marron");
        Vertice vertice = new Vertice();
        vertice.construirPoblado(jugador, new Poblado(jugador));

        Hexagono hexagono = new Hexagono(new Desierto(), 7);
        hexagono.agregarVertice(vertice);

        Tablero tablero = new Tablero(List.of(hexagono));

        // Act
        tablero.producir(7);

        Madera madera = new Madera();

        // Assert
        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(madera)));
    }
}