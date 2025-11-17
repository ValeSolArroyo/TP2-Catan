package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.recursos.Recurso;

public class NullConstruccion implements Construccion {

    @Override
    public void producir(Recurso recurso) {
        // No hay construcción, no produce nada
    }
}
