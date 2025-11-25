package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.excepciones.YaHayCiudadError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Grano;
import edu.fiuba.algo3.modelo.recursos.Mineral;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;

import java.util.List;
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
    public boolean tieneDePropietarioA(Jugador jugador) {
        return this.propietario.equals(jugador);
    }

    @Override
    public List<Recurso> cobrar() {
        return List.of(new Grano(), new Grano(), new Mineral(), new Mineral(), new Mineral());
    }

    @Override
    public void validarEn(EspacioConstruible espacio, Jugador jugador) {
        espacio.validarCiudad(jugador);
    }


    @Override
    public void ocupar() {
        throw new YaHayCiudadError("No se puede colocar");
    }
}