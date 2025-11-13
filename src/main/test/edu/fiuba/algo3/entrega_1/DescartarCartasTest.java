package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class DescartarCartasTest {
    @Test
    public void test01jugadorDescartaMitadAlSacarSiete() {
        Jugador jugador = new Jugador(1, "Pepe");
        for (int i = 0; i < 10; i++) {
            jugador.agregarRecursos("Madera", 1);
        }
        Tablero tablero = new Tablero();
        Juego juego = new Juego(List.of(jugador), tablero);
        juego.verificarDescartesPorLadron();

        assertEquals(5, jugador.obtenerCantidadTotalDeRecursos());
    }

    @Test
    public void test02jugadorNoDescartaSiTiene7oMenos() {
        Jugador jugador = new Jugador(1, "Pepe");
        for (int i = 0; i < 7; i++) {
            jugador.agregarRecursos("Lana", 1);
        }
        Tablero tablero = new Tablero();
        Juego juego = new Juego(List.of(jugador), tablero);
        juego.verificarDescartesPorLadron();

        assertEquals(7, jugador.obtenerCantidadTotalDeRecursos());
    }

}
