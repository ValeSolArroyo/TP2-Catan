package edu.fiuba.algo3.tests_integracion.entrega_1;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tableroFactory.*;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReparticionRecursosTest {

    @Test
    public void test01VerificarJugadorRecibeRecursoCuandoSeLlamaARepartir() {
        // Arrange
        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();
        Jugador jugador = new Jugador(1, "A");

        // Act
        Vertice vertice = tablero.encontrarVertice(10);

        jugador.construirPobladoInicialEn(vertice);

        int recursosAntes = jugador.obtenerCantidadTotalDeRecursos();
        tablero.darRecursosIniciales(jugador, vertice);
        int recursosDespues = jugador.obtenerCantidadTotalDeRecursos();

        // Assert
        assertEquals(0, recursosAntes, "El jugador debe iniciar con 0 recursos.");
        assertTrue(recursosDespues >= 1, "El jugador debe recibir recursos de los hexágonos adyacentes.");
    }

}