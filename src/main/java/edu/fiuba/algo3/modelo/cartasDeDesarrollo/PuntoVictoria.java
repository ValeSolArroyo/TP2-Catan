package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.excepciones.CartaNoJugableError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;

import java.util.List;

public class PuntoVictoria implements CartaDesarrollo {
    private Jugador jugadorActual;

    public PuntoVictoria(){

    }

    public PuntoVictoria(Jugador jugador){
        this.jugadorActual = jugador;
    }

    @Override
    public void ejecutarAlGuardar() {
        jugadorActual.sumarPVPorCartaDesarollo();
    }

    @Override
    public void ejecutar() {
    }

    public String getCarta(){
        return "Punto de Victoria";
    }
}
