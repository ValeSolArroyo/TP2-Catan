package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;

import java.util.List;

public class ProgresoMonopolio implements CartaDesarrollo {
    private Juego juego;
    private Recurso recursoDeseado;

    public ProgresoMonopolio(){

    }

    public ProgresoMonopolio(Juego juego, Recurso recursoDeseado) {
        this.juego = juego;
        this.recursoDeseado = recursoDeseado;
    }

    @Override
    public void ejecutarAlGuardar() {

    }

    @Override
    public void ejecutar() {
        juego.entregarAJugador(recursoDeseado);
    }
}
