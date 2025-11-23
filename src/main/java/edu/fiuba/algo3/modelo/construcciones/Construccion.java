package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;

import java.util.Set;

public interface Construccion {
    void producir(Recurso recurso);
    void registrarPropietarioEn(Set<Jugador> jugadores);
    void validarLugarLibre();
    boolean esPropiedadDe(Jugador jugador);
}
