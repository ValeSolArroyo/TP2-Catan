package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public class PuntoVictoria implements CartaDesarrollo {
    private boolean cartaHabilitada= true;

    public PuntoVictoria(){}

    @Override
    public void ejecutarAlGuardar(Jugador jugador) {
        jugador.sumarPVPorCartaDesarollo();
        jugador.eliminarCarta(new PuntoVictoria());
    }

    @Override
    public void ejecutar() {
    }

    public String getCarta(){
        return "Punto de Victoria";
    }

    @Override
    public void habilitarCarta() {
    }

    @Override
    public boolean getHabilitacion() {
        return cartaHabilitada;
    }

    @Override
    public boolean coincideCon(CartaDesarrollo cartaAComparar) {
        return cartaAComparar.coincideConPuntoVictoria();
    }

    @Override
    public boolean coincideConCaballero() {
        return false;
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
        return true;
    }
}
