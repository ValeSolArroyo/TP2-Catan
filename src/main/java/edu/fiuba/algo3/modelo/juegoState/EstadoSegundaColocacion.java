package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.*;

import java.util.List;

public class EstadoSegundaColocacion implements EstadoJuego {

    @Override
    public void colocarPobladoInicial(Juego juego, Vertice vertice, Arista arista) {
        Jugador jugador = juego.jugadorActual();

        jugador.construir(new Poblado(jugador), vertice);
        jugador.construir(new Carretera(jugador), arista);

        juego.darRecursosIniciales(vertice);

        juego.segundaColocacionRealizada();
    }

    public int lanzarDados(Juego juego, Dado dado){
        throw new IllegalStateException("No se pueden tirar los dados en la fase de colocaciones iniciales.");
    }

    public void descartePorLadron(Juego juego, List<Jugador> jugadores){
        throw new IllegalStateException("No se puede verificar descarte por ladrón en colocaciones iniciales.");
    }

    public void robarCartaDe(Juego juego, Jugador victima){
        throw new IllegalStateException("No se puede robar carta en colocaciones iniciales.");
    }

    public List<Jugador> moverLadron(Juego juego, Hexagono hexagono){
        throw new IllegalStateException("No se puede mover al ladrón durante colocaciones iniciales.");
    }

    public void construir(Juego juego, Construccion construccion, EspacioConstruible espacio) {
        throw new IllegalStateException("No se puede construir durante colocaciones iniciales.");
    }

    public void finalizarTurno(Juego juego) {
        throw new IllegalStateException("No se puede finalizar el turno en la fase de colocaciones iniciales.");
    }
}
