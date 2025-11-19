package edu.fiuba.algo3.modelo.patronJuego;

import edu.fiuba.algo3.modelo.Juego;

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
}

