package edu.fiuba.algo3.modelo;

public class Poblado extends Construccion {
    // ATRIBUTOS DE CLASE

    // ATRIBUTOS

    // CONSTRUCTORES
    public Poblado(Jugador propietario) {
        super(propietario);
    }

    // MÉTODOS DE CLASE

    // MÉTODOS GENERALES

    // MÉTODOS DE COMPORTAMIENTO
    @Override
    public int obtenerPuntosVictoria() {
        return 1;
    }

    @Override public int recursosProducidos() {
        return 1;
    }

    // GETTERS

    // SETTERS
}