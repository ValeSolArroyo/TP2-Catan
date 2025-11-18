package edu.fiuba.algo3.modelo.excepciones;

public class RecursosInsuficientesError extends RuntimeException {
    public RecursosInsuficientesError(String mensaje) {
        super(mensaje);
    }
}
