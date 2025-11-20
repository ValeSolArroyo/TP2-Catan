package edu.fiuba.algo3.modelo.recursos;

import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;

public class Madera implements Recurso {
    @Override
    public void agregarseA(Inventario inventario) {
        inventario.sumarMadera();
    }

    @Override
    public void restarseDe(Inventario inventario) {
        inventario.restarMadera();
    }

    @Override
    public void asignarA(Jugador jugador) {
        jugador.recibir(this);
    }
}
