package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

public class Ciudad implements Construccion {
    private Jugador propietario;

    public Ciudad(Jugador propietario) {
        this.propietario = propietario;
    }

    @Override
    public void producir(Jugador jugador, Recurso recurso) {
        recurso.asignarA(jugador);
        recurso.asignarA(jugador); // produce x2
    }
}