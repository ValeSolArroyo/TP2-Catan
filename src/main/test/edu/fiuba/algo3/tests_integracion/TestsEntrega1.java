package edu.fiuba.algo3.tests_integracion;

import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;
import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.AccionActivarLadron;
import edu.fiuba.algo3.modelo.juegoState.EstadoAccionesTurno;
import edu.fiuba.algo3.modelo.juegoState.EstadoLadron;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.tableroFactory.TableroCatanFactory;
import edu.fiuba.algo3.modelo.terrenos.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class TestsEntrega1 {
    private boolean tiene(Jugador j, Recurso r, int c) {
        try {
            j.tieneRecursos(r, c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
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
        assertThrows(ReglaDeDistanciaError.class, () -> jugador.construir(new Poblado(jugador), vertice2));
        assertDoesNotThrow(() -> jugador.construir(new Poblado(jugador), vertice3));
    }

    @Test
    public void test03JugadorRecibeRecursosInicialesAlColocarSegundoPoblado() {
        // Arrange
        Jugador jugador = new Jugador(1, "Ana");
        Vertice vertice = new Vertice();
        Hexagono hexagonoBosque = new Hexagono(new Bosque(), 8);
        hexagonoBosque.agregarVertice(vertice);
        Tablero tablero = new Tablero(List.of(hexagonoBosque));

        // Act
        jugador.construir(new Poblado(jugador), vertice);
        tablero.darRecursosIniciales(vertice);

        // Assert
        assertDoesNotThrow(() -> jugador.tieneRecursos(new Madera(), 1),
                "El jugador debería haber recibido 1 madera.");
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
        // Arrange
        Jugador jugador = new Jugador(1, "Luis");
        Vertice verticePoblado = new Vertice();
        Vertice verticeCiudad = new Vertice();
        Hexagono hexagonoCampo = new Hexagono(new Campo(), 6);
        hexagonoCampo.agregarVertice(verticePoblado);
        hexagonoCampo.agregarVertice(verticeCiudad);
        Tablero tablero = new Tablero(List.of(hexagonoCampo));
        Juego juego = new Juego(List.of(jugador), tablero, null);

        jugador.construir(new Poblado(jugador), verticePoblado);
        jugador.construir(new Ciudad(jugador), verticeCiudad);

        // Act
        juego.producirRecursos(6);

        // Assert
        assertDoesNotThrow(() -> jugador.tieneRecursos(new Grano(), 3),
                "El jugador debería tener 3 granos (1 del poblado + 2 de la ciudad).");
    }

    @Test
    public void test06LadronBloqueaProduccion() {
        // Arrange
        Jugador jugador = new Jugador(1, "Maria");
        Vertice verticePoblado = new Vertice();
        Hexagono hexagonoColina = new Hexagono(new Colina(), 9);
        hexagonoColina.agregarVertice(verticePoblado);
        Tablero tablero = new Tablero(List.of(hexagonoColina));
        Juego juego = new Juego(List.of(jugador), tablero, null);

        juego.establecerEstado(new EstadoAccionesTurno());
        jugador.construir(new Poblado(jugador), verticePoblado);

        juego.establecerEstado(new EstadoLadron());
        juego.activarLadron(new AccionActivarLadron(), hexagonoColina, jugador);

        // Act
        juego.producirRecursos(9);

        // Assert
        assertThrows(RuntimeException.class, () -> jugador.tieneRecursos(new Ladrillo(), 1),
                "El jugador no debería tener ladrillos, el ladrón bloqueó la producción.");
    }

    @Test
    public void test07DescarteAlSalirSiete() {
        // Arrange
        Jugador jugador = new Jugador(1, "Carlos");
        Hexagono hexagonoDesierto = new Hexagono(new Desierto(), 0);
        Tablero tablero = new Tablero(List.of(hexagonoDesierto));
        Juego juego = new Juego(List.of(jugador), tablero, null);

        for (int i = 0; i < 9; i++) {
            jugador.recibirRecurso(new Madera());
        }

        // Act
        juego.establecerEstado(new EstadoLadron());
        juego.activarLadron(new AccionActivarLadron(), hexagonoDesierto, jugador);

        // Assert
        assertDoesNotThrow(() -> jugador.tieneRecursos(new Madera(), 4),
                "El jugador debería haber descartado 4 cartas y quedarse con 5.");
        assertThrows(RuntimeException.class, () -> jugador.tieneRecursos(new Madera(), 6),
                "No debería tener 6 cartas.");
    }

    @Test
    public void test08MoverLadronYRobarCartaAleatoria() {
        // Arrange
        Jugador jugadorActivo = new Jugador(1, "Sofia");
        Jugador jugadorVictima = new Jugador(2, "Diego");
        Vertice vertice = new Vertice();
        Hexagono hexagonoDesierto = new Hexagono(new Desierto(), 0);
        hexagonoDesierto.agregarVertice(vertice);
        Tablero tablero = new Tablero(List.of(hexagonoDesierto));
        Juego juego = new Juego(List.of(jugadorActivo, jugadorVictima), tablero, null);

        jugadorVictima.recibirRecurso(new Madera());
        jugadorVictima.recibirRecurso(new Madera());
        jugadorVictima.recibirRecurso(new Ladrillo());
        jugadorVictima.recibirRecurso(new Lana());
        jugadorVictima.recibirRecurso(new Grano());
        jugadorVictima.construir(new Poblado(jugadorVictima), vertice);

        // Act
        juego.establecerEstado(new EstadoLadron());
        juego.activarLadron(new AccionActivarLadron(), hexagonoDesierto, jugadorVictima);

        // Assert: el activo ganó 1 carta de algún tipo y la víctima perdió 1 carta de alguno de los tipos que tenía
        boolean activoGanoAlgo =
                tiene(jugadorActivo, new Madera(), 1) ||
                tiene(jugadorActivo, new Ladrillo(), 1) ||
                tiene(jugadorActivo, new Lana(), 1) ||
                tiene(jugadorActivo, new Grano(), 1) ||
                tiene(jugadorActivo, new Mineral(), 1);

        // La víctima empezó con: 2 Maderas, 1 Ladrillo, 1 Lana, 1 Grano
        boolean victimaPerdioAlgo =
                !tiene(jugadorVictima, new Madera(), 2) ||
                !tiene(jugadorVictima, new Ladrillo(), 1) ||
                !tiene(jugadorVictima, new Lana(), 1) ||
                !tiene(jugadorVictima, new Grano(), 1);

        assertTrue(activoGanoAlgo, "El jugador activo debería haber ganado 1 carta tras el robo");
        assertTrue(victimaPerdioAlgo, "La víctima debería haber perdido 1 carta de alguno de sus recursos");
    }
}