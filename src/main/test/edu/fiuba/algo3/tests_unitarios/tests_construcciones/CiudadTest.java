package edu.fiuba.algo3.tests_unitarios.tests_construcciones;

import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.excepciones.YaHayCiudadError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CiudadTest {

    @Test
    public void test01CiudadTieneUltimoPropietario() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Ciudad ciudad = new Ciudad(jugador);

        assertDoesNotThrow(() -> ciudad.tieneDePropietarioA(jugador));
    }

    @Test
    public void test02CiudadLanzaExcepcionSiNoEsPropietario() {
        Jugador jugador1 = new Jugador(1, "Constructor1", Color.BLUE);
        Jugador jugador2 = new Jugador(2, "Constructor2", Color.RED);
        Ciudad ciudad = new Ciudad(jugador1);

        assertThrows(ConstruccionInvalidaError.class, () -> ciudad.tieneDePropietarioA(jugador2));
    }

    @Test
    public void test03CiudadOcuparLanzaExcepcion() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Ciudad ciudad = new Ciudad(jugador);

        assertThrows(YaHayCiudadError.class, ciudad::ocupar);
    }

    @Test
    public void test04CiudadProduceDobleRecurso() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Ciudad ciudad = new Ciudad(jugador);

        ciudad.producir(new Madera());

        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(new Madera(), new Madera())));
    }

    @Test
    public void test05CiudadCosto() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Ciudad ciudad = new Ciudad(jugador);

        for (int i = 0; i < 2; i++) {
            jugador.recibirRecurso(new Grano());
        }
        for (int i = 0; i < 3; i++) {
            jugador.recibirRecurso(new Mineral());
        }

        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(new Grano(), new Grano(), new Mineral(), new Mineral(), new Mineral())));
    }

    @Test
    public void test06CiudadCobraRecursosAlConstruir() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Ciudad ciudad = new Ciudad(jugador);

        for (int i = 0; i < 2; i++) {
            jugador.recibirRecurso(new Grano());
        }
        for (int i = 0; i < 3; i++) {
            jugador.recibirRecurso(new Mineral());
        }

        jugador.entregarRecursos(List.of(new Grano(), new Grano(), new Mineral(), new Mineral(), new Mineral()));

        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Grano())));
    }

    @Test
    public void test07CiudadProduceMultiplesRecursosDiferentes() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Ciudad ciudad = new Ciudad(jugador);

        ciudad.producir(new Madera());
        ciudad.producir(new Grano());
        ciudad.producir(new Mineral());

        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(
                new Madera(), new Madera(),
                new Grano(), new Grano(),
                new Mineral(), new Mineral()
        )));
    }
}
