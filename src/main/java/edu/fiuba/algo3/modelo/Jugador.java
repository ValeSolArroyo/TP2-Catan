package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.Map;

public class Jugador {
    private final int id;
    private final String nombre;
    private final Map<String, Integer> recursos = new HashMap<>();

    public Jugador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
