package edu.fiuba.algo3.modelo.juego;
import java.util.Random;
import edu.fiuba.algo3.modelo.observer.Observable;

public class Dado extends Observable {
    private final Random random;
    private int valor1;
    private int valor2;

    public Dado() {
        this.random = new Random(System.nanoTime());
    }

    public int lanzarDados() {
        this.valor1 = random.nextInt(6) + 1;
        this.valor2 = random.nextInt(6) + 1;
        notificarObservadores();
        return valor1 + valor2;
    }

    public int obtenerDado1() {
        return valor1;
    }

    public int obtenerDado2() {
        return valor2;
    }
}


