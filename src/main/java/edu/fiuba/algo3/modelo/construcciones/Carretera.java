package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

public class Carretera implements Construccion {
    private Jugador propietario;

    public Carretera(Jugador propietario) {
        this.propietario = propietario;
    }

    @Override
    public void producir(Recurso recurso) {
        propietario.agregarRecurso(recurso.nombre(), 1);
    }
}
