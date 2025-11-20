package edu.fiuba.algo3.tests_integracion.entrega_1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import edu.fiuba.algo3.modelo.construcciones.Poblado; 
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.juegoState.EstadoActivarLadron;
import edu.fiuba.algo3.modelo.tableroFactory.TableroCatanFactory;


public class LadronTest {
    
    private Tablero tablero;
    private Jugador jugadorActivo;
    private Jugador victimaA;
    private Jugador victimaB;
    private Juego juego;
    
    private final int VERTICE_ID_A = 10;
    private final int VERTICE_ID_B = 20;
    private final int HEXAGONO_DESTINO_ID = 5;
    private final int HEXAGONO_ORIGEN_ID = 1;  

    @BeforeEach
    public void setUp() {
        tablero = new TableroCatanFactory().crearTablero();
        
        jugadorActivo = new Jugador(1, "Activo");
        victimaA = new Jugador(2, "Víctima A");
        victimaB = new Jugador(3, "Víctima B");
        
        List<Jugador> jugadores = List.of(jugadorActivo, victimaA, victimaB);
        juego = new Juego(jugadores, tablero);
        tablero.obtenerHexagono(HEXAGONO_ORIGEN_ID).ponerLadron();
        
        Vertice vA = tablero.encontrarVertice(VERTICE_ID_A);
        Vertice vB = tablero.encontrarVertice(VERTICE_ID_B);
        vA.asignarConstruccion(new Poblado(victimaA)); 
        vB.asignarConstruccion(new Poblado(victimaB));
        juego.setEstado(new EstadoActivarLadron());
    }

    // Auxiliar para testeo
    private void inyectarRecursos(Jugador jugador, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            jugador.recibir(new Madera());
        }
    }
    

    @Test
    public void test01JugadorConMasDeSieteCartasDescartaLaMitad() {
        // Arrange
        inyectarRecursos(victimaA, 8); 
        int recursosAntes = victimaA.obtenerCantidadTotalDeRecursos();

        // Act
        juego.verificarDescartesPorLadron();

        // Assert
        int recursosDespues = victimaA.obtenerCantidadTotalDeRecursos();
        assertEquals(8, recursosAntes, "Debe empezar con 8 cartas.");
        assertEquals(4, recursosDespues, "Debe descartar exactamente la mitad (4).");
    }

   @Test
        public void test02MoverLadronIdentificaCorrectamenteLasVictimasAdyacentes() {
        // Arrange
        final int VERTICE_ID_A = 12;
        final int VERTICE_ID_B = 7;
        final int HEXAGONO_DESTINO_ID = 5; 
    
        Vertice verticeA = tablero.encontrarVertice(VERTICE_ID_A);
        Vertice verticeB = tablero.encontrarVertice(VERTICE_ID_B);
        verticeA.asignarConstruccion(new Poblado(victimaA)); 
        verticeB.asignarConstruccion(new Poblado(victimaB)); 
    
        // Act
        List<Jugador> victimasElegibles = juego.moverLadron(HEXAGONO_DESTINO_ID);

        // Assert
        assertEquals(2, victimasElegibles.size(), "Debe haber 2 víctimas elegibles.");
    }
    
    @Test
    public void test03RobarCartaTransfiereUnRecursoAleatorioDeLaVictimaAlLadron() {
        // Arrange
        inyectarRecursos(victimaA, 5); 
        int recursosActivoAntes = jugadorActivo.obtenerCantidadTotalDeRecursos(); 
        int recursosVictimaAntes = victimaA.obtenerCantidadTotalDeRecursos(); 

        // Act
        juego.robarCartaDe(victimaA);

        // Assert
        int recursosActivoDespues = jugadorActivo.obtenerCantidadTotalDeRecursos();
        int recursosVictimaDespues = victimaA.obtenerCantidadTotalDeRecursos();

        assertEquals(0, recursosActivoAntes, "El ladrón debe empezar con 0 cartas.");
        assertEquals(5, recursosVictimaAntes, "La víctima debe empezar con 5 cartas.");

        assertEquals(1, recursosActivoDespues, "El ladrón debe ganar 1 carta.");
        assertEquals(4, recursosVictimaDespues, "La víctima debe perder 1 carta.");
    }

    @Test
    public void test04MoverLadronBloqueaLaProduccionDelHexagono() {
        // Arrange
        Jugador jugador = new Jugador(99, "Pepe");
        Vertice vertice = tablero.encontrarVertice(7); 
        jugador.construirPobladoInicialEn(vertice); 
        Hexagono hexagono = tablero.obtenerHexagono(11); 
        int fichaProduccion = hexagono.obtenerNumeroFicha();
    
        // mover el ladrón (cambia el estado interno a EstadoConLadron)
        juego.moverLadron(HEXAGONO_DESTINO_ID);

        // Act
        tablero.producir(fichaProduccion); 
    
        // Assert
        assertEquals(0, jugador.obtenerCantidadTotalDeRecursos(), 
                 "El hexágono con el ladrón no debe producir recursos.");
    }
}