package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;


public interface EstadoJuego {

    void colocarPobladoInicial(Juego juego, Vertice vertice, Arista arista, Jugador jugador, Tablero tablero);

    int lanzarDados(Juego juego, Dado dado);

    void verificarDescartesPorLadron(Juego juego, List<Jugador> jugadores);

    List<Jugador> moverLadron(Juego juego, Hexagono hexagono);

    void robarCartaDe(Juego juego, Jugador victima, Jugador ladron);
    
    void construirPoblado(Juego juego, Vertice vertice);

    void construirCiudad(Juego juego, Vertice vertice);

    void construirCarretera(Juego juego, Arista arista);

    void finalizarTurno(Juego juego);
}

