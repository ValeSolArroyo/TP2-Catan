package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.excepciones.YaHayCarreteraError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Ladrillo;
import edu.fiuba.algo3.modelo.recursos.Madera;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;

import java.util.List;
import java.util.Set;

public class Carretera implements Construccion {
    private final Jugador propietario;

    public Carretera(Jugador propietario) {
        this.propietario = propietario;
    }

    @Override
    public void producir(Recurso recurso) {
        // No produce
    }

    @Override
    public void registrarPropietarioEn(Set<Jugador> jugadores) {
        // No registra
    }

    @Override
    public boolean esPropiedadDe(Jugador jugador) {
        return this.propietario.equals(jugador);
    }

    @Override
    public void validarEn(EspacioConstruible espacio, Jugador jugador) {
        espacio.validarCarretera(jugador);
    }

    @Override
    public List<Recurso> cobrar() {
        return List.of(new Madera(), new Ladrillo());
    }

    @Override
    public void ocupar() {
        throw new YaHayCarreteraError("No se puede colocar");
    }
}
