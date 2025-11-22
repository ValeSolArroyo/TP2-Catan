package edu.fiuba.algo3.tests_integracion.entrega_1;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.juego.Juego;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LanzamientoDadosTest {

    @Test
    public void test01NoSePuedeLanzarDadosEnEstadoDeColocacionInicial() {
        // Arrange y Act
        Jugador jugador1 = new Jugador(1, "Juanito");
        Jugador jugador2 = new Jugador(2, "Pepe");
        Tablero tablero = new Tablero(new ArrayList<>(), new HashMap<>(), new HashMap<>(), new HashMap<>());
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        Juego juego = new Juego(jugadores, tablero);

        // Assert
        assertThrows(IllegalStateException.class, () -> juego.lanzarDados(),
                "No se debería poder lanzar dados durante la fase de colocación inicial");
    }
}