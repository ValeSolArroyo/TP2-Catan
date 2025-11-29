package edu.fiuba.algo3.modelo.juegoCommand;

import edu.fiuba.algo3.modelo.juego.Juego;

public class AccionFinalizarTurno implements Accion{
    private Juego juego;

    public AccionFinalizarTurno(Juego juego){
        this.juego = juego;
    }

    @Override
    public void ejecutar(){
        juego.avanzarTurno();
    }
}
