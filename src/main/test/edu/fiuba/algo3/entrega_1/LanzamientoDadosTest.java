package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.Dado;
import edu.fiuba.algo3.modelo.Juego;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LanzamientoDadosTest {
    @Test
    public void lanzamientoGeneraNumeroEntre2y12() {
        Jugador jugador1 = new Jugador(1,"Juanito");
        Jugador jugador2 = new Jugador(2, "Pepe");
        Tablero tablero = new Tablero();
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        Juego juego = new Juego(jugadores, tablero);

        for (int i = 0; i < 100; i++) {
            int resultado = juego.lanzarDados();
            assertTrue(resultado >= 2 && resultado <= 12,
                    "El lanzamiento debe generar un número entre 2 y 12, pero salió " + resultado);
        }
    }
}
