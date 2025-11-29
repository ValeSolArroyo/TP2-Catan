package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;

import java.util.List;

public class ProgresoDescubrimiento implements CartaDesarrollo{
    @Override
    public void aplicarEfecto(Juego juego, Jugador jugador, Jugador victima, List<Arista> carreterasAContruir, List<Recurso> recursosDeBanca, Recurso recursoAnunciado, List<Jugador> jugadores, Hexagono nuevoLugarLadron) {
        for (Recurso recurso: recursosDeBanca) {
            jugador.recibirRecurso(recurso);
        }
    }

    @Override
    public int puntosVictoria() {
        return 0;
    }
}
