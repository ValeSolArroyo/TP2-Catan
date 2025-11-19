package edu.fiuba.algo3.modelo.patronJuego;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;

public class EstadoTirarDados implements EstadoJuego {

    @Override
    public int lanzarDados(Juego juego) {
        int resultado = juego .lanzarDadosInterno();

        if (resultado == 7) {
            juego.setEstado(new EstadoActivarLadron());
        } else {
            juego.producirRecursos(resultado);
            juego.setEstado(new EstadoAccionesTurno());
        }
        return resultado;
    }

    @Override
    public void colocarPobladoInicial(Juego juego, Jugador jugador, int verticeID, int aristaID) {
        throw new IllegalStateException("No se puede realizar la colocación inicial en la fase de acciones del turno.");
    }
}

