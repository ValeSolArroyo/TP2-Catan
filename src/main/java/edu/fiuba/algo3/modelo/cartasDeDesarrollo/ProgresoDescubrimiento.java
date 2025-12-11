package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

import java.util.List;

public class ProgresoDescubrimiento implements CartaDesarrollo{

    private Jugador jugadorActual;
    private  List<Recurso> recursosDeBanca;
    private boolean cartaHabilitada= false;

    public ProgresoDescubrimiento(){}

    public ProgresoDescubrimiento(Jugador jugador,  List<Recurso> recursosDeBanca) {
       this.jugadorActual = jugador;
       this.recursosDeBanca = recursosDeBanca;
    }

    @Override
    public void ejecutarAlGuardar(Jugador jugador) {}

    @Override
    public void ejecutar() {
        for (Recurso recurso: recursosDeBanca) {
            jugadorActual.recibirRecurso(recurso);
        }
        jugadorActual.eliminarCarta(new ProgresoDescubrimiento());
    }

    public String getCarta(){
        return "Progreso de Descubrimiento";
    }

    @Override
    public void habilitarCarta() {
        this.cartaHabilitada = true;

    }

    @Override
    public boolean getHabilitacion() {
        return cartaHabilitada;
    }

    @Override
    public boolean coincideCon(CartaDesarrollo cartaAComparar) {
        return cartaAComparar.coincideConProgresoDescubrimiento();
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
        return true;
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
