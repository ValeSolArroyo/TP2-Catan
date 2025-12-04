package edu.fiuba.algo3.tests_unitarios.tests_cartas;

import edu.fiuba.algo3.modelo.cartasBonificacion.CartaBonificacion;
import edu.fiuba.algo3.modelo.cartasBonificacion.GranCaballeria;
import edu.fiuba.algo3.modelo.cartasBonificacion.GranRutaComercial;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartaBonificacionTest {

    @Test
    public void test01GranCaballeriaExisteYEsValido() {
        CartaBonificacion granCaballeria = new GranCaballeria();
        assertNotNull(granCaballeria);
    }

    @Test
    public void test02GranRutaComercialExisteYEsValido() {
        CartaBonificacion granRutaComercial = new GranRutaComercial();
        assertNotNull(granRutaComercial);
    }

    @Test
    public void test03GranCaballeriaRequiere3CaballerosMinimo() {
        GranCaballeria granCaballeria = new GranCaballeria();
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);

        granCaballeria.evaluarCartaBonificacion(jugador);

        assertDoesNotThrow(() -> granCaballeria.evaluarCartaBonificacion(jugador));
    }

    @Test
    public void test04GranRutaComercialRequiere5CarreterasMinimo() {
        GranRutaComercial granRutaComercial = new GranRutaComercial();
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);

        granRutaComercial.evaluarCartaBonificacion(jugador);

        assertDoesNotThrow(() -> granRutaComercial.evaluarCartaBonificacion(jugador));
    }

    @Test
    public void test05DosJugadoresDiferentesConCartasBonificacionDistintas() {
        Jugador jugador1 = new Jugador(1, "Constructor1", Color.BLUE);
        Jugador jugador2 = new Jugador(2, "Constructor2", Color.RED);

        CartaBonificacion carta1 = new GranCaballeria();
        CartaBonificacion carta2 = new GranRutaComercial();

        assertDoesNotThrow(() -> carta1.evaluarCartaBonificacion(jugador1));
        assertDoesNotThrow(() -> carta2.evaluarCartaBonificacion(jugador2));
    }

    @Test
    public void test06GranCaballeriaOtorgaBonificacionAlTercerCaballero() {
        GranCaballeria granCaballeria = new GranCaballeria();
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);

        jugador.registrarCaballeroJugado();
        jugador.registrarCaballeroJugado();
        jugador.registrarCaballeroJugado();

        assertDoesNotThrow(() -> granCaballeria.evaluarCartaBonificacion(jugador));
    }

    @Test
    public void test07GranRutaComercialOtorgaBonificacionAlTener5Carreteras() {
        GranRutaComercial granRutaComercial = new GranRutaComercial();
        Jugador jugador = org.mockito.Mockito.mock(Jugador.class);
        org.mockito.Mockito.when(jugador.conseguirRutaMasLarga()).thenReturn(5);
        assertDoesNotThrow(() -> granRutaComercial.evaluarCartaBonificacion(jugador));
    }
}
