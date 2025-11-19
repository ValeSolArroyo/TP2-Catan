package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

import java.util.Set;

public class Carretera implements Construccion {
    private final Jugador propietario;

    public Carretera(Jugador propietario) {
        this.propietario = propietario;
    }

    @Override
    public void producir(Recurso recurso) {
        // no produce nada
    }

    @Override
    public void registrarPropietarioEn(Set<Jugador> jugadores) {
        // No deberia registrarse
    }

    @Override
    public void validarLugarLibre() {
        throw new RuntimeException("El lugar ya está ocupado por una construcción."); //TODO: excepcion
    }
}
