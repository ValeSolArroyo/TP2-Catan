package edu.fiuba.algo3.modelo.recursos;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public class Ladrillo implements Recurso {

    @Override
    public boolean coincideCon(Recurso otro) {
        return otro.coincideConLadrillo(this);
    }

    @Override
    public boolean coincideConMadera(Madera madera) {return false; }

    @Override
    public boolean coincideConLadrillo(Ladrillo ladrillo) { return true; }

    @Override
    public boolean coincideConLana(Lana lana) { return false; }

    @Override
    public boolean coincideConGrano(Grano grano) { return false; }

    @Override
    public boolean coincideConMineral(Mineral m) { return false; }

    @Override
    public String getNombreRecurso() {
        return "Ladrillo";
    }
}