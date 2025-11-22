package edu.fiuba.algo3.modelo.juego;
import java.util.Random;

public class Dado {
    private final Random random;
    private int valor1;
    private int valor2;

    // CONSTRUCTOR CON SEMILLA 
    public Dado() {
        this.random = new Random(System.nanoTime());
    }

    public int lanzar() {
        valor1 = random.nextInt(6) + 1;
        valor2 = random.nextInt(6) + 1;
        return valor1 + valor2;
    }
}


