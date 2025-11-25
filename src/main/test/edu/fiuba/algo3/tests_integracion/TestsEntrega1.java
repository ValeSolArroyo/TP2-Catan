package edu.fiuba.algo3.tests_integracion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;
import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.*;

import edu.fiuba.algo3.modelo.tableroFactory.TableroCatanFactory;
import edu.fiuba.algo3.modelo.terrenos.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class TestsEntrega1 {
    @Test
    public void test01AleatoriedadDeTerrenosYFichas() {
        // Arrange
        TableroCatanFactory terrenoFactory = new TableroCatanFactory();
        List<Terreno> listaTerrenos1 = terrenoFactory.generarTerrenosAleatorios();
        List<Terreno> listaTerrenos2 = terrenoFactory.generarTerrenosAleatorios();

        // Act
        List<Integer> listaFichas1 = terrenoFactory.generarFichasAleatorias();
        List<Integer> listaFichas2 = terrenoFactory.generarFichasAleatorias();

        // Assert
        assertNotEquals(listaTerrenos1.toString(), listaTerrenos2.toString());
        assertNotEquals(listaFichas1.toString(), listaFichas2.toString());
    }

    @Test
    public void test02ReglaDistanciaPobladosIniciales() {
        // Arrange
        Jugador jugador = new Jugador(1, "Juan");
        Vertice vertice1 = new Vertice();
        Vertice vertice2 = new Vertice();
        Vertice vertice3 = new Vertice();

        // Act
        vertice1.agregarVecino(vertice2);
        vertice2.agregarVecino(vertice1);

        // Assert
        assertDoesNotThrow(() -> jugador.construir(new Poblado(jugador), vertice1));
        assertThrows(ReglaDeDistanciaError.class, () -> jugador.construir(new Poblado(jugador), vertice2)
        );
        assertDoesNotThrow(() -> jugador.construir(new Poblado(jugador), vertice3));
    }

    @Test
    public void test03JugadorRecibeRecursosInicialesAlColocarSegundoPoblado() {
    }

    @Test
    public void test04LanzamientoDadosValido() {
        // Arrange
        Dado dado = new Dado();

        // Act
        int resultado = dado.lanzarDados();

        // Assert
        assertTrue(resultado >= 2 && resultado <= 12);
    }

    @Test
    public void test05ProduccionPobladoYCiudad() {
    }

    @Test
    public void test06LadronBloqueaProduccion() {
    }

    @Test
    public void test07DescarteAlSalirSiete() {
    }

    @Test
    public void test08MoverLadronYRobarCartaAleatoria() {
    }
}
