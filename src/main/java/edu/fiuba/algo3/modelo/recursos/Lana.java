package edu.fiuba.algo3.modelo.recursos;

import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;

public class Lana implements Recurso {
    @Override
    public void agregarseA(Inventario inventario) {
        inventario.sumarLana();
    }

    @Override
    public void restarseDe(Inventario inventario) {
        inventario.restarLana();
    }

    @Override
    public void asignarA(Jugador jugador) {
        jugador.recibir(this);
    }
}
