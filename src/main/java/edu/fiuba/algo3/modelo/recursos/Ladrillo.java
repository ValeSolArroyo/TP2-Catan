package edu.fiuba.algo3.modelo.recursos;

import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.comercio.Puerto;

public class Ladrillo implements Recurso {
    @Override
    public void agregarseA(Inventario inventario) {
        inventario.sumarLadrillo();
    }

    @Override
    public void restarseDe(Inventario inventario, int cantidad) {
        inventario.restarLadrillo(cantidad);
    }

    @Override
    public void asignarA(Jugador jugador) {
        jugador.recibir(this);
    }

    @Override
    public int obtenerTasaEn(Puerto puerto) {
        return puerto.tasaLadrillo();
    }
}