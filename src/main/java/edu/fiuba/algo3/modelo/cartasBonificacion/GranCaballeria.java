package edu.fiuba.algo3.modelo.cartasBonificacion;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class GranCaballeria implements CartaBonificacion {
    private Jugador dueño;
    private int mayorEjercito;
    private boolean yaOtorgada;

    public GranCaballeria(){
        this.mayorEjercito =  3;
        this.yaOtorgada = false;
    }

    @Override
    public boolean evaluarCartaBonificacion(Jugador jugador, Tablero tablero) {
        int cantidad = jugador.conseguirCartasCaballeroJugadas();
        if ((mayorEjercito == 3) && (cantidad == 3) && (!yaOtorgada)) {
            this.dueño = jugador;
            this.yaOtorgada = true;
            jugador.recibirCartaBonificacion(this);
            return true;
        } else if (cantidad > this.mayorEjercito){
            if (this.dueño != jugador){
                dueño.perderCartaBonificacion(this);
                jugador.recibirCartaBonificacion(this);
                this.dueño = jugador;
                return true;
            }
            this.mayorEjercito = cantidad;
            return false;
        }
        return false;
    }
}
