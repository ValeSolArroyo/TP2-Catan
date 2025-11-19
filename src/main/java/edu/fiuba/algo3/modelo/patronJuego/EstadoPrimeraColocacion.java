package edu.fiuba.algo3.modelo.patronJuego;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Vertice;


public class EstadoPrimeraColocacion implements EstadoJuego {
    @Override
    public void colocarPobladoInicial(Juego juego, Jugador jugador, int verticeId, int aristaId) {
        juego.colocarPobladoInicialInterno(jugador, verticeId);
        juego.construirCarreteraInicialInterno(jugador, aristaId);
        juego.darPuntoDeVictoria(jugador);
        if (juego.todosColocaronPrimerPoblado()) {
            juego.setEstado(new EstadoSegundaColocacion());
        } else {
            juego.pasarAlSiguienteJugador();
        }
    }
}
