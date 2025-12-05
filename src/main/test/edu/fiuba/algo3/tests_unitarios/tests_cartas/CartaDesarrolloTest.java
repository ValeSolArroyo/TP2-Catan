package edu.fiuba.algo3.tests_unitarios.tests_cartas;

import edu.fiuba.algo3.modelo.cartasDeDesarrollo.Caballero;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.PuntoVictoria;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.ProgresoConstruccion;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.ProgresoDescubrimiento;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.ProgresoMonopolio;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    public void test06ProgresoDescubrimientoReparteRecursos() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        CartaDesarrollo progresoDescubrimiento = new ProgresoDescubrimiento();

        List<Recurso> recursosEnBanca = List.of(new Madera(), new Grano());

        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Madera(), new Grano())));
        assertNotNull(progresoDescubrimiento);
    }

    @Test
    public void test07CaballeroAumentaCaballerosJugados() {
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
    public void test08PuntoVictoriaSumaPunto() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        PuntoVictoria puntoVictoria = new PuntoVictoria();
        int antes = jugador.conseguirPuntosDeVictoria();
        puntoVictoria.ejecutarAlGuardar(jugador);
        int despues = jugador.conseguirPuntosDeVictoria();
        assertTrue(despues > antes);
    }

    @Test
    public void test09ProgresoDescubrimientoOtorgaRecursos() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        List<Recurso> recursos = List.of(new Madera(), new Grano());
        ProgresoDescubrimiento carta = new ProgresoDescubrimiento(jugador, recursos);
        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(recursos));
        carta.ejecutar();
        assertDoesNotThrow(() -> jugador.entregarRecursos(recursos));
    }

    @Test
    public void test10ProgresoConstruccionConstruyeCarretera() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        edu.fiuba.algo3.modelo.juego.Juego juego = org.mockito.Mockito.mock(edu.fiuba.algo3.modelo.juego.Juego.class);
        edu.fiuba.algo3.modelo.tablero.Arista arista = org.mockito.Mockito.mock(edu.fiuba.algo3.modelo.tablero.Arista.class);
        List<edu.fiuba.algo3.modelo.tablero.Arista> aristas = List.of(arista);
        ProgresoConstruccion carta = new ProgresoConstruccion(juego, jugador, aristas);
        assertDoesNotThrow(() -> carta.ejecutar());
    }

    @Test
    public void test11ProgresoMonopolioInvocaMonopolio() {
        edu.fiuba.algo3.modelo.juego.Juego juego = org.mockito.Mockito.mock(edu.fiuba.algo3.modelo.juego.Juego.class);
        Recurso recurso = new Madera();
        ProgresoMonopolio carta = new ProgresoMonopolio(juego, recurso);
        carta.ejecutar();
        org.mockito.Mockito.verify(juego).entregarAJugador(recurso);
    }
}
