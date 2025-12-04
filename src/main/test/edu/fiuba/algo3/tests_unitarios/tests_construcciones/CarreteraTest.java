package edu.fiuba.algo3.tests_unitarios.tests_construcciones;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.excepciones.YaHayCarreteraError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarreteraTest {

    @Test
    public void test01CarreteraTieneUltimoPropietario() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Carretera carretera = new Carretera(jugador);

        assertDoesNotThrow(() -> carretera.tieneDePropietarioA(jugador));
    }

    @Test
    public void test02CarreteraLanzaExcepcionSiNoEsPropietario() {
        Jugador jugador1 = new Jugador(1, "Constructor1", Color.BLUE);
        Jugador jugador2 = new Jugador(2, "Constructor2", Color.RED);
        Carretera carretera = new Carretera(jugador1);

        assertThrows(ConstruccionInvalidaError.class, () -> carretera.tieneDePropietarioA(jugador2));
    }

    @Test
    public void test03CarreteraOcuparLanzaExcepcion() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Carretera carretera = new Carretera(jugador);

        assertThrows(YaHayCarreteraError.class, carretera::ocupar);
    }

    @Test
    public void test04CarreteraNoDaPuntosDeVictoria() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Carretera carretera = new Carretera(jugador);

        assertEquals(0, carretera.puntosVictoria());
    }

    @Test
    public void test05CarreteraNoProduceRecurso() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Carretera carretera = new Carretera(jugador);

        carretera.producir(new Madera());

        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Madera())));
    }

    @Test
    public void test06CarreteraCosto() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Carretera carretera = new Carretera(jugador);

        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Ladrillo());

        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(new Madera(), new Ladrillo())));
    }

    @Test
    public void test07CarreteraCobraRecursosAlConstruir() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Carretera carretera = new Carretera(jugador);

        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Ladrillo());

        jugador.entregarRecursos(List.of(new Madera(), new Ladrillo()));

        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Madera())));
    }

    @Test
    public void test08MultiplesCarreterasDelMismoJugador() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Carretera carretera1 = new Carretera(jugador);
        Carretera carretera2 = new Carretera(jugador);

        assertDoesNotThrow(() -> carretera1.tieneDePropietarioA(jugador));
        assertDoesNotThrow(() -> carretera2.tieneDePropietarioA(jugador));
    }
}
