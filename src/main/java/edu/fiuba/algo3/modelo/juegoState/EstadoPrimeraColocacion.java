package edu.fiuba.algo3.modelo.juegoState;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;


public class EstadoPrimeraColocacion implements EstadoJuego {
    @Override
    public void colocarPobladoInicial(Juego juego, Jugador jugador, int verticeId, int aristaId) {
        juego.colocarPobladoInicialInterno(jugador, verticeId);
        juego.construirCarreteraInicialInterno(jugador, aristaId);
        if (juego.todosColocaronPrimerPoblado()) {
            juego.setEstado(new EstadoSegundaColocacion());
        } else {
            juego.pasarAlSiguienteJugador();
        }
    }
}
