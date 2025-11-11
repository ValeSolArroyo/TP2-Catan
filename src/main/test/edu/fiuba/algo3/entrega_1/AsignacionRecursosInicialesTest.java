package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class AsignacionRecursosInicialesTest {
    @Test
    public void test01daUnRecursoPorCadaHexagonoAdyacente() {
        Jugador jugador = new Jugador(1, "Pepe");

        Hexagono bosque = new Hexagono(1, "Bosque", 5);
        Hexagono campo = new Hexagono(2, "Campo", 8);

        Vertice vertice = new Vertice(1);
        vertice.agregarHexagono(bosque);
        vertice.agregarHexagono(campo);

        Juego juego = new Juego(List.of(jugador), new Tablero());

        juego.darRecursosIniciales(jugador, vertice);

        assertAll(
                () -> assertEquals(1, jugador.obtenerCantidadDeRecurso("Bosque")),
                () -> assertEquals(1, jugador.obtenerCantidadDeRecurso("Campo"))
        );
    }
}
