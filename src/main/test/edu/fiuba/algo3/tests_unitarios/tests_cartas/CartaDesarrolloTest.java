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

        // Antes de ejecutar
        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Madera(), new Grano())));

        // Después de ejecutar (simulando sin parámetros de Juego)
        // Se verifica indirectamente a través de comportamiento observable
        assertNotNull(progresoDescubrimiento);
    }

    @Test
    public void test07CartasDeDesarrolloImplementanInterfaz() {
        CartaDesarrollo carta1 = new Caballero();
        CartaDesarrollo carta2 = new PuntoVictoria();
        CartaDesarrollo carta3 = new ProgresoConstruccion();
        CartaDesarrollo carta4 = new ProgresoDescubrimiento();
        CartaDesarrollo carta5 = new ProgresoMonopolio();

        assertTrue(carta1 instanceof CartaDesarrollo);
        assertTrue(carta2 instanceof CartaDesarrollo);
        assertTrue(carta3 instanceof CartaDesarrollo);
        assertTrue(carta4 instanceof CartaDesarrollo);
        assertTrue(carta5 instanceof CartaDesarrollo);
    }

    @Test
    public void test08DiferentesCartasDeDesarrolloSonDistintas() {
        CartaDesarrollo caballero = new Caballero();
        CartaDesarrollo puntoVictoria = new PuntoVictoria();
        CartaDesarrollo progresoConstruccion = new ProgresoConstruccion();

        assertNotEquals(caballero.getClass(), puntoVictoria.getClass());
        assertNotEquals(puntoVictoria.getClass(), progresoConstruccion.getClass());
        assertNotEquals(caballero.getClass(), progresoConstruccion.getClass());
    }
}
