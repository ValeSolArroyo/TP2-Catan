package edu.fiuba.algo3.modelo.juegoCommand;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Vertice;

public class AccionSegundaColocacion implements Accion{
    private Juego juego;
    private Vertice vertice;
    private Arista arista;

    public AccionSegundaColocacion(Juego juego, Vertice vertice, Arista arista){
        this.juego = juego;
        this.vertice = vertice;
        this.arista = arista;
    }

    @Override
    public void ejecutar(){
        juego.colocarSegundoPoblado(vertice, arista );
    }
}

