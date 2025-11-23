package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comercio.Banca;
import edu.fiuba.algo3.modelo.juegoState.EstadoPrimeraColocacion;
import edu.fiuba.algo3.modelo.juegoState.EstadoJuego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import java.util.List;

public class Juego {
    private final List<Jugador> listaJugadores;
    private Tablero tablero;
    private int indiceTurno;
    private final Dado dado;
    private EstadoJuego estadoActual;
    private final Banca banca;


    public Juego(List<Jugador> jugadores, Tablero tablero) {
        if (jugadores.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un jugador.");
        }
        this.listaJugadores = jugadores;
        this.dado = new Dado();
        this.tablero = tablero;
        this.indiceTurno = 0;
        this.estadoActual = new EstadoPrimeraColocacion();
        this.banca = new Banca();
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

    public void construirPoblado(Vertice vertice, Jugador jugador) {
        estadoActual.construirPoblado(this, vertice);
    }

    public void construirCiudad(Vertice vertice, Jugador jugador ) {
        estadoActual.construirCiudad(this, vertice);
    }

    public void construirCarretera(Arista arista, Jugador jugador) {
        estadoActual.construirCarretera(this, arista);
    }

    public void producirRecursos(int numero) {
        tablero.producir(numero);
    }

    public void darRecursosIniciales(Jugador jugador, Vertice vertice){
        tablero.darRecursosIniciales(jugador, vertice);
    }

    public boolean todosColocaronPrimerPoblado() {
        for (Jugador jugador : listaJugadores) {
            if (!jugador.primeraColocacion()) return false;
        }
        return true;
    }

    public boolean todosTerminaronColocacionesIniciales() {
        for (Jugador jugador : listaJugadores) {
            if (!jugador.segundaColocacion()) {
                return false;
            }
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
