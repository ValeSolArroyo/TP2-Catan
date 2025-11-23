package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;

import java.util.List;

public class EstadoAccionesTurno implements EstadoJuego {

    @Override
    public void construirPoblado(Juego juego, Vertice vertice, Jugador jugador) {
        jugador.construirPobladoInicialEn(vertice);
    }

    @Override
    public void construirCiudad(Juego juego, Vertice vertice, Jugador jugador){
        jugador.construirCiudad(vertice);
    }

    @Override
    public void construirCarretera(Juego juego, Arista arista, Jugador jugador) {
        jugador.construirCarretera(arista);
    }

    @Override
    public void finalizarTurno(Juego juego) {
       juego.pasarAlSiguienteJugador();
        juego.setEstado(new EstadoTirarDados());
    }

    public void colocarPobladoInicial(Juego juego,Vertice vertice, Arista arista, Jugador jugador, Tablero tablero) {
        throw new IllegalStateException("No se puede realizar la colocación inicial en la fase del ladrón.");
    }

    public int lanzarDados(Juego juego, Dado dado){
        throw new IllegalStateException("No se pueden tirar los dados durante el desarrollo de un turno.");
    }

    public void verificarDescartesPorLadron(Juego juego, List<Jugador> jugadores){
        throw new IllegalStateException("No se puede verificar descarte por ladron durante el desarrollo de un turno.");
    }

    public void robarCartaDe(Juego juego, Jugador victima, Jugador jugador){
        throw new IllegalStateException("No se puede robar carta en acciones de turno.");
    }

    public List<Jugador> moverLadron(Juego juego, Hexagono hexagono, Jugador jugador){
        throw new IllegalStateException("No se puede mover al ladron durante las accones de turno.");
    }

}
