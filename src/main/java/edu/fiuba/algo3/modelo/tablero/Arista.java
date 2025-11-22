package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.excepciones.AsentamientoExistenteError;
import edu.fiuba.algo3.modelo.excepciones.CaminoNoConectadoError;
import edu.fiuba.algo3.modelo.jugador.Jugador;

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

    // COMPORTAMIENTO 
    public void asignarConstruccion(Construccion construccion) {
        this.construccion = construccion;
    }

    // VALIDACIONES 

    public boolean tieneCarreteraPropia(Jugador jugador) {
        if (!(this.construccion instanceof Carretera)) {
            return false;
        }
        return this.construccion.esPropiedadDe(jugador);
    }

    public void validarConstruccionCarretera(Jugador jugador) {
        if (!(construccion instanceof NullConstruccion)) {
            throw new AsentamientoExistenteError("No se puede construir, Carretera ya existente");
        }
        boolean conectadoAAsentamiento =
        vertice1.tieneConstruccionPropia(jugador) || vertice2.tieneConstruccionPropia(jugador);
    
        boolean conectadoACarretera =
        vertice1.tieneCarreteraPropiaAdyacente(jugador) || vertice2.tieneCarreteraPropiaAdyacente(jugador);
    
        if (!conectadoAAsentamiento && !conectadoACarretera) {
            throw new CaminoNoConectadoError("La carretera debe estar conectada a otra construcción propia.");
        }
    }


    public int obtenerId() {
        return this.id;
    }


}
