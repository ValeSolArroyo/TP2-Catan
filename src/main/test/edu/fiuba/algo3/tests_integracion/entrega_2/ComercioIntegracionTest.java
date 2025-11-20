package edu.fiuba.algo3.tests_integracion.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.juegoState.EstadoAccionesTurno;
import edu.fiuba.algo3.modelo.tableroFactory.TableroCatanFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComercioIntegracionTest {

    @Test
    public void test01JugadorPuedeComerciarConBancoTasa4a1() {
        // Arrange
        Jugador jugador1 = new Jugador(1, "Comerciante");
        Jugador jugador2 = new Jugador(2, "Socio");

        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();

        Juego juego = new Juego(List.of(jugador1, jugador2), tablero);

        // Fuerza el estado para permitir el comercio (simulamos que ya tiro dados)
        juego.setEstado(new EstadoAccionesTurno());

        // Act: Jugador cambia 4 Madera por 1 Ladrillo
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
        juego.setEstado(new EstadoAccionesTurno());

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
        juego.setEstado(new EstadoAccionesTurno());

        jugador1.recibir(new Madera());    
        jugador2.recibir(new Ladrillo());  

        int recursosJ1Antes = jugador1.obtenerCantidadTotalDeRecursos();
        int recursosJ2Antes = jugador2.obtenerCantidadTotalDeRecursos();

        // Act: J1 le da Madera a J2 a cambio de Ladrillo
        // El jugador actual (jugador1) propone el cambio
        juego.intercambiar(jugador2, new Madera(), new Ladrillo());

        // Assert
        // La cantidad total no cambia, solo cambian de mano
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
        juego.setEstado(new EstadoAccionesTurno());

        jugador1.recibir(new Madera());
        // Jugador 2 NO tiene nada

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
        juego.setEstado(new EstadoAccionesTurno());

        // Construimos en un vértice que sabemos que tiene Puerto 3:1 (Vértice 1)
        Vertice verticePuerto = tablero.encontrarVertice(1);

        // Recursos para construir el poblado
        jugador1.recibir(new Madera()); jugador1.recibir(new Ladrillo());
        jugador1.recibir(new Lana());   jugador1.recibir(new Grano());

        // Construimos para desbloquear el puerto
        jugador1.construirPoblado(verticePuerto);

        // Ahora le damos 3 Lanas para comerciar
        jugador1.recibir(new Lana());
        jugador1.recibir(new Lana());
        jugador1.recibir(new Lana());

        int recursosAntes = jugador1.obtenerCantidadTotalDeRecursos();

        // Act: Comerciar 3 Lanas por 1 Mineral (Tasa 3:1)
        juego.comerciarConBanco(new Lana(), new Mineral());

        // Assert: Gastó 3, recibió 1 -> Total baja en 2
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
        juego.setEstado(new EstadoAccionesTurno());

        // Construimos en un vértice que sabemos que tiene Puerto Madera (Vértice 4)
        Vertice verticePuerto = tablero.encontrarVertice(4);

        // Recursos para construir
        jugador1.recibir(new Madera()); jugador1.recibir(new Ladrillo());
        jugador1.recibir(new Lana());   jugador1.recibir(new Grano());

        jugador1.construirPoblado(verticePuerto);

        // Le damos 2 Maderas para comerciar
        jugador1.recibir(new Madera());
        jugador1.recibir(new Madera());

        int recursosAntes = jugador1.obtenerCantidadTotalDeRecursos();

        // Act: Comerciar 2 Maderas por 1 Grano (Tasa 2:1)
        juego.comerciarConBanco(new Madera(), new Grano());

        // Assert: Gastó 2, recibió 1 -> Total baja en 1
        int recursosDespues = jugador1.obtenerCantidadTotalDeRecursos();
        assertEquals(recursosAntes - 1, recursosDespues);
    }

    @Test
    public void test07JugadorConPuertoMaderaPaga4PorOtrosRecursos() {
        // Arrange: Mismo escenario, tiene Puerto de Madera
        Jugador jugador1 = new Jugador(1, "Maderero");
        Jugador jugador2 = new Jugador(2, "Socio");
        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();
        Juego juego = new Juego(List.of(jugador1, jugador2), tablero);
        juego.setEstado(new EstadoAccionesTurno());

        Vertice verticePuerto = tablero.encontrarVertice(4); // Puerto Madera

        // Construimos para desbloquear
        jugador1.recibir(new Madera()); jugador1.recibir(new Ladrillo());
        jugador1.recibir(new Lana());   jugador1.recibir(new Grano());
        jugador1.construirPoblado(verticePuerto);

        // Le damos 3 Ladrillos (NO alcanza para 4:1)
        jugador1.recibir(new Ladrillo());
        jugador1.recibir(new Ladrillo());
        jugador1.recibir(new Ladrillo());

        // Act & Assert
        // Intenta cambiar Ladrillo. Como su puerto es de Madera, la tasa para Ladrillo sigue siendo 4.
        // Con 3 Ladrillos debería fallar.
        assertThrows(RecursosInsuficientesError.class, () -> {
            juego.comerciarConBanco(new Ladrillo(), new Grano());
        });
    }
}