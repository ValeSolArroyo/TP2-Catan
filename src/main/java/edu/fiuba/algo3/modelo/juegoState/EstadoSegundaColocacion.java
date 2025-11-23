package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;

public class EstadoSegundaColocacion implements EstadoJuego {

    @Override
    public void colocarPobladoInicial(Juego juego, Vertice vertice, Arista arista, Jugador jugador, Tablero tablero)) {

        this.colocarPobladoInicialInterno(vertice);
        this.construirCarreteraInicialInterno(arista);
        this.darRecursosInicialesInterno(vertice, jugador, tablero);

        if (juego.todosTerminaronColocacionesIniciales()) {
            juego.setEstado(new EstadoTirarDados());
        } else {
            juego.pasarAlJugadorAnterior();
        }
    }

    public void colocarPobladoInicialInterno(Vertice vertice, Jugador jugador) {
        jugador.construirPobladoInicialEn(vertice);
    }

    public void construirCarreteraInicialInterno(Arista arista, Jugador jugador) {
        jugador.construirCarreteraInicialEn(arista);
    }

    public void darRecursosInicialesInterno(Vertice vertice, Jugador jugador, Tablero tablero) {
        tablero.darRecursosIniciales(jugador, vertice);
    }

}
