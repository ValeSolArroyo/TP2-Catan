package edu.fiuba.algo3.modelo.juegoCommand;

import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;

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
