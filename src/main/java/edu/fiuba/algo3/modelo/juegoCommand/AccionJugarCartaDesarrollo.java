package edu.fiuba.algo3.modelo.juegoCommand;

import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.juego.Juego;

public class AccionJugarCartaDesarrollo implements Accion{
    private Juego juego;
    private CartaDesarrollo cartaDesarrollo;

    public AccionJugarCartaDesarrollo(Juego juego, CartaDesarrollo cartaDesarrollo){
        this.juego = juego;
        this.cartaDesarrollo = cartaDesarrollo;
    }


    @Override
    public void ejecutar(){
        juego.jugarCartaDesarrollo(cartaDesarrollo);
    }

}
