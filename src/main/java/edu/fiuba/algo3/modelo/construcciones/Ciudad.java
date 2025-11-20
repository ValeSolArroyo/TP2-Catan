package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.AsentamientoExistenteError;
import edu.fiuba.algo3.modelo.recursos.Recurso;

import java.util.Set;

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

    @Override
    public void registrarPropietarioEn(Set<Jugador> jugadores) {
        jugadores.add(propietario);
    }

    @Override
    public void validarLugarLibre() {
        throw new AsentamientoExistenteError("El lugar ya está ocupado por una construcción.");
    }

    @Override
    public boolean esPropiedadDe(Jugador jugador) {
        return this.propietario.equals(jugador);
    }
}