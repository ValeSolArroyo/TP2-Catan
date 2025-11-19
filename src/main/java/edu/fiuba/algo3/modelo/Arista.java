package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;

import java.util.Collections;

public class Arista {

    private final Vertice vertice1;
    private final Vertice vertice2;
    private Construccion construccion;

    public Arista(Vertice vertice1, Vertice vertice2) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.construccion = new NullConstruccion();

        vertice1.agregarArista(this);
        vertice2.agregarArista(this);
    }

    public void construirCarretera(Jugador jugador) {
        try {
            construccion.validarLugarLibre();
        } catch (RuntimeException e) {
            throw new RuntimeException("Ya hay una carretera en este camino.");
        }

        // 2. Validar conexión (alguno de los extremos debe conectarme)


        this.construccion = new Carretera(jugador);
    }


}
