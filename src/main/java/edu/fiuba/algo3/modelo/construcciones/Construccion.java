package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;

import java.util.List;
import java.util.Set;

public interface Construccion {
    void validarEn(EspacioConstruible espacio, Jugador jugador);
    void producir(Recurso recurso);
    List<Recurso> cobrar();
    void registrarPropietarioEn(Set<Jugador> jugadores);
    boolean esPropiedadDe(Jugador jugador);
    void ocupar();
}
