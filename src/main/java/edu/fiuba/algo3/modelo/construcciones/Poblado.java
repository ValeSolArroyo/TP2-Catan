package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

public class Poblado extends Construccion {
    public Poblado(Jugador propietario) {
        super(propietario);
    }

    @Override
    public int obtenerPuntosVictoria() {
        return 1;
    }

    @Override
    public int recursosProducidos() {
        return 1;
    }

    @Override
    public void producir(Jugador jugador, Recurso recurso) {
         jugador.agregarRecursos(recurso, 1);// produce 1
    }
}