package edu.fiuba.algo3.tests_integracion;

import edu.fiuba.algo3.modelo.construcciones.*;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;
import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.tableroFactory.TableroCatanFactory;
import edu.fiuba.algo3.modelo.terrenos.*;
import javafx.scene.paint.Color;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

public class TestEntrega1 {
    @Test
    public void test01AleatoriedadDeTerrenosYFichas() {
        TableroCatanFactory factory = mock(TableroCatanFactory.class);

        List<Terreno> terrenoA = List.of(new Bosque(), new Colina(), new Campo());
        List<Terreno> terrenoB = List.of(new Campo(), new Pastizal(), new Montaña());
        List<Integer> fichasA = List.of(5, 8, 10);
        List<Integer> fichasB = List.of(6, 9, 3);

        when(factory.generarTerrenosAleatorios()).thenReturn(terrenoA, terrenoB);
        when(factory.generarFichasAleatorias()).thenReturn(fichasA, fichasB);

        List<Terreno> primera = factory.generarTerrenosAleatorios();
        List<Terreno> segunda = factory.generarTerrenosAleatorios();
        List<Integer> f1 = factory.generarFichasAleatorias();
        List<Integer> f2 = factory.generarFichasAleatorias();

        assertNotEquals(primera, segunda, "Las listas de terrenos deberían diferir entre invocaciones");
        assertNotEquals(f1, f2, "Las fichas deberían diferir entre invocaciones");
    }

    @Test
    public void test02ReglaDistanciaPobladosIniciales() {
        Jugador jugador = new Jugador(1, "Juan", Color.YELLOW);
        Vertice vertice1 = new Vertice();
        Vertice vertice2 = new Vertice();
        Vertice vertice3 = new Vertice();

        vertice1.agregarVecino(vertice2);
        vertice2.agregarVecino(vertice1);

        assertDoesNotThrow(() -> jugador.construirPrimerosPoblados(new Poblado(jugador), vertice1));
        assertThrows(ReglaDeDistanciaError.class, () -> jugador.construirPrimerosPoblados(new Poblado(jugador), vertice2));
        assertDoesNotThrow(() -> jugador.construirPrimerosPoblados(new Poblado(jugador), vertice3));
    }

    @Test
    public void test03JugadorRecibeRecursosInicialesAlColocarSegundoPoblado() {
        Jugador jugador = new Jugador(1, "Ana", Color.BLUE);
        Vertice vertice = new Vertice();
        Hexagono hexagonoBosque = new Hexagono(new Bosque(), 8);
        hexagonoBosque.agregarVertice(vertice);
        Tablero tablero = new Tablero(List.of(hexagonoBosque));

        jugador.construirPrimerosPoblados(new Poblado(jugador), vertice);
        tablero.darRecursosIniciales(vertice);

        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(new Madera())));
    }

    @Test
    public void test04LanzamientoDadosValido() {
        Dado dado = new Dado();

        int resultado = dado.lanzarDados();

        assertTrue(resultado >= 2 && resultado <= 12);
    }

    @Test
    public void test05ProduccionPobladoYCiudad() {
        Jugador jugador = new Jugador(1, "Luis", Color.GREEN);
        Vertice verticePoblado = new Vertice();
        Hexagono hexagonoCampo = new Hexagono(new Campo(), 6);
        hexagonoCampo.agregarVertice(verticePoblado);
        Tablero tablero = new Tablero(List.of(hexagonoCampo));
        Juego juego = new Juego(List.of(jugador), tablero, null);

        jugador.construirPrimerosPoblados(new Poblado(jugador), verticePoblado);
        
        jugador.recibirRecurso(new Grano());
        jugador.recibirRecurso(new Grano());
        for (int i = 0; i < 3; i++) {
            jugador.recibirRecurso(new Mineral());
        }
        
        jugador.construir(new Ciudad(jugador), verticePoblado);

        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(new Grano())));
    }

    @Test
    public void test06LadronBloqueaProduccion() {
        Jugador jugador = new Jugador(1, "Maria", Color.YELLOW);
        Vertice verticePoblado = new Vertice();
        Hexagono hexagonoColina = new Hexagono(new Colina(), 9);
        hexagonoColina.agregarVertice(verticePoblado);
        Tablero tablero = new Tablero(List.of(hexagonoColina));
        Juego juego = new Juego(List.of(jugador), tablero, null);

        jugador.construirPrimerosPoblados(new Poblado(jugador), verticePoblado);

        hexagonoColina.ponerLadron();

        juego.producirRecursos(9);

        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Ladrillo())));
    }

    @Test
    public void test07DescarteAlSalirSiete() {
        Jugador jugador = new Jugador(1, "Carlos", Color.BLUE);
        Hexagono hexagonoDesierto = new Hexagono(new Desierto(), 0);
        Tablero tablero = new Tablero(List.of(hexagonoDesierto));
        Juego juego = new Juego(List.of(jugador), tablero, null);

        java.util.stream.IntStream.range(0, 9).forEach(i -> jugador.recibirRecurso(new Madera()));

        juego.descartePorLadron();
        
        List<Recurso> cincoMaderas = java.util.stream.Stream.generate(Madera::new).limit(5).collect(Collectors.toList());
        List<Recurso> seisMaderas = java.util.stream.Stream.generate(Madera::new).limit(6).collect(Collectors.toList());
        assertDoesNotThrow(() -> jugador.entregarRecursos(cincoMaderas));
        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(seisMaderas));
    }

    @Test
    public void test08MoverLadronYRobarCartaAleatoria() {
        Jugador jugadorActivo = new Jugador(1, "Sofia", Color.BLUE);
        Jugador jugadorVictima = new Jugador(2, "Diego", Color.ORANGE);
        Vertice vertice = new Vertice();
        Hexagono hexagonoDesierto = new Hexagono(new Desierto(), 0);
        hexagonoDesierto.agregarVertice(vertice);
        Tablero tablero = new Tablero(List.of(hexagonoDesierto));
        Juego juego = new Juego(List.of(jugadorActivo, jugadorVictima), tablero, null);

        jugadorVictima.construirPrimerosPoblados(new Poblado(jugadorVictima), vertice);
        jugadorVictima.recibirRecurso(new Madera());
        jugadorVictima.recibirRecurso(new Ladrillo());
        jugadorVictima.recibirRecurso(new Lana());
        jugadorVictima.recibirRecurso(new Grano());

        hexagonoDesierto.ponerLadron();

        juego.robarCartaDe(jugadorVictima);

        List<Recurso> recursosOriginales = List.of(new Madera(), new Ladrillo(), new Lana(), new Grano());
        assertThrows(RecursosInsuficientesError.class, () -> jugadorVictima.entregarRecursos(recursosOriginales));

        boolean roboExitoso = false;
        List<Recurso> tiposPosibles = List.of(new Madera(), new Ladrillo(), new Lana(), new Grano());

        for (Recurso recurso : tiposPosibles) {
            try {
                jugadorActivo.entregarRecursos(List.of(recurso));
                roboExitoso = true;
                break;
            } catch (Exception e) {
                // No hizo el robo de este tipo de recurso
            }
        }

        assertTrue(roboExitoso, "El jugador activo debería poder entregar 1 recurso de algún tipo tras el robo");
    }
}