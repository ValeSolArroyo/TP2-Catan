package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;

import java.util.List;
import java.util.Set;

public class NullConstruccion implements Construccion {
    @Override
    public boolean esPropiedadDe(Jugador jugador) {
        return false;
    }

    @Override
    public List<Recurso> cobrar() {
        return List.of();
    }

    @Override
    public void validarEn(EspacioConstruible espacio, Jugador jugador) {}

    @Override
    public void ocupar() {
    }

}
