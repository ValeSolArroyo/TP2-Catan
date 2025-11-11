package edu.fiuba.algo3.modelo;

public class Ciudad extends Construccion {

    public Ciudad(Jugador propietario) {
        super(propietario);
    }

    @Override
    public int obtenerPuntosVictoria() {
        return 2;
    }

    @Override public int recursosProducidos() {
        return 2;
    }
}