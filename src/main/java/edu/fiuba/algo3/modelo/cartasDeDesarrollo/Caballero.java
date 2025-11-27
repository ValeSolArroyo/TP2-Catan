package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;

import java.util.List;

public class Caballero implements CartaDesarrollo {

    @Override
    public void aplicarEfecto(Juego juego, Jugador jugador, Jugador victima, List<Arista> carreterasAContruir, List<Recurso> recursosDeBanca, Recurso recursoAnunciado, List<Jugador> jugadores, Hexagono nuevoLugarLadron) {
        // Que llame al metodo moverLadron() y le pasa el hexagono de nuevoLugarLadron.
        // A partir de eso, moverLadron devuelve una lista de afectados. Por lo que usamos el jugadorVictima
        // que se le pasa por parametro en este metodo. Ahí hacemos robarCartaDe() y ponemos a víctima

        // Al aplicar efecto, tenerun metodo que llame a jugador qeu se llame sumerCaballero o algo asi
        // y qu este le sume uno al contador.
        // si tiene 3 o +, tiene la de GranCaballeria.


        //Juego va a tener dos instyancias como atributos: la de gran bcaballeria y la de la ruta.
        //En este método se aplican efectos y eso y despues que se llame a juego y que éste se encargue
        // de acutualizar quien es el dueño de la carta de bonificacion.

        // en este metodo se ve quien tiene mayor cantidad de cartas de caballeria JUGADAS y a  partir de
        //eso juego decide si el jugador que ACABA de jugar la carta pasa a ser el nuevo dueño o si el
        // anterir sigue siendo el dueño.



        jugador.registrarCaballeroJugado();
        juego.revisarGranCaballeria(jugador);
    }

    @Override
    public int puntosVictoria() {
        return 0;
    }
}
