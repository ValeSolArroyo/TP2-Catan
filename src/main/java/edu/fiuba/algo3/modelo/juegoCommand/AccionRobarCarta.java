package edu.fiuba.algo3.modelo.juegoCommand;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public class AccionRobarCarta implements Accion{
    private Juego juego;
    private Jugador victima;

    public AccionRobarCarta(Juego juego, Jugador victima){
        this.juego = juego;
        this.victima = victima;
    }

    @Override
    public void ejecutar(){
        juego.robarCartaDe(victima);
    }
}

