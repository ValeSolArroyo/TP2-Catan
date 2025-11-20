package edu.fiuba.algo3.modelo.comercio;

import edu.fiuba.algo3.modelo.Jugador;
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

    public void ejecutar() {
        // 1. Cobrar al proponente
        try {
            proponente.gastar(oferta, 1);
        } catch (RecursosInsuficientesError e) {
            throw new RecursosInsuficientesError("El proponente no tiene el recurso para el intercambio."); //TODO: excepcion
        }

        // 2. Cobrar al aceptante
        try {
            aceptante.gastar(demanda, 1);
        } catch (RecursosInsuficientesError e) {
            // Devuelve lo cobrado al proponente
            proponente.recibir(oferta);
            throw new RecursosInsuficientesError("El aceptante no tiene el recurso para el intercambio."); //TODO: excepcion
        }

        aceptante.recibir(oferta);
        proponente.recibir(demanda);
    }
}