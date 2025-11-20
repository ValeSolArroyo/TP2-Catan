package edu.fiuba.algo3.tests_integracion.entrega_1;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.modelo.patronTablero.*;
import edu.fiuba.algo3.modelo.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReparticionRecursosTest {

    @Test
    public void test01VerificarJugadorRecibeRecursoCuandoSeLlamaARepartir() {
        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();
        Jugador jugador = new Jugador(1, "A");
        Vertice vertice = tablero.encontrarVertice(10);

        jugador.construirPobladoEn(vertice);

        int recursosAntes = jugador.obtenerCantidadTotalDeRecursos();
        assertEquals(0, recursosAntes, "El jugador debe iniciar con 0 recursos.");


        tablero.darRecursosIniciales(jugador, vertice);
        int recursosDespues = jugador.obtenerCantidadTotalDeRecursos();

        // Assert: Debe haber recibido al menos 1 recurso por cada hexágono adyacente
        assertTrue(recursosDespues >= 1, "El jugador debe recibir recursos de los hexágonos adyacentes.");
    }

}