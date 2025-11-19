package edu.fiuba.algo3.modelo.patronJuego;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Vertice;

public class EstadoSegundaColocacion implements EstadoJuego {

    @Override
    public void colocarPobladoInicial(Juego juego, Jugador jugador, int verticeId) {

        Vertice vertice = juego.colocarPobladoInicialInterno(jugador, verticeId);
        juego.darRecursosInicialesInterno(jugador, vertice);

        if (juego.todosTerminaronColocacionesIniciales()) {
            juego.setEstado(new EstadoTirarDados());
        } else {
            juego.pasarAlJugadorAnterior();
        }
    }
}
