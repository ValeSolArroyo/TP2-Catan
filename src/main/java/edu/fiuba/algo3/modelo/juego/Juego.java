package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.cartasBonificacion.GranCaballeria;
import edu.fiuba.algo3.modelo.cartasBonificacion.GranRutaComercial;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Grano;
import edu.fiuba.algo3.modelo.recursos.Mineral;
import edu.fiuba.algo3.modelo.recursos.Lana;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.*;

import java.util.List;

public class Juego {
    private final List<Jugador> listaJugadores;
    private Tablero tablero;
    private int indiceTurno;
    private final Dado dado;
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
        this.cartasDesarrollo = cartasDesarrollo;
        this.granCaballeria = new GranCaballeria();
        this.granRutaComercial = new GranRutaComercial();

    }

    public void construir(Construccion construccion, EspacioConstruible espacio) {
        Jugador jugador = this.jugadorActual();
        jugador.construir(construccion, espacio);
    }

    public void robarCartaDe(Jugador victima) {
        Jugador ladron = this.jugadorActual();
        ladron.robarCarta(victima);
    }

    public void moverLadron(Hexagono nuevoLugar){
        this.tablero.moverLadronA(nuevoLugar);
    }

    public void comprarCartaDesarrollo(){
        List<Recurso> costoCarta = List.of(new Mineral(), new Grano(), new Lana());
        Jugador jugador = this.jugadorActual();
        jugador.guardarCartaDesarrollo(this.cartasDesarrollo.get(0), costoCarta);
        cartasDesarrollo.remove(0);
    }

    public void comerciar() {
        // TODO
    }

    public void jugarCartaDesarrollo(CartaDesarrollo cartaDesarrollo){
        // TODO
    }

    public void colocarPrimerPoblado(Vertice vertice, Arista arista) {
        Jugador jugador = this.jugadorActual();

        jugador.construir(new Poblado(jugador), vertice);
        jugador.construir(new Carretera(jugador), arista);

        if (indiceTurno < listaJugadores.size() - 1) {
            this.avanzarTurno();
        }
    }

    public void colocarSegundoPoblado(Vertice vertice, Arista arista) {
        Jugador jugador = this.jugadorActual();

        jugador.construir(new Poblado(jugador), vertice);
        jugador.construir(new Carretera(jugador), arista);

        this.darRecursosIniciales(vertice);

        if (indiceTurno > 0) {
            this.retrocederTurno();
        }
    }

    public int lanzarDados() {
        int resultado = dado.lanzarDados();
        if (resultado != 7) {
            this.producirRecursos(resultado);
        } else {
            this.descartePorLadron();
        }
        return resultado;
    }

    public void descartePorLadron() {
        for (Jugador jugador : listaJugadores){
            jugador.descartar();
        }
    }


    public void producirRecursos(int numero) {
        tablero.producir(numero);
    }

    public void darRecursosIniciales(Vertice vertice){
        tablero.darRecursosIniciales(vertice);
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
