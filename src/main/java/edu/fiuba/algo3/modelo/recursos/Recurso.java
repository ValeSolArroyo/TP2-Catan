package edu.fiuba.algo3.modelo.recursos;

public interface Recurso {
    boolean coincideCon(Recurso otro);

    boolean coincideConMadera(Madera madera);
    boolean coincideConLadrillo(Ladrillo ladrillo);
    boolean coincideConGrano(Grano grano);
    boolean coincideConLana(Lana lana);
    boolean coincideConMineral(Mineral mineral);

    String getNombreRecurso();
}
