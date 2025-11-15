package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.Jugador;

public class Poblado extends Construccion {
    public Poblado(Jugador propietario) {
        super(propietario);
    }

    @Override
    public int obtenerPuntosVictoria() {
        return 1;
    }

    @Override public int recursosProducidos() {
        return 1;
    }
}