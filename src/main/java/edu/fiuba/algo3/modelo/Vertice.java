package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import  java.util.List;

import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.AsentamientoExistenteError;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;


public class Vertice {
    private Construccion construccion;

    public Vertice() {
        this.construccion = new NullConstruccion();
    }
    public void construirPoblado(Jugador jugador) {
        // Validaciones de distancia y recursos van acá
        this.construccion = new Poblado(jugador);
    }

    public void construirCiudad(Jugador jugador) {
        // Validaciones
        this.construccion = new Ciudad(jugador);
    }

    //!!!!!!!!!!!! REVISAR !!!!!!!!!!!!!!!!!
    public Construccion obtenerConstruccion() {
        return construccion;
    }
}