package edu.fiuba.algo3.modelo.juegoState;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.modelo.Arista;


public class EstadoPrimeraColocacion implements EstadoJuego {
    @Override
    public void colocarPobladoInicial(Juego juego, Vertice vertice, Arista arista) {
        juego.colocarPobladoInicialInterno(vertice);
        juego.construirCarreteraInicialInterno(arista);
        if (juego.todosColocaronPrimerPoblado()) {
            juego.setEstado(new EstadoSegundaColocacion());
        } else {
            juego.pasarAlSiguienteJugador();
        }
    }
}
