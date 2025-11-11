package edu.fiuba.algo3.modelo;
import java.util.Random;


public class Dado {
    private final Random random = new Random();
    private int valor1;
    private int valor2;

    public int lanzar() {
        valor1 = random.nextInt(6) + 1;
        valor2 = random.nextInt(6) + 1;
        return valor1 + valor2;
    }
}

