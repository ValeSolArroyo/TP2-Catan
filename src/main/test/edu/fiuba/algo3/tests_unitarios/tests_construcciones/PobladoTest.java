package edu.fiuba.algo3.tests_unitarios.tests_construcciones;

import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.excepciones.YaHayPobladoError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PobladoTest {

    @Test
    public void test01PobladoTieneUltimoPropietario() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Poblado poblado = new Poblado(jugador);

        assertDoesNotThrow(() -> poblado.tieneDePropietarioA(jugador));
    }

    @Test
    public void test02PobladoLanzaExcepcionSiNoEsPropietario() {
        Jugador jugador1 = new Jugador(1, "Constructor1", Color.BLUE);
        Jugador jugador2 = new Jugador(2, "Constructor2", Color.RED);
        Poblado poblado = new Poblado(jugador1);

        assertThrows(ConstruccionInvalidaError.class, () -> poblado.tieneDePropietarioA(jugador2));
    }

    @Test
    public void test03PobladoOcuparLanzaExcepcion() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Poblado poblado = new Poblado(jugador);

        assertThrows(YaHayPobladoError.class, poblado::ocupar);
    }

    @Test
    public void test04PobladoDaPuntosDeVictoria() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Poblado poblado = new Poblado(jugador);

        assertEquals(1, poblado.puntosVictoria());
    }

    @Test
    public void test05PobladoProduceRecurso() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Poblado poblado = new Poblado(jugador);

        poblado.producir(new Madera());
        poblado.producir(new Grano());

        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(new Madera(), new Grano())));
    }

    @Test
    public void test06PobladoCosto() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Poblado poblado = new Poblado(jugador);

        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Ladrillo());
        jugador.recibirRecurso(new Lana());
        jugador.recibirRecurso(new Grano());

        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(new Madera(), new Ladrillo(), new Lana(), new Grano())));
    }

    @Test
    public void test07PobladoCobraRecursosAlConstruir() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Poblado poblado = new Poblado(jugador);

        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Ladrillo());
        jugador.recibirRecurso(new Lana());
        jugador.recibirRecurso(new Grano());

        jugador.entregarRecursos(List.of(new Madera(), new Ladrillo(), new Lana(), new Grano()));

        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Madera())));
    }
}
