package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

import java.util.Set;

public class Poblado implements Construccion {
    private final Jugador propietario;

    public Poblado(Jugador propietario) {
        this.propietario = propietario;
    }

    @Override
    public void producir(Recurso recurso) {
        recurso.asignarA(propietario);
    }

    @Override
    public void registrarPropietarioEn(Set<Jugador> jugadores) {
        jugadores.add(propietario);
    }

    @Override
    public void validarLugarLibre() {
        throw new RuntimeException("El lugar ya está ocupado por una construcción."); //TODO: excepcion
    }

}