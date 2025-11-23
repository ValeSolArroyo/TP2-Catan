package edu.fiuba.algo3.modelo.juegoState;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;


public class EstadoPrimeraColocacion implements EstadoJuego {
    @Override
    public void colocarPobladoInicial(Juego juego, Vertice vertice, Arista arista, Jugador jugador) {
        this.colocarPobladoInicialInterno(vertice, jugador);
        this.construirCarreteraInicialInterno(arista, jugador);
        if (juego.todosColocaronPrimerPoblado()) {
            juego.setEstado(new EstadoSegundaColocacion());
        } else {
            juego.pasarAlSiguienteJugador();
        }
    }

    public void colocarPobladoInicialInterno(Vertice vertice, Jugador jugador) {
        jugador.construirPobladoInicialEn(vertice);
    }

    public void construirCarreteraInicialInterno(Arista arista, Jugador jugador) {
        jugador.construirCarreteraInicialEn(arista);
    }


}
