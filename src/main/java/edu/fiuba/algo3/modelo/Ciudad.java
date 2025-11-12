package edu.fiuba.algo3.modelo;

public class Ciudad extends Construccion {
    // ATRIBUTOS DE CLASE

    // ATRIBUTOS

    // CONSTRUCTORES
    public Ciudad(Jugador propietario) {
        super(propietario);
    }

    // MÉTODOS DE CLASE

    // MÉTODOS GENERALES

    // MÉTODOS DE COMPORTAMIENTO
    @Override
    public int obtenerPuntosVictoria() {
        return 2;
    }

    @Override public int recursosProducidos() {
        return 2;
    }

    // GETTERS

    // SETTERS
}