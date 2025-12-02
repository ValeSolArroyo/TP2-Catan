package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.cartasBonificacion.GranCaballeria;
import edu.fiuba.algo3.modelo.cartasBonificacion.GranRutaComercial;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.comercio.ComercioJugador;
import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
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

    public void ejecutarComercioPuerto(Vertice verticePuerto, List<Recurso> recursosAEntregar, Recurso recursoDeseado) {
        Jugador jugador = this.jugadorActual();
        jugador.comerciarConPuerto(verticePuerto, recursosAEntregar, recursoDeseado);
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

    public void lanzarDados() {
        int resultado = dado.lanzarDados();
        if (resultado != 7) {
            this.producirRecursos(resultado);
        } else {
            this.descartePorLadron();
        }
    }

    public void descartePorLadron() {
        for (Jugador jugador : listaJugadores){
            jugador.descartar();
        }
    }

    public void ejecutarAccion(Accion accion){
        accion.ejecutar();
    }

    public void ejecutarCartaDesarrollo(CartaDesarrollo carta, Jugador victima, Hexagono nuevoLugar,
                                        List<Arista> carreterasAConstruir, List<Recurso> recursosDeBanca,
                                        Recurso recursoDeseado) {
        Jugador jugador = jugadorActual();
        carta.ejecutar(this, victima, jugador, nuevoLugar, carreterasAConstruir, recursosDeBanca, recursoDeseado);
    }

    // Comercio con banca e interno
    public void ejecutarComercioJugador(ComercioJugador comercioJugador) {
        Jugador jugador = jugadorActual();
        comercioJugador.ejecutar(jugador);
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

    public void entregarAJugador(Recurso recursoDeseado) {
        Jugador jugador = jugadorActual();
        for (Jugador jugadorQueEntrega: listaJugadores) {
            jugadorQueEntrega.entregarRecursos(List.of(recursoDeseado));
            jugador.recibirRecurso(recursoDeseado);
        }
    }

    public void evaluarPVJugadorActual() {
        Jugador jugador = jugadorActual();
        jugador.evaluarSiEsGanador();
    }

    public List<Jugador> getJugadores() {
        return listaJugadores;
    }

    public Tablero getTablero() { return tablero; }

    public Dado getDado() {
        return this.dado;
    }
}
