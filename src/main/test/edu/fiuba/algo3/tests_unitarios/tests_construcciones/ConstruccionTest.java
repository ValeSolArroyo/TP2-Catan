package edu.fiuba.algo3.tests_unitarios.tests_construcciones;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConstruccionTest {

    @Test
    public void test01PobladoTieneMayorCostoQueCarretera() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Poblado poblado = new Poblado(jugador);
        Carretera carretera = new Carretera(jugador);

        assertTrue(poblado.puntosVictoria() > carretera.puntosVictoria());
    }

    @Test
    public void test02CiudadTieneMayorCostoQuePoblado() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Ciudad ciudad = new Ciudad(jugador);
        Poblado poblado = new Poblado(jugador);

        assertTrue(ciudad.puntosVictoria() > poblado.puntosVictoria());
    }

    @Test
    public void test03CiudadProduceMasQuePopulado() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Ciudad ciudad = new Ciudad(jugador);
        Poblado poblado = new Poblado(jugador);

        ciudad.producir(new Madera());
        poblado.producir(new Madera());

        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(
                new Madera(), new Madera(), new Madera()
        )));
    }

    @Test
    public void test04DiferentesTiposDeConstruccionesDelMismoJugador() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Poblado poblado = new Poblado(jugador);
        Ciudad ciudad = new Ciudad(jugador);
        Carretera carretera = new Carretera(jugador);

        assertDoesNotThrow(() -> poblado.tieneDePropietarioA(jugador));
        assertDoesNotThrow(() -> ciudad.tieneDePropietarioA(jugador));
        assertDoesNotThrow(() -> carretera.tieneDePropietarioA(jugador));
    }

    @Test
    public void test05PobladoYCiudadProducenRecursos() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Poblado poblado = new Poblado(jugador);
        Ciudad ciudad = new Ciudad(jugador);

        poblado.producir(new Grano());
        ciudad.producir(new Grano());

        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(
                new Grano(), new Grano(), new Grano()
        )));
    }

    @Test
    public void test06ConstruccionesPuedenSerDeLosDiferentesJugadores() {
        Jugador jugador1 = new Jugador(1, "Constructor1", Color.BLUE);
        Jugador jugador2 = new Jugador(2, "Constructor2", Color.RED);

        Poblado poblado1 = new Poblado(jugador1);
        Poblado poblado2 = new Poblado(jugador2);

        assertDoesNotThrow(() -> poblado1.tieneDePropietarioA(jugador1));
        assertDoesNotThrow(() -> poblado2.tieneDePropietarioA(jugador2));
    }

    @Test
    public void test07CarreteraNoProducePeroCobraPrecio() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Carretera carretera = new Carretera(jugador);

        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Ladrillo());

        jugador.entregarRecursos(List.of(new Madera(), new Ladrillo()));

        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Madera())));
    }
}
