package edu.fiuba.algo3.tests_integracion.entrega_2;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.juegoState.EstadoAccionesTurno;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tableroFactory.TableroCatanFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComercioIntegracionTest {

    /*@Test
    public void test01JugadorPuedeComerciarConBancoTasa4a1() {
        // Arrange
        Jugador jugador1 = new Jugador(1, "Comerciante");
        Jugador jugador2 = new Jugador(2, "Socio");

        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();

        Juego juego = new Juego(List.of(jugador1, jugador2), tablero);

        juego.establecerEstado(new EstadoAccionesTurno());

        // Act
        jugador1.recibir(new Madera());
        jugador1.recibir(new Madera());
        jugador1.recibir(new Madera());
        jugador1.recibir(new Madera());

        int recursosAntes = jugador1.obtenerCantidadTotalDeRecursos();
        juego.comerciarConBanco(new Madera(), new Ladrillo());
        int recursosDespues = jugador1.obtenerCantidadTotalDeRecursos();

        // Assert
        assertEquals(recursosAntes - 3, recursosDespues);
    }

    @Test
    public void test02JugadorNoPuedeComerciarSinRecursosSuficientes() {
        // Arrange
        Jugador jugador1 = new Jugador(1, "Comerciante");
        Jugador jugador2 = new Jugador(2, "Socio");

        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();

        Juego juego = new Juego(List.of(jugador1, jugador2), tablero);
        juego.establecerEstado(new EstadoAccionesTurno());

        jugador1.recibir(new Madera());
        jugador1.recibir(new Madera());
        jugador1.recibir(new Madera());

        // Act & Assert
        assertThrows(RecursosInsuficientesError.class, () -> {
            juego.comerciarConBanco(new Madera(), new Ladrillo());
        });
    }

    @Test
    public void test03IntercambioExitosoEntreDosJugadores() {
        // Arrange
        Jugador jugador1 = new Jugador(1, "Comerciante");
        Jugador jugador2 = new Jugador(2, "Socio");

        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();

        Juego juego = new Juego(List.of(jugador1, jugador2), tablero);
        juego.establecerEstado(new EstadoAccionesTurno());

        jugador1.recibir(new Madera());    
        jugador2.recibir(new Ladrillo());  

        int recursosJ1Antes = jugador1.obtenerCantidadTotalDeRecursos();
        int recursosJ2Antes = jugador2.obtenerCantidadTotalDeRecursos();

        // Act
        juego.intercambiar(jugador2, new Madera(), new Ladrillo());

        // Assert
        assertEquals(recursosJ1Antes, jugador1.obtenerCantidadTotalDeRecursos());
        assertEquals(recursosJ2Antes, jugador2.obtenerCantidadTotalDeRecursos());
        assertDoesNotThrow(() -> juego.intercambiar(jugador2, new Ladrillo(), new Madera()));
    }

    @Test
    public void test04IntercambioFallaSiElOtroJugadorNoTieneElRecurso() {
        // Arrange
        Jugador jugador1 = new Jugador(1, "Comerciante");
        Jugador jugador2 = new Jugador(2, "Socio");

        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();

        Juego juego = new Juego(List.of(jugador1, jugador2), tablero);
        juego.establecerEstado(new EstadoAccionesTurno());

        jugador1.recibir(new Madera());

        // Act & Assert
        assertThrows(RecursosInsuficientesError.class, () -> {
            juego.intercambiar(jugador2, new Madera(), new Ladrillo());
        });
    }

    @Test
    public void test05JugadorConPuertoGenericoComerciaTasa3a1() {
        // Arrange
        Jugador jugador1 = new Jugador(1, "Portuario");
        Jugador jugador2 = new Jugador(2, "Socio");
        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();
        Juego juego = new Juego(List.of(jugador1, jugador2), tablero);
        juego.establecerEstado(new EstadoAccionesTurno());

        Vertice verticePuerto = tablero.encontrarVertice(1);

        jugador1.recibir(new Madera()); jugador1.recibir(new Ladrillo());
        jugador1.recibir(new Lana());   jugador1.recibir(new Grano());

        jugador1.construirPoblado(verticePuerto);

        jugador1.recibir(new Lana());
        jugador1.recibir(new Lana());
        jugador1.recibir(new Lana());

        int recursosAntes = jugador1.obtenerCantidadTotalDeRecursos();

        // Act
        juego.comerciarConBanco(new Lana(), new Mineral());

        // Assert
        int recursosDespues = jugador1.obtenerCantidadTotalDeRecursos();
        assertEquals(recursosAntes - 2, recursosDespues);
    }

    @Test
    public void test06JugadorConPuertoMaderaComerciaTasa2a1() {
        // Arrange
        Jugador jugador1 = new Jugador(1, "Maderero");
        Jugador jugador2 = new Jugador(2, "Socio");
        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();
        Juego juego = new Juego(List.of(jugador1, jugador2), tablero);
        juego.establecerEstado(new EstadoAccionesTurno());

        Vertice verticePuerto = tablero.encontrarVertice(4);

        jugador1.recibir(new Madera()); jugador1.recibir(new Ladrillo());
        jugador1.recibir(new Lana());   jugador1.recibir(new Grano());

        jugador1.construirPoblado(verticePuerto);

        jugador1.recibir(new Madera());
        jugador1.recibir(new Madera());

        int recursosAntes = jugador1.obtenerCantidadTotalDeRecursos();

        // Act
        juego.comerciarConBanco(new Madera(), new Grano());

        // Assert
        int recursosDespues = jugador1.obtenerCantidadTotalDeRecursos();
        assertEquals(recursosAntes - 1, recursosDespues);
    }

    @Test
    public void test07JugadorConPuertoMaderaPaga4PorOtrosRecursos() {
        // Arrange
        Jugador jugador1 = new Jugador(1, "Maderero");
        Jugador jugador2 = new Jugador(2, "Socio");
        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();
        Juego juego = new Juego(List.of(jugador1, jugador2), tablero);
        juego.establecerEstado(new EstadoAccionesTurno());

        Vertice verticePuerto = tablero.encontrarVertice(4); // Puerto Madera

        jugador1.recibir(new Madera()); jugador1.recibir(new Ladrillo());
        jugador1.recibir(new Lana());   jugador1.recibir(new Grano());
        jugador1.construirPoblado(verticePuerto);

        jugador1.recibir(new Ladrillo());
        jugador1.recibir(new Ladrillo());
        jugador1.recibir(new Ladrillo());

        // Act & Assert
        assertThrows(RecursosInsuficientesError.class, () -> {
            juego.comerciarConBanco(new Ladrillo(), new Grano());
        });
    }*/
}