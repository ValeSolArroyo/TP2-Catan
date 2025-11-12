package edu.fiuba.algo3.modelo;
import java.util.Random;

public class Dado {
    // ATRIBUTOS DE CLASE

    // ATRIBUTOS
    private final Random random = new Random();
    private int valor1;
    private int valor2;

    // CONSTRUCTORES

    // MÉTODOS DE CLASE

    // MÉTODOS GENERALES

    // MÉTODOS DE COMPORTAMIENTO
    public int lanzar() {
        valor1 = random.nextInt(6) + 1;
        valor2 = random.nextInt(6) + 1;
        return valor1 + valor2;
    }

    // GETTERS

    // SETTERS
}

