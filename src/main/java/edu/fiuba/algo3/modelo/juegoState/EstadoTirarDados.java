package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;

import java.util.List;

public class EstadoTirarDados implements EstadoJuego {

    @Override
    public int lanzarDados(Juego juego, Dado dado) {
        int resultado = dado.lanzarDados();

        if (resultado == 7) {
            juego.establecerEstado(new EstadoLadron());
        } else {
            juego.producirRecursos(resultado);
            juego.establecerEstado(new EstadoAccionesTurno());
        }
        return resultado;
    }

    @Override
    public void colocarPobladoInicial(Juego juego, Vertice vertice, Arista arista) {
        throw new IllegalStateException("No se puede realizar la colocación inicial el lanzamiento dados.");
    }

    public void descartePorLadron(Juego juego, List<Jugador> jugadores){
        throw new IllegalStateException("No se puede verificar descarte por ladrón durante el lanzamiento de dados.");
    }

    public void robarCartaDe(Juego juego, Jugador victima){
        throw new IllegalStateException("No se puede robar carta durante lanzamiento de dados.");
    }

    public List<Jugador> moverLadron(Juego juego, Hexagono hexagono){
        throw new IllegalStateException("No se puede mover al ladrón durante lanzamiento de dados.");
    }

    public void construirCiudad(Juego juego, Vertice vertice) {
        throw new IllegalStateException("No se puede construir una ciudad en la fase de lanzamiento dados.");
    }

    public void construirPoblado(Juego juego, Vertice vertice) {
        throw new IllegalStateException("No se puede construir una poblado en la fase de lanzamiento de dados.");
    }

    public void construirCarretera(Juego juego, Arista arista) {
        throw new IllegalStateException("No se puede construir una carretera en la fase de lanzamiento de dados.");
    }

    public void finalizarTurno(Juego juego) {
        throw new IllegalStateException("No se puede finalizar el turno en la fase de lanzamiento de dados.");
    }
}

