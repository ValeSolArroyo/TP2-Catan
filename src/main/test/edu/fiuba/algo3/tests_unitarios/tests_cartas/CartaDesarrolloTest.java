package edu.fiuba.algo3.tests_unitarios.tests_cartas;

import edu.fiuba.algo3.modelo.cartasDeDesarrollo.Caballero;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.PuntoVictoria;
import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.ProgresoConstruccion;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.ProgresoDescubrimiento;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.ProgresoMonopolio;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.FabricaCartas;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CartaDesarrolloTest {

    @Test
    public void test01PuntoVictoriaExisteYEsValido() {
        CartaDesarrollo puntoVictoria = new PuntoVictoria();
        assertNotNull(puntoVictoria);
    }

    @Test
    public void test02CaballeroExisteYEsValido() {
        CartaDesarrollo caballero = new Caballero();
        assertNotNull(caballero);
    }

    @Test
    public void test03ProgresoConstruccionExisteYEsValido() {
        CartaDesarrollo progresoConstruccion = new ProgresoConstruccion();
        assertNotNull(progresoConstruccion);
    }

    @Test
    public void test04ProgresoDescubrimientoExisteYEsValido() {
        CartaDesarrollo progresoDescubrimiento = new ProgresoDescubrimiento();
        assertNotNull(progresoDescubrimiento);
    }

    @Test
    public void test05ProgresoMonopolioExisteYEsValido() {
        CartaDesarrollo progresoMonopolio = new ProgresoMonopolio();
        assertNotNull(progresoMonopolio);
    }

    @Test
    public void test06CaballeroAumentaCaballerosJugados() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        edu.fiuba.algo3.modelo.juego.Juego juego = org.mockito.Mockito.mock(edu.fiuba.algo3.modelo.juego.Juego.class);
        edu.fiuba.algo3.modelo.tablero.Hexagono hexagono = org.mockito.Mockito.mock(edu.fiuba.algo3.modelo.tablero.Hexagono.class);
        Jugador victima = new Jugador(2, "Victima", Color.RED);
        Caballero caballero = new Caballero(juego, hexagono, victima, jugador);
        int antes = jugador.conseguirCartasCaballeroJugadas();
        caballero.ejecutar();
        int despues = jugador.conseguirCartasCaballeroJugadas();
        assertEquals(antes + 1, despues);
    }

    @Test
    public void test07PuntoVictoriaSumaPunto() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        PuntoVictoria puntoVictoria = new PuntoVictoria();
        int antes = jugador.conseguirPuntosDeVictoria();
        puntoVictoria.ejecutarAlGuardar(jugador);
        int despues = jugador.conseguirPuntosDeVictoria();
        assertTrue(despues > antes);
    }

    @Test
    public void test08ProgresoDescubrimientoOtorgaRecursos() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        List<Recurso> recursos = List.of(new Madera(), new Grano());
        ProgresoDescubrimiento carta = new ProgresoDescubrimiento(jugador, recursos);
        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(recursos));
        carta.ejecutar();
        assertDoesNotThrow(() -> jugador.entregarRecursos(recursos));
    }

    @Test
    public void test09ProgresoConstruccionConstruyeCarretera() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        edu.fiuba.algo3.modelo.juego.Juego juego = org.mockito.Mockito.mock(edu.fiuba.algo3.modelo.juego.Juego.class);
        edu.fiuba.algo3.modelo.tablero.Arista arista = org.mockito.Mockito.mock(edu.fiuba.algo3.modelo.tablero.Arista.class);
        List<edu.fiuba.algo3.modelo.tablero.Arista> aristas = List.of(arista);
        ProgresoConstruccion carta = new ProgresoConstruccion(juego, jugador, aristas);
        assertDoesNotThrow(() -> carta.ejecutar());
    }

    @Test
    public void test10ProgresoMonopolioEntregaRecursosCorrectamente() {
        edu.fiuba.algo3.modelo.juego.Juego juego = org.mockito.Mockito.mock(edu.fiuba.algo3.modelo.juego.Juego.class);
        Recurso recurso = new Madera();
        ProgresoMonopolio carta = new ProgresoMonopolio(juego, recurso);
        carta.ejecutar();
        org.mockito.Mockito.verify(juego).entregarAJugador(recurso);
    }

    @Test
    public void test11PuntoVictoriaSeEliminaAlGuardar() {
        Jugador jugador = new Jugador(1, "Jugador", Color.BLUE);
        PuntoVictoria pv = new PuntoVictoria();
        pv.ejecutarAlGuardar(jugador);
        assertFalse(jugador.getCartasDesarrollo().contains(pv));
    }

    @Test
    public void test12CaballeroMueveLadron() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        Juego juego = mock(Juego.class);
        Hexagono hex = mock(Hexagono.class);
        Jugador victima = mock(Jugador.class);

        Caballero carta = new Caballero(juego, hex, victima, jugador);
        carta.ejecutar();
        verify(juego).moverLadron(hex);
    }

    @Test
    public void test13CaballeroRobaCarta() {
        Juego juego = mock(Juego.class);
        Hexagono hex = mock(Hexagono.class);
        Jugador jugador = new Jugador(1, "Jugador", Color.BLUE);
        Jugador victima = new Jugador(2, "Victima", Color.RED);

        Caballero caballero = new Caballero(juego, hex, victima, jugador);
        caballero.ejecutar();
        verify(juego).robarCartaDe(victima);
    }

    @Test
    public void test14ConstruccionConstruyeCarreteras() {
        Jugador jugador = mock(Jugador.class);
        Juego juego = mock(Juego.class);
        Arista arista = mock(Arista.class);

        List<Arista> aristas = List.of(arista);
        ProgresoConstruccion carta = new ProgresoConstruccion(juego, jugador, aristas);
        carta.ejecutar();

        verify(jugador, times(1)).construir(any(Carretera.class), eq(arista));
    }

    @Test
    public void test15MazoDeDesarrolloContieneLasCantidadCorrectas() {
        List<CartaDesarrollo> mazo = FabricaCartas.crearMazo();

        long caballeros = mazo.stream().filter(c -> c instanceof Caballero).count();
        long pv = mazo.stream().filter(c -> c instanceof PuntoVictoria).count();
        long mono = mazo.stream().filter(c -> c instanceof ProgresoMonopolio).count();
        long desc = mazo.stream().filter(c -> c instanceof ProgresoDescubrimiento).count();
        long constru = mazo.stream().filter(c -> c instanceof ProgresoConstruccion).count();

        assertEquals(14, caballeros);
        assertEquals(5, pv);
        assertEquals(2, mono);
        assertEquals(2, desc);
        assertEquals(2, constru);
    }

    @Test
    public void test16MazoEsAleatorio() {
        List<CartaDesarrollo> mazo1 = FabricaCartas.crearMazo();
        List<CartaDesarrollo> mazo2 = FabricaCartas.crearMazo();
        assertNotEquals(mazo1.toString(), mazo2.toString());
    }

    

}