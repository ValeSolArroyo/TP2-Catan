package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;

import java.util.List;

public class ProgresoDescubrimiento implements CartaDesarrollo{

    @Override
    public void ejecutar(Juego juego, Jugador victima, Jugador jugador, Hexagono nuevoLugar, List<Arista> carreterasAConstruir, List<Recurso> recursosDeBanca, Recurso recursoDeseado) {
        for (Recurso recurso: recursosDeBanca) {
            jugador.recibirRecurso(recurso);
        }
    }
}
