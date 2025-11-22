package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comercio.Banca;
import edu.fiuba.algo3.modelo.comercio.Intercambio;
import edu.fiuba.algo3.modelo.juegoState.EstadoPrimeraColocacion;
import edu.fiuba.algo3.modelo.juegoState.EstadoJuego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // ESTADO Y ACCIONES

    public void setEstado(EstadoJuego estado) {
        this.estadoActual = estado;
    }

    public int lanzarDados() {
        return estadoActual.lanzarDados(this);
    }

    public void verificarDescartesPorLadron() {
        estadoActual.verificarDescartesPorLadron(this);
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

      public void darRecursosInicialesInterno(Vertice vertice) {
        Jugador jugador = jugadorActual();
        tablero.darRecursosIniciales(jugador, vertice);
    }

    public int lanzarDadosInterno() {
        return dado.lanzar();
    }

     public List<Jugador> moverLadronInterno(Hexagono hexagono) {
        hexagono.ponerLadron();
        Set<Jugador> listaAfectados = new HashSet<>();
        hexagono.registrarPropietariosEn(listaAfectados);
        listaAfectados.remove(jugadorActual());
        return new ArrayList<>(listaAfectados);
    }

    public void robarCartaDeInterno(Jugador victima) {
        jugadorActual().robarCarta(victima);
    }

    public void producirRecursos(int numero) {
        tablero.producir(numero);
    }

    // CONSTRUCCIONES Y COLOCACIONES INICIALES

    public void construirPoblado(Vertice vertice) {
        estadoActual.construirPoblado(this, vertice);
    }

    
    public void construirCiudad(Vertice vertice) {
        estadoActual.construirCiudad(this, vertice);
    }

    public void construirCarretera(Arista arista) {
        estadoActual.construirCarretera(this, arista);
    }


    public void colocarPobladoInicialInterno(Vertice vertice) {
        Jugador jugador = jugadorActual();
        jugador.construirPobladoInicialEn(vertice);
    }


    public void construirPobladoInterno(Vertice vertice) {
        Jugador jugador = jugadorActual();
        jugador.construirPobladoInicialEn(vertice);
    }

    public void construirCarreteraInicialInterno(Arista arista) {
        Jugador jugador = jugadorActual();
        jugador.construirCarreteraInicialEn(arista);
    }


    // VALIDACIONES

    public boolean todosColocaronPrimerPoblado() {
        for (Jugador jugador : listaJugadores) {
            if (!jugador.yaColocoPrimeraConstruccion()) return false;
        }
        return true;
    }

    public boolean todosTerminaronColocacionesIniciales() {
        for (Jugador jugador : listaJugadores) {
            if (!jugador.yaColocoConstruccionesInicialesCompletas()) {
                return false;
            }
        }
        return true;
    }

    public void verificarDescartesPorLadronInterno() {
        for (Jugador jugador : listaJugadores) {
            jugador.descartarSiExcedeLimiteDeCartas();
        }
    }


    // TURNO 
    public Jugador jugadorActual() {
        return listaJugadores.get(indiceTurno);
    }

    public void pasarAlSiguienteJugador() {
        indiceTurno = (indiceTurno + 1) % listaJugadores.size();
    }

    public void pasarAlJugadorAnterior() {
        indiceTurno = (indiceTurno - 1 + listaJugadores.size()) % listaJugadores.size();
    }


    // COMERCIO

    public void comerciarConBanco(Recurso entregado, Recurso recibido) {
        // El estado valida si es el turno y momento correcto (ej. no tirar dados)
        estadoActual.comerciarConBanco(this, entregado, recibido);
    }

    public void intercambiar(Jugador otroJugador, Recurso entregado, Recurso recibido) {
        estadoActual.intercambiar(this, otroJugador, entregado, recibido);
    }

    public void comerciarConBancoInterno(Recurso entregado, Recurso recibido) {
        banca.comerciar(jugadorActual(), entregado, recibido);
    }

    public void intercambiarInterno(Jugador otroJugador, Recurso entregado, Recurso recibido) {
        Intercambio intercambio = new Intercambio(jugadorActual(), otroJugador, entregado, recibido);
        intercambio.ejecutar();
    }
}
