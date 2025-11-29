package edu.fiuba.algo3.modelo.juegoCommand;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.tablero.Hexagono;

public class AccionMoverLadron implements Accion{
    private Juego juego;
    private Hexagono nuevoLugar;

    public AccionMoverLadron(Juego juego, Hexagono nuevoLugar){
        this.juego = juego;
        this. nuevoLugar = nuevoLugar;
    }

    @Override
    public void ejecutar(){
        juego.moverLadron(nuevoLugar);
    }
}
