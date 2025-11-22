package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;

public class EstadoAccionesTurno implements EstadoJuego {

    @Override
    public void construirPoblado(Juego juego, Vertice vertice) {
        juego.construirPobladoInterno(vertice);
    }

    @Override
    public void finalizarTurno(Juego juego) {
        juego.pasarAlSiguienteJugador();
        juego.setEstado(new EstadoTirarDados());
    }

    @Override
    public void colocarPobladoInicial(Juego juego, Vertice vertice, Arista arista) {
        throw new IllegalStateException("No se puede realizar la colocación inicial en la fase de acciones del turno.");
    }

    @Override
    public void comerciarConBanco(Juego juego, Recurso entregado, Recurso recibido) {
        juego.comerciarConBancoInterno(entregado, recibido);
    }

    @Override
    public void intercambiar(Juego juego, Jugador otroJugador, Recurso entregado, Recurso recibido) {
        juego.intercambiarInterno(otroJugador, entregado, recibido);
    }
}
