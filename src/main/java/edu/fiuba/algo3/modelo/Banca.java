package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.recursos.Recurso;

public class Banca {
    public void comerciar(Jugador jugador, Recurso entregado, Recurso recibido) {
        int costo = jugador.obtenerTasaDeCambioPara(entregado);
        jugador.gastar(entregado, costo);
        jugador.recibir(recibido);
    }
}
