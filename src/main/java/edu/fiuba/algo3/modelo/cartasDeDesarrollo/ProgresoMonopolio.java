package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

public class ProgresoMonopolio implements CartaDesarrollo {
    private Juego juego;
    private Recurso recursoDeseado;
    private Jugador jugadorActual;

    public ProgresoMonopolio(){}

    public ProgresoMonopolio(Juego juego, Recurso recursoDeseado, Jugador jugadorActual) {
        this.juego = juego;
        this.recursoDeseado = recursoDeseado;
        this.jugadorActual = jugadorActual;
    }

    @Override
    public void ejecutarAlGuardar(Jugador jugador) {}

    @Override
    public void ejecutar() {
        juego.entregarAJugador(recursoDeseado);
        jugadorActual.eliminarCarta(this);
    }

    public String getCarta(){
        return "Progreso Monopolio";
    }
}
