package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.jugador.Inventario;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;

import java.util.List;
import java.util.Set;

public interface Construccion {
    void validarEn(EspacioConstruible espacio, Jugador jugador);
    void producir(Recurso recurso);
    void cobrar(Inventario inventario);
    void registrarPropietarioEn(Set<Jugador> jugadores);
    boolean tieneDePropietarioA(Jugador jugador);
    void ocupar();
}
