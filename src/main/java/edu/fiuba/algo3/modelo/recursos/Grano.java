package edu.fiuba.algo3.modelo.recursos;

import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;

public class Grano implements Recurso {
    @Override
    public void agregarseA(Inventario inventario) {
        inventario.sumarGrano();
    }

    @Override
    public void restarseDe(Inventario inventario) {
        inventario.restarGrano();
    }

    @Override
    public void asignarA(Jugador jugador) {
        jugador.recibir(this);
    }
}