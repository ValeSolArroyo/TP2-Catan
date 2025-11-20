package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;

import java.util.Collections;

public class Arista {

    private final Vertice vertice1;
    private final Vertice vertice2;
    private Construccion construccion;
    private final int id;

    public Arista(int id, Vertice vertice1, Vertice vertice2) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.id = id;
        this.construccion = new NullConstruccion();

        vertice1.agregarArista(this);
        vertice2.agregarArista(this);
    }

    public int obtenerId() {
        return this.id;
    }

    public void asignarConstruccion(Construccion construccion) {
        this.construccion = construccion;
    }


}
