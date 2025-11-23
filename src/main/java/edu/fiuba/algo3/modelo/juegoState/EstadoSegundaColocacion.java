package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;

import java.util.List;

public class EstadoSegundaColocacion implements EstadoJuego {

    @Override
    public void colocarPobladoInicial(Juego juego, Vertice vertice, Arista arista, Jugador jugador, Tablero tablero) {

        jugador.construirPobladoInicialEn(vertice);
        jugador.construirCarreteraInicialEn(arista);

        tablero.darRecursosIniciales(jugador, vertice);

        if (juego.todosTerminaronColocacionesIniciales()) {
            juego.setEstado(new EstadoTirarDados());
        } else {
            juego.pasarAlJugadorAnterior();
        }
    }

    public int lanzarDados(Juego juego, Dado dado){
        throw new IllegalStateException("No se pueden tirar los dados en la fase de colocaciones iniciales.");
    }

    public void verificarDescartesPorLadron(Juego juego, List<Jugador> jugadores){
        throw new IllegalStateException("No se puede verificar descarte por ladrón en colocaciones iniciales.");
    }

    public void robarCartaDe(Juego juego, Jugador victima, Jugador ladron){
        throw new IllegalStateException("No se puede robar carta en colocaciones iniciales.");
    }

    public List<Jugador> moverLadron(Juego juego, Hexagono hexagono, Jugador jugador){
        throw new IllegalStateException("No se puede mover al ladrón durante colocaciones iniciales.");
    }

    public void construirCiudad(Juego juego, Vertice vertice, Jugador jugador) {
        throw new IllegalStateException("No se puede construir una ciudad en la fase de colocaciones iniciales.");
    }

    public void construirPoblado(Juego juego, Vertice vertice, Jugador jugador) {
        throw new IllegalStateException("No se puede construir un poblado en la fase de colocaciones iniciales.");
    }

    public void construirCarretera(Juego juego, Arista arista, Jugador jugador) {
        throw new IllegalStateException("No se puede construir una carretera en la fase de colocaciones iniciales.");
    }

    public void finalizarTurno(Juego juego) {
        throw new IllegalStateException("No se puede finalizar el turno en la fase de colocaciones iniciales.");
    }
}
