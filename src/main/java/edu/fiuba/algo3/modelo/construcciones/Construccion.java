package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.Jugador;

public abstract class Construccion {
    protected Jugador propietario;

    public Construccion(Jugador propietario) {
        this.propietario = propietario;
    }

    public boolean esPropietario(Jugador jugador) {
        return this.propietario.equals(jugador);
    }

    public abstract int obtenerPuntosVictoria();

    public abstract int recursosProducidos();

    public Jugador obtenerPropietario() {
        return propietario;
    }
}
