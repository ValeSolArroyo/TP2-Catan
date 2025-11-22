package edu.fiuba.algo3.modelo.juegoState;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;

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
    public void colocarPobladoInicial(Juego juego, Vertice vertice, Arista arista) {
        throw new IllegalStateException("No se puede realizar la colocación inicial en la fase de acciones del turno.");
    }
}

