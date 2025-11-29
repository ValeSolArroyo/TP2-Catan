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

public class AccionConstruir implements Accion{
    private Construccion construccion;
    private EspacioConstruible espacio;
    private Juego juego;

    public AccionConstruir(Construccion construccion, EspacioConstruible espacio, Juego juego){
        this.construccion = construccion;
        this.espacio = espacio;
        this.juego = juego;
    }

    @Override
    public void ejecutar(){
        juego.construir(construccion, espacio);
    }

}
