package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.tableroFactory.TableroCatanFactory;
import edu.fiuba.algo3.modelo.tableroFactory.TableroFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //App.main(args);

        List<Jugador> listaJugadores = List.of(
                new Jugador(1, "pepe"),
                new Jugador(2, "lucia"),
                new Jugador(3, "juan"),
                new Jugador(4, "miguel")
        );

        TableroFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();

        Juego juego = new Juego(listaJugadores, tablero);

    }

}
