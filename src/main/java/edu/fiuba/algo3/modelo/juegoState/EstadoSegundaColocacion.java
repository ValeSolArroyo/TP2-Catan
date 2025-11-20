package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Vertice;

public class EstadoSegundaColocacion implements EstadoJuego {

    @Override
    public void colocarPobladoInicial(Juego juego, Jugador jugador, int verticeId, int aristaId) {

        Vertice vertice = juego.colocarPobladoInicialInterno(jugador, verticeId);
        juego.construirCarreteraInicialInterno(jugador, aristaId);
        juego.darPuntoDeVictoria(jugador);
        juego.darRecursosInicialesInterno(jugador, vertice);
        if (juego.todosTerminaronColocacionesIniciales()) {
            juego.setEstado(new EstadoTirarDados());
        } else {
            juego.pasarAlJugadorAnterior();
        }
    }
}
