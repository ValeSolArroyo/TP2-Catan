package edu.fiuba.algo3.tests_unitarios;

import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.terrenos.Bosque;
import edu.fiuba.algo3.modelo.terrenos.Terreno;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class VerticeTest {

    @Test
    public void test01NoPermiteConstruirPobladoSiHayVecinoOcupado() {
        Jugador jugador = new Jugador(1, "A");
        Jugador otro = new Jugador(2, "B");

        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);

        v1.agregarVecino(v2);

        v2.asignarConstruccion(new Poblado(otro));

        assertThrows(ReglaDeDistanciaError.class, () -> v1.construirPoblado(jugador),
                "No se puede construir un poblado en un vértice adyacente a otro ocupado");
    }

    @Test
    public void test02PermiteConstruirPobladoSiEstaLibre() {
        Jugador jugador = new Jugador(1, "A");
        Vertice v = new Vertice(1);

        v.construirPoblado(jugador);

        assertTrue(v.obtenerConstruccion() instanceof Poblado,
                "El vértice debería contener un Poblado después de construirlo");
    }

    @Test
    public void test03VerticeProduceRecursosParaSuConstruccion() {
        Jugador jugador = new Jugador(1, "A");
        Vertice vertice = new Vertice(1);
        Terreno bosque = new Bosque();

        vertice.construirPoblado(jugador);
        vertice.producirSegunTerreno(bosque);

        assertTrue(vertice.esPropiedadDe(jugador),
                "El vértice debería producir recursos para su propietario");
    }

    @Test
    public void test04RegistraPropietariosCorrectamente() {
        Jugador jugador1 = new Jugador(1, "A");
        Jugador jugador2 = new Jugador(2, "B");
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Set<Jugador> jugadores = new HashSet<>();

        v1.construirPoblado(jugador1);
        v2.construirPoblado(jugador2);

        v1.registrarPropietarioEn(jugadores);
        v2.registrarPropietarioEn(jugadores);

        assertEquals(2, jugadores.size(),
                "Deberían registrarse ambos propietarios");
        assertTrue(jugadores.contains(jugador1) && jugadores.contains(jugador2),
                "El conjunto debería contener a ambos jugadores");
    }

    @Test
    public void test05DiferentesJugadoresPuedenConstruirEnVerticesNoAdyacentes() {
        Jugador jugador1 = new Jugador(1, "A");
        Jugador jugador2 = new Jugador(2, "B");
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);

        v1.agregarVecino(v2);
        v2.agregarVecino(v1);
        v2.agregarVecino(v3);
        v3.agregarVecino(v2);

        v1.construirPoblado(jugador1);
        v3.construirPoblado(jugador2);

        assertTrue(v1.esPropiedadDe(jugador1) && v3.esPropiedadDe(jugador2),
                "Diferentes jugadores deberían poder construir respetando la regla de distancia");
    }
}
