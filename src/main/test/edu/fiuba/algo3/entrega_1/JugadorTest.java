package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class JugadorTest {

    @Test
    public void test01MoverLadronDevuelveJugadoresAfectados() {
        Jugador jugador1 = new Jugador(1, "Ana");
        Jugador jugador2 = new Jugador(2, "Pepito");

        // Vertice de la victima
        Vertice vertice = new Vertice(1);
        vertice.construirPoblado(jugador2);

        Hexagono h = new Hexagono(1, "Bosque", 8);
        h.agregarVertice(vertice);

        Tablero tablero = new Tablero() {
            @Override
            public Hexagono obtenerHexagono(int id) {
                return h;
            }
        };

        Juego juego = new Juego(List.of(jugador1, jugador2), tablero);

        List<Jugador> posiblesVictimas = juego.moverLadron(1);

        assertEquals(1, posiblesVictimas.size());
        assertEquals(jugador2, posiblesVictimas.get(0));
    }

    @Test
    public void test02JugadorRobaCartae() {
        Jugador ladrón = new Jugador(1, "Ana");
        Jugador victima = new Jugador(2, "Pepito");

        victima.agregarRecursos("Madera", 3);
        victima.agregarRecursos("Ladrillo", 2);

        int totalAntes = victima.obtenerCantidadTotalDeRecursos();

        // Ana roba a Pepito
        ladrón.robarCarta(victima);

        assertTrue(victima.obtenerCantidadTotalDeRecursos() < totalAntes);
        assertEquals(1, ladrón.obtenerCantidadTotalDeRecursos());
    }
}
