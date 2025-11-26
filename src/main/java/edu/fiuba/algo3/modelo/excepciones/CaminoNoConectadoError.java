package edu.fiuba.algo3.modelo.excepciones;

public class CaminoNoConectadoError extends RuntimeException {
    public CaminoNoConectadoError(String message) {
        super(message);
    }
}
