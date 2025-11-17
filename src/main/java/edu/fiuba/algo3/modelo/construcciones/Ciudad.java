package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

public class Ciudad implements Construccion {
    private final Jugador propietario;

    public Ciudad(Jugador propietario) {
        this.propietario = propietario;
    }

    @Override
    public void producir(Recurso recurso) {
        recurso.asignarA(propietario);
        recurso.asignarA(propietario); // produce doble
    }
}