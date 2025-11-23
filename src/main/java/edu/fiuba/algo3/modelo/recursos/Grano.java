package edu.fiuba.algo3.modelo.recursos;

import edu.fiuba.algo3.modelo.jugador.Inventario;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public class Grano implements Recurso {
    private final String tipo = "Grano";

    @Override
    public void asignarA(Jugador jugador) {
        jugador.recibirRecurso(this);
    }

    @Override
    public void eliminarDe(Inventario inventario) {
        inventario.eliminarRecurso(tipo);
    }

    @Override
    public boolean brinda(String recurso) {
        return recurso.equals(tipo);
    }
}