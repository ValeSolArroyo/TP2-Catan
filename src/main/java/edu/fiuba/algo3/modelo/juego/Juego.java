package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comercio.Banca;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.juegoState.EstadoPrimeraColocacion;
import edu.fiuba.algo3.modelo.juegoState.EstadoJuego;
import edu.fiuba.algo3.modelo.juegoState.EstadoSegundaColocacion;
import edu.fiuba.algo3.modelo.juegoState.EstadoTirarDados;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.*;

import java.util.List;

public class Juego {
    private final List<Jugador> listaJugadores;
    private Tablero tablero;
    private int indiceTurno;
    private final Dado dado;
    private EstadoJuego estadoActual;


    public Juego(List<Jugador> jugadores, Tablero tablero) {
        if (jugadores.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un jugador.");
        }
        this.listaJugadores = jugadores;
        this.dado = new Dado();
        this.tablero = tablero;
        this.indiceTurno = 0;
        this.estadoActual = new EstadoPrimeraColocacion();
    }

    public void establecerEstado(EstadoJuego estado) {
        this.estadoActual = estado;
    }

    public void colocarPobladoInicial(Juego juego, Vertice vertice, Arista arista) {
     estadoActual.colocarPobladoInicial(this, vertice, arista);
    }

    public int lanzarDados() {
        return estadoActual.lanzarDados(this, dado);
    }

    public void descartePorLadron() {
        estadoActual.descartePorLadron(this, listaJugadores);
    }

    public List<Jugador> moverLadron(Hexagono hexagono) {
        return estadoActual.moverLadron(this, hexagono);
    }

    public void robarCartaDe(Jugador victima) {
        estadoActual.robarCartaDe(this, victima);
    }

    public void finalizarTurno() {
        estadoActual.finalizarTurno(this);
    }

    public void construir(Construccion construccion, EspacioConstruible espacio) {
        estadoActual.construir(this, construccion, espacio);
    }

    public void producirRecursos(int numero) {
        tablero.producir(numero);
    }

    public void darRecursosIniciales(Vertice vertice){
        tablero.darRecursosIniciales(vertice);
    }

    public void primeraColocacionRealizada(){
        if (todosColocaronPrimerPoblado()) {
            establecerEstado(new EstadoSegundaColocacion());
        } else {
            avanzarTurno();
        }
    }

    public void segundaColocacionRealizada(){
        if (todosTerminaronColocacionesIniciales()) {
            establecerEstado(new EstadoTirarDados());
        } else {
            retrocederTurno();
        }
    }

    public boolean todosColocaronPrimerPoblado() {
        for (Jugador jugador : listaJugadores) {
            if (!jugador.primeraColocacion()) return false;
        }
        return true;
    }

    public boolean todosTerminaronColocacionesIniciales() {
        for (Jugador jugador : listaJugadores) {
            if (!jugador.segundaColocacion()) return false;
        }
        return true;
    }


    public Jugador jugadorActual() {
        return listaJugadores.get(indiceTurno);
    }

    public void avanzarTurno() {
        indiceTurno = (indiceTurno + 1) % listaJugadores.size();
    }

    public void retrocederTurno() {
        indiceTurno = (indiceTurno - 1 + listaJugadores.size()) % listaJugadores.size();
    }

}
