package edu.fiuba.algo3.tests_unitarios;

import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class VerticeTest {

    @Test
    public void test01NoPermiteConstruirPobladoSiHayVecinoOcupado() {
        // Arrange
        Jugador jugador = new Jugador(1, "A");
        Jugador otro = new Jugador(2, "B");

        Vertice vertice1 = new Vertice(1);
        Vertice vertice2 = new Vertice(2);
        vertice1.agregarVecino(vertice2);

        vertice2.asignarConstruccion(new Poblado(otro));

        // Act & Assert
        assertThrows(ReglaDeDistanciaError.class, () -> vertice1.construirPoblado(jugador),
                "No se puede construir un poblado en un vértice adyacente a otro ocupado");
    }

    @Test
    public void test02PermiteConstruirPobladoSiEstaLibre() {
        // Arrange
        Jugador jugador = new Jugador(1, "A");
        Vertice vertice = new Vertice(1);

        // Act
        vertice.construirPoblado(jugador);

        // Assert
        assertTrue(vertice.obtenerConstruccion() instanceof Poblado,
                "El vértice debería contener un Poblado después de construirlo");
    }

    @Test
    public void test03RegistraPropietariosCorrectamente() {
        // Arrange
        Jugador jugador1 = new Jugador(1, "A");
        Jugador jugador2 = new Jugador(2, "B");
        Vertice vertice1 = new Vertice(1);
        Vertice vertice2 = new Vertice(2);
        Set<Jugador> jugadores = new HashSet<>();

        vertice1.construirPoblado(jugador1);
        vertice2.construirPoblado(jugador2);

        // Act
        vertice1.registrarPropietarioEn(jugadores);
        vertice2.registrarPropietarioEn(jugadores);

        // Assert
        assertEquals(2, jugadores.size(),
                "Deberían registrarse ambos propietarios");
        assertTrue(jugadores.contains(jugador1) && jugadores.contains(jugador2),
                "El conjunto debería contener a ambos jugadores");
    }

    @Test
    public void test04DiferentesJugadoresPuedenConstruirEnVerticesNoAdyacentes() {
        // Arrange
        Jugador jugador1 = new Jugador(1, "A");
        Jugador jugador2 = new Jugador(2, "B");
        Vertice vertice1 = new Vertice(1);
        Vertice vertice2 = new Vertice(2);
        Vertice vertice3 = new Vertice(3);

        vertice1.agregarVecino(vertice2);
        vertice2.agregarVecino(vertice1);
        vertice2.agregarVecino(vertice3);
        vertice3.agregarVecino(vertice2);

        // Act
        vertice1.construirPoblado(jugador1);
        vertice3.construirPoblado(jugador2);

        // Assert
        assertTrue(vertice1.esPropiedadDe(jugador1) && vertice3.esPropiedadDe(jugador2),
                "Diferentes jugadores deberían poder construir respetando la regla de distancia");
    }
}
