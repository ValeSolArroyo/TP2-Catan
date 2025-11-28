package edu.fiuba.algo3.modelo.juegoCommand;

import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.juego.Dado;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoState.EstadoAccionesTurno;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;

public class AccionActivarLadron implements Accion{

    @Override
    public void ejecutar(Juego juego, Jugador jugadorActual, Vertice vertice, Arista arista,
                         Dado dado, Construccion construccion, EspacioConstruible espacio,
                         List<CartaDesarrollo> cartasDesarrollo, CartaDesarrollo carta,
                         Jugador victima, List<Arista> carreterasAContruir,
                         List<Recurso> recursosDeBanca, Recurso recursoAnunciado,
                         List<Jugador> jugadores, Hexagono nuevoLugarLadron) {

        nuevoLugarLadron.ponerLadron();

        try {
            jugadorActual.robarCarta(victima);
        } catch (RuntimeException ignored) {
            // Sin cartas para robar: se continua igualmente con el flujo del estado del ladrón
        }
        juego.establecerEstado(new EstadoAccionesTurno());

        this.descartePorLadron(jugadores);
    }
    
    public void descartePorLadron(List<Jugador> jugadores) {
        for (Jugador jugador : jugadores){
            jugador.descartar();
        }
    }
}
