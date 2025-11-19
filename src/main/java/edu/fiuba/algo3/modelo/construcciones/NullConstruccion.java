package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

import java.util.Queue;
import java.util.Set;

public class NullConstruccion implements Construccion {

    @Override
    public void producir(Recurso recurso) {
        // No hay construcción, no produce nada
    }

    @Override
    public void registrarPropietarioEn(Set<Jugador> jugadores) {
        // No hace nada
    }

    @Override
    public void validarLugarLibre() {}


}
