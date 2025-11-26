package edu.fiuba.algo3.modelo.juego;
import java.util.Random;

public class Dado {
    private final Random random;
    private int valor1;
    private int valor2;

    public Dado() {
        this.random = new Random(System.nanoTime());
    }

    public int lanzarDados() {
        valor1 = random.nextInt(6) + 1;
        valor2 = random.nextInt(6) + 1;
        return valor1 + valor2;
    }
}


