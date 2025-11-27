package edu.fiuba.algo3.modelo.cartasBonificacion;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public class GranCaballeria implements CartaBonificacion {
    private Jugador dueño;
    private int mayorEjercito;
    private boolean yaOtorgada;

    public GranCaballeria(){
        this.mayorEjercito =  3;
        this.yaOtorgada = false;
    }


    @Override
    public void evaluarCartaBonificacion(Jugador jugador) {
        int cantidad=  jugador.conseguirCartasCaballeroJugadas();
        if ( (mayorEjercito == 3) && (cantidad == 3) && (!yaOtorgada)){
            this.dueño = jugador;
            this.yaOtorgada = true;
            jugador.recibirCartaBonificacion(this);

        }else if (cantidad >this.mayorEjercito){
            this.mayorEjercito = cantidad;
            dueño.perderCartaBonificacion(this);
            jugador.recibirCartaBonificacion(this);
            this.dueño = jugador;

        }

    }
}
