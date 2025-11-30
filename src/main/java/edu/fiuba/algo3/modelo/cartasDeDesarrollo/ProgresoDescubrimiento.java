package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;

import java.util.List;

public class ProgresoDescubrimiento implements CartaDesarrollo{

    private Jugador jugador;
    private List<Recurso> recursosDeBanca;

    public ProgresoDescubrimiento(Jugador jugador, List<Recurso> recursosDeBanca) {
        this.jugador = jugador;
        this.recursosDeBanca = recursosDeBanca;
    }

    @Override
    public void ejecutar() {
        for (Recurso recurso: recursosDeBanca) {
            jugador.recibirRecurso(recurso);
        }
    }
}
