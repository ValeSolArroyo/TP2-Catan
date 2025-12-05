package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public class PuntoVictoria implements CartaDesarrollo {
    public PuntoVictoria(){}

    @Override
    public void ejecutarAlGuardar(Jugador jugador) {
        jugador.sumarPVPorCartaDesarollo();
        jugador.eliminarCarta(this);
    }

    @Override
    public void ejecutar() {
    }

    public String getCarta(){
        return "Punto de Victoria";
    }
}
