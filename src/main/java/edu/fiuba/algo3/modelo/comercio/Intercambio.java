package edu.fiuba.algo3.modelo.comercio;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.recursos.Recurso;

public class Intercambio {
    private final Jugador proponente;
    private final Jugador aceptante;
    private final Recurso oferta;
    private final Recurso demanda;

    public Intercambio(Jugador proponente, Jugador aceptante, Recurso oferta, Recurso demanda) {
        this.proponente = proponente;
        this.aceptante = aceptante;
        this.oferta = oferta;
        this.demanda = demanda;
    }
}