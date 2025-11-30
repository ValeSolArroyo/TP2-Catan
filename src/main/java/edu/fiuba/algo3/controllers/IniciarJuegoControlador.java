package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.cartasDeDesarrollo.FabricaCartas;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tableroFactory.TableroCatanFactory;

import java.util.List;

public class IniciarJuegoControlador {
    public Juego crearNuevaPartida(List<Jugador> jugadores) {
        TableroCatanFactory tableroJuego = new TableroCatanFactory();
        Tablero tablero = tableroJuego.crearTablero();
        List<CartaDesarrollo> cartasDesarrollo = FabricaCartas.crearMazo();

        return new Juego(jugadores, tablero, cartasDesarrollo);
    }
}


