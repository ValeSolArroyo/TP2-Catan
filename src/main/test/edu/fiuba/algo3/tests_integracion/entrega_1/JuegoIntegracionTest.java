package edu.fiuba.algo3.tests_integracion.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Hexagono;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoIntegracionTest {

    @Test
    public void test01DaUnRecursoPorCadaHexagonoAdyacente() {
        // Arrange
        Jugador jugador = new Jugador(1, "Pepe");
        Hexagono bosque = new Hexagono(1, "Bosque", 5);
        Hexagono campo = new Hexagono(2, "Campo", 8);
        Vertice vertice = new Vertice(1);

        // Act
        vertice.agregarHexagono(bosque);
        vertice.agregarHexagono(campo);

        Juego juego = new Juego(List.of(jugador), new Tablero());
        juego.darRecursosIniciales(jugador, vertice);

        // Assert
        assertEquals(1, jugador.obtenerCantidadDeRecurso("Bosque"));
        assertEquals(1, jugador.obtenerCantidadDeRecurso("Campo"));
    }

    @Test
    public void test02JugadorDescartaMitadAlSacarSiete() {
        // Arrange
        Jugador jugador = new Jugador(1, "Pepe");

        // Act
        for (int i = 0; i < 10; i++) {
            jugador.agregarRecursos("Madera", 1);
        }

        Juego juego = new Juego(List.of(jugador), new Tablero());
        juego.verificarDescartesPorLadron();

        // Assert
        assertEquals(5, jugador.obtenerCantidadTotalDeRecursos());
    }

    @Test
    public void test03JugadorNoDescartaSiTiene7oMenos() {
        // Arrange
        Jugador jugador = new Jugador(1, "Pepe");

        // Act
        for (int i = 0; i < 7; i++) {
            jugador.agregarRecursos("Lana", 1);
        }

        Juego juego = new Juego(List.of(jugador), new Tablero());
        juego.verificarDescartesPorLadron();

        // Assert
        assertEquals(7, jugador.obtenerCantidadTotalDeRecursos());
    }

    @Test
    public void test04LanzamientoGeneraNumeroEntre2y12() {
        // Arrange
        Jugador jugador1 = new Jugador(1, "Juanito");
        Jugador jugador2 = new Jugador(2, "Pepe");
        ArrayList<Jugador> jugadores = new ArrayList<>();

        // Act
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        Juego juego = new Juego(jugadores, new Tablero());
        int resultado = juego.lanzarDados();

        // Assert
        assertTrue(resultado >= 2 && resultado <= 12,
                "El lanzamiento debe generar un número entre 2 y 12, pero salió " + resultado);
    }

    @Test
    public void test05MoverLadronDevuelveJugadoresAfectados() {
        // Arrange
        Jugador jugador1 = new Jugador(1, "Ana");
        Jugador jugador2 = new Jugador(2, "Pepito");

        Vertice vertice = new Vertice(1);
        Hexagono hexagono = new Hexagono(1, "Bosque", 8);

        // Act
        vertice.construirPoblado(jugador2);
        hexagono.agregarVertice(vertice);

        Tablero tablero = new Tablero() {
            @Override
            public Hexagono obtenerHexagono(int id) {
                return hexagono;
            }
        };

        Juego juego = new Juego(List.of(jugador1, jugador2), tablero);

        List<Jugador> posiblesVictimas = juego.moverLadron(1);

        // Assert
        assertEquals(1, posiblesVictimas.size());
        assertEquals(jugador2, posiblesVictimas.get(0));
    }

    @Test
    public void test06JugadorRobaCartas() {
        // Arrange
        Jugador ladron = new Jugador(1, "Ana");
        Jugador victima = new Jugador(2, "Pepito");

        // Act
        victima.agregarRecursos("Madera", 3);
        victima.agregarRecursos("Ladrillo", 2);

        int totalAntes = victima.obtenerCantidadTotalDeRecursos();

        // Ana roba a Pepito
        ladron.robarCarta(victima);

        // Assert
        assertTrue(victima.obtenerCantidadTotalDeRecursos() < totalAntes);
        assertEquals(1, ladron.obtenerCantidadTotalDeRecursos());
    }
}