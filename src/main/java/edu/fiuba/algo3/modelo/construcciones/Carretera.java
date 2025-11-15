package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.Jugador;

public class Carretera extends Construccion {
    public Carretera(Jugador propietario) {
        super(propietario);
    }

    @Override
    public int obtenerPuntosVictoria() {
        return 0;
    }

    @Override public int recursosProducidos() {
        return 0;
    }
}
