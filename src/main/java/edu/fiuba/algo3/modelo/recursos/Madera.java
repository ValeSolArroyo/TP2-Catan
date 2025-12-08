package edu.fiuba.algo3.modelo.recursos;

public class Madera implements Recurso {

    @Override
    public boolean coincideCon(Recurso otro) {
        return otro.coincideConMadera(this);
    }

    @Override
    public boolean coincideConMadera(Madera madera) {
        return true;
    }

    @Override
    public boolean coincideConLadrillo(Ladrillo ladrillo) { return false; }

    @Override
    public boolean coincideConLana(Lana lana) { return false; }

    @Override
    public boolean coincideConGrano(Grano grano) { return false; }

    @Override
    public boolean coincideConMineral(Mineral m) { return false; }

    @Override
    public String getNombreRecurso() {
        return "Madera";
    }
}
