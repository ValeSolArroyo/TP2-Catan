package edu.fiuba.algo3.modelo;

public abstract class Construccion {
    // ATRIBUTOS DE CLASE

    // ATRIBUTOS
    protected Jugador propietario;

    // CONSTRUCTORES
    public Construccion(Jugador propietario) {
        this.propietario = propietario;
    }

    // MÉTODOS DE CLASE

    // MÉTODOS GENERALES

    // MÉTODOS DE COMPORTAMIENTO
    public boolean esPropietario(Jugador jugador) {
        return this.propietario.equals(jugador);
    }

    public abstract int obtenerPuntosVictoria();

    public abstract int recursosProducidos();

    // GETTERS
    public Jugador obtenerPropietario() {
        return propietario;
    }

    // SETTERS
}
