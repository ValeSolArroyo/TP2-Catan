package edu.fiuba.algo3.tests_unitarios.tests_cartas;

import edu.fiuba.algo3.modelo.cartasBonificacion.CartaBonificacion;
import edu.fiuba.algo3.modelo.cartasBonificacion.GranCaballeria;
import edu.fiuba.algo3.modelo.cartasBonificacion.GranRutaComercial;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        Tablero tablero = mock(Tablero.class);

        assertDoesNotThrow(() -> granCaballeria.evaluarCartaBonificacion(jugador, tablero));
    }

    @Test
    public void test04GranRutaComercialRequiere5CarreterasMinimo() {
        GranRutaComercial granRutaComercial = new GranRutaComercial();
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);

        Tablero tableroMock = mock(Tablero.class);
        when(tableroMock.conseguirRutaMasLarga(jugador)).thenReturn(5);

        assertDoesNotThrow(() -> granRutaComercial.evaluarCartaBonificacion(jugador, tableroMock));
    }

    @Test
    public void test05DosJugadoresDiferentesConCartasBonificacionDistintas() {
        Jugador jugador1 = new Jugador(1, "Constructor1", Color.BLUE);
        Jugador jugador2 = new Jugador(2, "Constructor2", Color.RED);

        CartaBonificacion carta1 = new GranCaballeria();
        CartaBonificacion carta2 = new GranRutaComercial();

        Tablero tableroMock = mock(Tablero.class);
        when(tableroMock.conseguirRutaMasLarga(jugador2)).thenReturn(5);

        assertDoesNotThrow(() -> carta1.evaluarCartaBonificacion(jugador1, tableroMock));
        assertDoesNotThrow(() -> carta2.evaluarCartaBonificacion(jugador2, tableroMock));
    }

    @Test
    public void test06GranCaballeriaOtorgaBonificacionAlTercerCaballero() {
        GranCaballeria granCaballeria = new GranCaballeria();
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);

        jugador.sumarCartaCaballeroJugada();
        jugador.sumarCartaCaballeroJugada();
        jugador.sumarCartaCaballeroJugada();

        Tablero tableroMock = mock(Tablero.class);

        assertDoesNotThrow(() -> granCaballeria.evaluarCartaBonificacion(jugador, tableroMock));
    }

    @Test
    public void test07GranRutaComercialOtorgaBonificacionAlTener5Carreteras() {
        GranRutaComercial granRutaComercial = new GranRutaComercial();
        Jugador jugador = mock(Jugador.class);
        Tablero tableroMock = mock(Tablero.class);
        when(tableroMock.conseguirRutaMasLarga(jugador)).thenReturn(5);
        assertDoesNotThrow(() -> granRutaComercial.evaluarCartaBonificacion(jugador, tableroMock));
    }

    @Test
    public void test08BonificacionGranRutaComercialPasaAlJugadorConMasCarreteras() {
        Tablero tablero = mock(Tablero.class);
        GranRutaComercial granRuta = new GranRutaComercial();

        Jugador jugador1 = new Jugador(1, "jugador1", Color.RED);
        Jugador jugador2 = new Jugador(2, "jugador2", Color.BLUE);

        int pvInicialjugador1 = jugador1.conseguirPuntosDeVictoriaTotales();
        int pvInicialjugador2 = jugador2.conseguirPuntosDeVictoriaTotales();


        Mockito.when(tablero.conseguirRutaMasLarga(jugador1)).thenReturn(5);
        Mockito.when(tablero.conseguirRutaMasLarga(jugador2)).thenReturn(0);

        granRuta.evaluarCartaBonificacion(jugador1, tablero);

        int pvTrasAsignacionjugador1 = jugador1.conseguirPuntosDeVictoriaTotales();
        int pvTrasAsignacionjugador2 = jugador2.conseguirPuntosDeVictoriaTotales();

        assertEquals(pvInicialjugador1 + 2, pvTrasAsignacionjugador1,
                "jugador1 debe ganar los 2 PV de la bonificación");
        assertEquals(pvInicialjugador2, pvTrasAsignacionjugador2,
                "jugador2 todavía no debe tener puntos extra");

        Mockito.when(tablero.conseguirRutaMasLarga(jugador1)).thenReturn(5);
        Mockito.when(tablero.conseguirRutaMasLarga(jugador2)).thenReturn(6);

        granRuta.evaluarCartaBonificacion(jugador2, tablero);

        int pvFinaljugador1 = jugador1.conseguirPuntosDeVictoriaTotales();
        int pvFinaljugador2 = jugador2.conseguirPuntosDeVictoriaTotales();

        assertEquals(pvInicialjugador1, pvFinaljugador1,
                "jugador1 debe perder los 2 PV cuando jugador2 supera la ruta");

        assertEquals(pvInicialjugador2 + 2, pvFinaljugador2,
                "jugador2 debe recibir la bonificación ahora");


    }
}