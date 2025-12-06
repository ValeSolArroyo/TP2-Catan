package edu.fiuba.algo3.modelo.cartasBonificacion;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class GranRutaComercial implements CartaBonificacion{

    private Jugador dueño;
    private int mayorRutaComercial;
    private boolean yaOtorgada;

    public GranRutaComercial(){
        this.mayorRutaComercial = 5;
        this.yaOtorgada = false;
    }

    @Override
    public void evaluarCartaBonificacion(Jugador jugador, Tablero tablero) {
        int cantidad=  tablero.conseguirRutaMasLarga(jugador);
        if ((mayorRutaComercial == 5) && (cantidad >= 5)  && (!yaOtorgada)){
            this.dueño = jugador;
            this.yaOtorgada = true;
            jugador.recibirCartaBonificacion(this);

        } else if (cantidad >this.mayorRutaComercial) {
            this.mayorRutaComercial = cantidad;
            dueño.perderCartaBonificacion(this);
            jugador.recibirCartaBonificacion(this);
            this.dueño = jugador;
        }
    }
}

