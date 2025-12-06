package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

import java.util.List;

public class ProgresoDescubrimiento implements CartaDesarrollo{

    private Jugador jugadorActual;
    private  List<Recurso> recursosDeBanca;

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
        jugadorActual.eliminarCarta(this);
    }

    public String getCarta(){
        return "Progreso de Descubrimiento";
    }
}
