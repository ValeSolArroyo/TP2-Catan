package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;

public class EstadoSegundaColocacion implements EstadoJuego {

    @Override
    public void colocarPobladoInicial(Juego juego, Vertice vertice, Arista arista) {

        juego.colocarPobladoInicialInterno(vertice);
        juego.construirCarreteraInicialInterno(arista);
        juego.darRecursosInicialesInterno(vertice);
        if (juego.todosTerminaronColocacionesIniciales()) {
            juego.setEstado(new EstadoTirarDados());
        } else {
            juego.pasarAlJugadorAnterior();
        }
    }
}
