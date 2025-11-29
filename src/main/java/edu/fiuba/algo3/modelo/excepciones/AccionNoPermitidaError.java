package edu.fiuba.algo3.modelo.excepciones;

public class AccionNoPermitidaError extends RuntimeException {
    public AccionNoPermitidaError(String message) {
        super(message);
    }
}
