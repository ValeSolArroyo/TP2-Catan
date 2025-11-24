package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.excepciones.YaHayPobladoError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Ladrillo;
import edu.fiuba.algo3.modelo.recursos.Lana;
import edu.fiuba.algo3.modelo.recursos.Madera;
import edu.fiuba.algo3.modelo.recursos.Grano;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;

import java.util.List;
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
    public boolean esPropiedadDe(Jugador jugador) {
        return this.propietario.equals(jugador);
    }

    @Override
    public List<Recurso> costoConstruccion() {
        return List.of(new Madera(), new Ladrillo(), new Lana(), new Grano());
    }

    @Override
    public void validarEn(EspacioConstruible espacio, Jugador jugador) {
        espacio.validarPoblado(jugador);
    }

    @Override
    public void construir() {
        throw new YaHayPobladoError("No se puede colocar");
    }
}