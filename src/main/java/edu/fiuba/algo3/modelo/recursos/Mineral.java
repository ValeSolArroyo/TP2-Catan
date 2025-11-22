package edu.fiuba.algo3.modelo.recursos;

import edu.fiuba.algo3.modelo.jugador.Inventario;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.comercio.Puerto;

public class Mineral implements Recurso {
    @Override
    public void agregarseA(Inventario inventario) {
        inventario.sumarMineral();
    }

    @Override
    public void restarseDe(Inventario inventario, int cantidad) {
        inventario.restarMineral(cantidad);
    }

    @Override
    public void asignarA(Jugador jugador) {
        jugador.recibir(this);
    }

    @Override
    public int obtenerTasaEn(Puerto puerto) {
        return puerto.tasaMineral();
    }
}