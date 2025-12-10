package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Hexagono;

public class Caballero implements CartaDesarrollo {

    private Juego juego;
    private Hexagono nuevoLugar;
    private Jugador victima;
    private Jugador jugadorActual;

    public Caballero(){}

    public Caballero(Juego juego, Hexagono nuevoLugar, Jugador victima, Jugador jugadorActual){
        this.juego = juego;
        this.nuevoLugar = nuevoLugar;
        this.victima = victima;
        this.jugadorActual = jugadorActual;
    }

    @Override
    public void ejecutarAlGuardar(Jugador jugador) {}

    @Override
    public void ejecutar() {
        juego.moverLadron(nuevoLugar);
        juego.robarCartaDe(victima);
        jugadorActual.eliminarCarta(new Caballero());
        jugadorActual.sumarCartaCaballeroJugada();
    }

    public String getCarta(){
        return "Caballero";
    }
    @Override
    public boolean coincideCon(CartaDesarrollo cartaAComparar) {
        return cartaAComparar.coincideConCaballero();
    }
    @Override
    public boolean coincideConCaballero() {
        return true;
    }

    @Override
    public boolean coincideConProgresoConstruccion() {
        return false;
    }

    @Override
    public boolean coincideConProgresoDescubrimiento() {
        return false;
    }

    @Override
    public boolean coincideConProgresoMonopolio() {
        return false;
    }

    @Override
    public boolean coincideConPuntoVictoria() {
        return false;
    }
}
