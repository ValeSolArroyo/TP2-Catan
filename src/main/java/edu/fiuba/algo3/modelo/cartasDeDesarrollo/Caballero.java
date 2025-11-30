package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;

import java.util.List;

public class Caballero implements CartaDesarrollo {

    private Juego juego;
    private Jugador victima;
    private Jugador jugador;
    private Hexagono nuevoLugar;

    public Caballero(Juego juego, Jugador victima, Jugador jugador, Hexagono nuevoLugar) {
        this.juego = juego;
        this.victima = victima;
        this.jugador = jugador;
        this.nuevoLugar = nuevoLugar;
    }

    @Override
    public void ejecutar() {
        juego.moverLadron(nuevoLugar);
        juego.robarCartaDe(victima);
        jugador.registrarCaballeroJugado();
        juego.revisarGranCaballeria(jugador);
    }
}
