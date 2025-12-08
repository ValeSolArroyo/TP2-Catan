package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.jugador.Inventario;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;
import java.util.List;

public class NullConstruccion implements Construccion {

    @Override
    public void tieneDePropietarioA(Jugador jugador) {
    }

    @Override
    public void cobrar(Inventario inventario) {
        inventario.consumirRecurso(List.of());
    }

    @Override
    public void producir(Recurso recurso){}

    @Override
    public void ocupar() {}

    @Override
    public int puntosVictoria() {
        return 0;
    }

    @Override
    public void aplicarCambio(Jugador jugador, EspacioConstruible espacio) {}

    @Override
    public int getIdPropietario() {
        return -1;
    }
}