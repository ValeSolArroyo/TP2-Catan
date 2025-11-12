package edu.fiuba.algo3.modelo.excepciones;

public class VerticeInexistenteError extends RuntimeException {
    public VerticeInexistenteError(String message) {
        super(message);
    }
}
