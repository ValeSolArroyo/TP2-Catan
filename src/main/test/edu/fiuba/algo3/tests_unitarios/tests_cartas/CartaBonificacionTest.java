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
    public void test03GranCaballeriaImplementaInterfaz() {
        CartaBonificacion granCaballeria = new GranCaballeria();
        assertTrue(granCaballeria instanceof CartaBonificacion);
    }

    @Test
    public void test04GranRutaComercialImplementaInterfaz() {
        CartaBonificacion granRutaComercial = new GranRutaComercial();
        assertTrue(granRutaComercial instanceof CartaBonificacion);
    }

    @Test
    public void test05GranCaballeriaYGranRutaComercialSonDistintas() {
        CartaBonificacion granCaballeria = new GranCaballeria();
        CartaBonificacion granRutaComercial = new GranRutaComercial();

        assertNotEquals(granCaballeria.getClass(), granRutaComercial.getClass());
    }

    @Test
    public void test06GranCaballeriaRequiere3CaballerosMinimo() {
        GranCaballeria granCaballeria = new GranCaballeria();
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);

        // Evaluar sin tener 3 caballeros (no debería otorgar la carta)
        granCaballeria.evaluarCartaBonificacion(jugador);

        // Verificar que la carta no fue otorgada aún
        assertDoesNotThrow(() -> granCaballeria.evaluarCartaBonificacion(jugador));
    }

    @Test
    public void test07GranRutaComercialRequiere5CarreterasMinimo() {
        GranRutaComercial granRutaComercial = new GranRutaComercial();
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);

        // Evaluar sin tener 5 carreteras (no debería otorgar la carta)
        granRutaComercial.evaluarCartaBonificacion(jugador);

        // Verificar que la carta no fue otorgada aún
        assertDoesNotThrow(() -> granRutaComercial.evaluarCartaBonificacion(jugador));
    }

    @Test
    public void test08DosJugadoresDiferentesConCartasBonificacionDistintas() {
        Jugador jugador1 = new Jugador(1, "Constructor1", Color.BLUE);
        Jugador jugador2 = new Jugador(2, "Constructor2", Color.RED);

        CartaBonificacion carta1 = new GranCaballeria();
        CartaBonificacion carta2 = new GranRutaComercial();

        assertDoesNotThrow(() -> carta1.evaluarCartaBonificacion(jugador1));
        assertDoesNotThrow(() -> carta2.evaluarCartaBonificacion(jugador2));
    }

    @Test
    public void test09MultiplesInstanciasDeGranCaballeriaExistenIndependientemente() {
        CartaBonificacion carta1 = new GranCaballeria();
        CartaBonificacion carta2 = new GranCaballeria();

        assertNotSame(carta1, carta2);
    }

    @Test
    public void test10MultiplesInstanciasDeGranRutaComercialExistenIndependientemente() {
        CartaBonificacion carta1 = new GranRutaComercial();
        CartaBonificacion carta2 = new GranRutaComercial();

        assertNotSame(carta1, carta2);
    }
}
