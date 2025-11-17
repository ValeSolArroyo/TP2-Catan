package edu.fiuba.algo3.modelo.recursos;

import edu.fiuba.algo3.modelo.Jugador;

public class Mineral implements Recurso {
    @Override
    public void asignarA(Jugador jugador) {
        jugador.recibir(this);
    }
}