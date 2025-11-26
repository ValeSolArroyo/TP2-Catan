package edu.fiuba.algo3.modelo.recursos;

import edu.fiuba.algo3.modelo.jugador.Inventario;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public class Grano implements Recurso {
    @Override
    public void asignarA(Jugador jugador) {
        jugador.recibirRecurso(this);
    }

    @Override
    public void eliminarDe(Inventario inventario) {
        inventario.eliminarRecurso(this);
    }

    @Override
    public boolean coincideCon(Recurso otro) {
        return otro.coincideConGrano(this);
    }

    @Override
    public boolean coincideConMadera(Madera madera) { return false; }

    @Override
    public boolean coincideConLadrillo(Ladrillo ladrillo) { return false; }

    @Override
    public boolean coincideConLana(Lana lana) { return false; }

    @Override
    public boolean coincideConGrano(Grano grano) { return true; }

    @Override
    public boolean coincideConMineral(Mineral m) { return false; }
}