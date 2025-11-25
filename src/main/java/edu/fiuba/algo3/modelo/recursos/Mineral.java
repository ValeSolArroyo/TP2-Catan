package edu.fiuba.algo3.modelo.recursos;

import edu.fiuba.algo3.modelo.jugador.Inventario;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public class Mineral implements Recurso {
    private final String tipo = "Mineral";

    @Override
    public void asignarA(Jugador jugador) {
        jugador.recibirRecurso(this);
    }

    public void eliminarDe (Inventario inventario) {
        inventario.eliminarRecurso("Mineral");
    }

    @Override
    public boolean brinda(String recurso) {
        return recurso.equals(tipo);
    }
}