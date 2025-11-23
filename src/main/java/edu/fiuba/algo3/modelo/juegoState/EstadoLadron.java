package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EstadoLadron implements EstadoJuego {

    @Override
    public void descartePorLadron(Juego juego, List<Jugador> jugadores) {
        for (Jugador jugador : jugadores){
            jugador.descartar();
        }
    }

    @Override
    public List<Jugador> moverLadron(Juego juego, Hexagono hexagono) {
        Jugador jugador = juego.jugadorActual();
        return this.moverLadronInterno(hexagono, jugador);
    }

    public List<Jugador> moverLadronInterno(Hexagono hexagono, Jugador jugadorActual) {
        hexagono.ponerLadron();
        Set<Jugador> listaAfectados = new HashSet<>();
        hexagono.registrarPropietariosEn(listaAfectados);
        listaAfectados.remove(jugadorActual);
        return new ArrayList<>(listaAfectados);
    }

    @Override
    public void robarCartaDe(Juego juego, Jugador victima) {
        Jugador ladron = juego.jugadorActual();
        ladron.robarCarta(victima);
        juego.establecerEstado(new EstadoAccionesTurno());
    }

    @Override
    public void colocarPobladoInicial(Juego juego,Vertice vertice, Arista arista) {
        throw new IllegalStateException("No se puede realizar la colocación inicial en la fase del ladrón.");
    }

    public int lanzarDados(Juego juego, Dado dado){
        throw new IllegalStateException("No se pueden tirar los dados en la fase del ladrón.");
    }

    public void finalizarTurno(Juego juego) {
        throw new IllegalStateException("No se pueden tirar los dados en la fase del ladrón.");
    }

    public void construirCiudad(Juego juego, Vertice vertice) {
        throw new IllegalStateException("No se puede construir una ciudad en la fase del ladrón.");
    }

    public void construirPoblado(Juego juego, Vertice vertice) {
        throw new IllegalStateException("No se puede construir un poblado en la fase del ladrón.");
    }

    public void construirCarretera(Juego juego, Arista arista){
        throw new IllegalStateException("No se puede construir carretera en la fase del ladrón.");
    }
}