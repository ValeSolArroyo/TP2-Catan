package edu.fiuba.algo3.modelo;

public class Poblado extends Construccion {
    public Poblado(Jugador propietario) {
        super(propietario);
    }

    @Override
    public int obtenerPuntosVictoria() {
        return 1;
    }
}
