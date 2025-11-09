package edu.fiuba.algo3.modelo;

public abstract class Construccion {
    protected Jugador propietario;

    public Construccion(Jugador propietario) {
        this.propietario = propietario;
    }

    public Jugador obtenerPropietario() {
        return propietario;
    }

    public abstract int obtenerPuntosVictoria();
}
