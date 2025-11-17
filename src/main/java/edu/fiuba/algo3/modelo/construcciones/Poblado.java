package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

public class Poblado implements Construccion {
    private final Jugador propietario;

    public Poblado(Jugador propietario) {
        this.propietario = propietario;
    }

    @Override
    public void producir(Recurso recurso) {
        recurso.asignarA(propietario);
    }
}