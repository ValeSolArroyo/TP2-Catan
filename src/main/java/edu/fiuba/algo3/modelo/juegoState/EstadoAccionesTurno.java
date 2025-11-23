package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;

import java.util.List;

public class EstadoAccionesTurno implements EstadoJuego {

    @Override
    public void construirPoblado(Juego juego, Vertice vertice) {
        Jugador jugador = juego.jugadorActual();
        jugador.construirPobladoInicialEn(vertice);
    }

    @Override
    public void construirCiudad(Juego juego, Vertice vertice){
        Jugador jugador = juego.jugadorActual();
        jugador.construirCiudad(vertice);
    }

    @Override
    public void construirCarretera(Juego juego, Arista arista) {
        Jugador jugador = juego.jugadorActual();
        jugador.construirCarretera(arista);
    }

    @Override
    public void finalizarTurno(Juego juego) {
       juego.avanzarTurno();
        juego.establecerEstado(new EstadoTirarDados());
    }

    public void colocarPobladoInicial(Juego juego,Vertice vertice, Arista arista) {
        throw new IllegalStateException("No se puede realizar la colocación inicial en la fase del ladrón.");
    }

    public int lanzarDados(Juego juego, Dado dado){
        throw new IllegalStateException("No se pueden tirar los dados durante el desarrollo de un turno.");
    }

    public void descartePorLadron(Juego juego, List<Jugador> jugadores){
        throw new IllegalStateException("No se puede verificar descarte por ladron durante el desarrollo de un turno.");
    }

    public void robarCartaDe(Juego juego, Jugador victima){
        throw new IllegalStateException("No se puede robar carta en acciones de turno.");
    }

    public List<Jugador> moverLadron(Juego juego, Hexagono hexagono){
        throw new IllegalStateException("No se puede mover al ladron durante las accones de turno.");
    }

}
