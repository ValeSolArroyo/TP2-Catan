package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;

import java.util.List;
import java.util.Set;

public interface Construccion {
    void validarEn(EspacioConstruible espacio, Jugador jugador);
    // TODO: cambiar nombre (a construir)
    void construir();
    void producir(Recurso recurso);
    void registrarPropietarioEn(Set<Jugador> jugadores);
    boolean esPropiedadDe(Jugador jugador);
    List<Recurso> costoConstruccion();
}
