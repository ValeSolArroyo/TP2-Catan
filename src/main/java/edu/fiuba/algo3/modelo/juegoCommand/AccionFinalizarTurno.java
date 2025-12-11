package edu.fiuba.algo3.modelo.juegoCommand;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public class AccionFinalizarTurno implements Accion {
    private Juego juego;

    public AccionFinalizarTurno(Juego juego){
        this.juego = juego;
    }

    @Override
    public void ejecutar(){
        Jugador jugador = juego.jugadorActual();
        jugador.habilitarCartasCompradas();
        juego.avanzarTurno();
    }
}
