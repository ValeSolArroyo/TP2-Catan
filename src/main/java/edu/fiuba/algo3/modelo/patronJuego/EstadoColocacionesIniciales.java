package edu.fiuba.algo3.modelo.patronJuego;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Vertice;

public class EstadoColocacionesIniciales implements EstadoJuego {

    @Override
    public void colocarPobladoInicial(Juego juego, Jugador jugador, int verticeId) {
        juego.colocarPobladoInicialInterno(jugador, verticeId);


        if (juego.todosTerminaronColocacionesIniciales()) {
            juego.setEstado(new EstadoTirarDados());
        }
    }

    @Override
    public void darRecursosIniciales(Juego juego, Jugador j, Vertice v) {
        juego.darRecursosInicialesInterno(j, v);
    }
}
