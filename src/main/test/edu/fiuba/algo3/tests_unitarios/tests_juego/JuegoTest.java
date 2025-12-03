package edu.fiuba.algo3.tests_unitarios.tests_juego;

import edu.fiuba.algo3.modelo.comercio.Banca;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.*;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTest {
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private List<CartaDesarrollo> cartas;

    @BeforeEach
    public void setUp() {
        jugador1 = new Jugador(1, "Ana", "Rojo");
        jugador2 = new Jugador(2, "Juan", "Azul");
        tablero = new Tablero(new ArrayList<>());
        cartas = new ArrayList<>();
    }

    @Test
    public void test01JuegoIniciaConElPrimerJugadorDeLaLista() {
        // Arrange
        jugador1.recibirRecurso(new Madera());
        jugador1.recibirRecurso(new Madera());
        jugador1.recibirRecurso(new Madera());
        jugador1.recibirRecurso(new Madera());

        Juego juego = new Juego(List.of(jugador1, jugador2), tablero, cartas);
        Banca comercioConBanca = new Banca(new Madera(), new Ladrillo());

        // Act & Assert
        assertDoesNotThrow(() -> juego.ejecutarComercioJugador(comercioConBanca));
    }

    @Test
    public void test02AvanzarTurnoCambiaElJugadorActual() {
        // Arrange
        jugador2.recibirRecurso(new Lana());
        jugador2.recibirRecurso(new Lana());
        jugador2.recibirRecurso(new Lana());
        jugador2.recibirRecurso(new Lana());

        Juego juego = new Juego(List.of(jugador1, jugador2), tablero, cartas);
        Banca comercioConBanca = new Banca(new Lana(), new Grano());

        // Act & Assert
        juego.avanzarTurno();
        assertDoesNotThrow(() -> juego.ejecutarComercioJugador(comercioConBanca));
    }

    @Test
    public void test03AvanzarTurnoVuelveAlPrimerJugadorDespuesDeUnaRondaCompleta() {
        // Arrange
        jugador1.recibirRecurso(new Madera());
        jugador1.recibirRecurso(new Madera());
        jugador1.recibirRecurso(new Madera());
        jugador1.recibirRecurso(new Madera());

        Juego juego = new Juego(List.of(jugador1, jugador2), tablero, cartas);
        Banca comercioConBanca = new Banca(new Madera(), new Ladrillo());

        // Act
        juego.avanzarTurno(); 
        juego.avanzarTurno();

        // Assert
        assertDoesNotThrow(() -> juego.ejecutarComercioJugador(comercioConBanca));
    }

    @Test
    public void test04JugadorPuedeComprarCartaDeDesarrolloSiTieneRecursos() {
        // Arrange
        Juego juego = new Juego(List.of(jugador1, jugador2), tablero, cartas);

        cartas.add(new PuntoVictoria());

        jugador1.recibirRecurso(new Grano());
        jugador1.recibirRecurso(new Lana());
        jugador1.recibirRecurso(new Mineral());

        // Act & Assert
        assertDoesNotThrow(() -> juego.comprarCartaDesarrollo());
        assertThrows(Exception.class, juego::comprarCartaDesarrollo);
    }
}