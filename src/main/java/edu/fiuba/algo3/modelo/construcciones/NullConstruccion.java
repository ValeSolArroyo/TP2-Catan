package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.jugador.Inventario;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;

import java.util.List;
import java.util.Set;

public class NullConstruccion implements Construccion {
    @Override
    public void validarEn(EspacioConstruible espacio, Jugador jugador) {
    }

    @Override
    public boolean tieneDePropietarioA(Jugador jugador) {
        return false;
    }

    @Override
    public void cobrar(Inventario inventario) {
        inventario.consumirRecurso(List.of());
    }

    @Override
    public void producir(Recurso recurso){

    }

    public void registrarPropietarioEn(Set<Jugador> jugadores) {

    }

    @Override
    public void ocupar() {
    }

    @Override
    public int puntosVictoria() {
        return 0;
    }

}
