package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.excepciones.AsentamientoExistenteError;
import edu.fiuba.algo3.modelo.excepciones.CaminoNoConectadoError;

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

    public void validarConstruccionCarretera(Jugador jugador) {
        if (!(construccion instanceof NullConstruccion)) {
            throw new AsentamientoExistenteError("No se puede construir, Carretera ya existente");
        }

        // Debe estar conectado a una construcción propia
        boolean conectado =
                vertice1.tieneConstruccionPropia(jugador) || vertice2.tieneConstruccionPropia(jugador);

        if (!conectado) {
            throw new CaminoNoConectadoError("La carretera debe estar conectada a otra construcción propia.");
        }
    }


    public int obtenerId() {
        return this.id;
    }

    public void asignarConstruccion(Construccion construccion) {
        this.construccion = construccion;
    }


}
