package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.cartasBonificacion.GranCaballeria;
import edu.fiuba.algo3.modelo.cartasBonificacion.GranRutaComercial;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.juegoState.EstadoPrimeraColocacion;
import edu.fiuba.algo3.modelo.juegoState.EstadoJuego;
import edu.fiuba.algo3.modelo.juegoState.EstadoSegundaColocacion;
import edu.fiuba.algo3.modelo.juegoState.EstadoTirarDados;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.*;

import java.util.List;

public class Juego {
    private final List<Jugador> listaJugadores;
    private Tablero tablero;
    private int indiceTurno;
    private final Dado dado;
    private EstadoJuego estadoActual;
    private List<CartaDesarrollo> cartasDesarrollo;
    private GranCaballeria granCaballeria;
    private GranRutaComercial granRutaComercial;

    public Juego(List<Jugador> jugadores, Tablero tablero, List<CartaDesarrollo> cartasDesarrollo) {
        if (jugadores.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un jugador.");
        }
        this.listaJugadores = jugadores;
        this.dado = new Dado();
        this.tablero = tablero;
        this.indiceTurno = 0;
        this.estadoActual = new EstadoPrimeraColocacion();
        this.cartasDesarrollo = cartasDesarrollo;
        this.granCaballeria = new GranCaballeria();
        this.granRutaComercial = new GranRutaComercial();

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

    public void comprarCartaDesarrollo(Jugador jugador){
        estadoActual.comprarCartaDesarrollo(this, cartasDesarrollo);
    }

    public void jugarCartaDesarrollo(Juego juego, CartaDesarrollo cartaDesarrollo, Jugador victima, List<Arista> carreterasAConstruir, List<Recurso> recursosDeBanca, Recurso recursoAnunciado, List<Jugador> jugadores, Hexagono nuevoLugarLadron){
        estadoActual.jugarCartaDesarrollo(this, cartaDesarrollo, victima, carreterasAConstruir, recursosDeBanca, recursoAnunciado, listaJugadores, nuevoLugarLadron);
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

    public void revisarGranCaballeria(Jugador jugador) {
        granCaballeria.evaluarCartaBonificacion(jugador);
    }

    public void revisarGranRutaComercial(Jugador jugador) {
        granRutaComercial.evaluarCartaBonificacion(jugador);
    }

}
