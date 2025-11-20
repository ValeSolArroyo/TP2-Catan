package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.juegoState.EstadoPrimeraColocacion;
import edu.fiuba.algo3.modelo.juegoState.EstadoJuego;
import edu.fiuba.algo3.modelo.recursos.Recurso;

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

    public void setEstado(EstadoJuego estado) {
        this.estadoActual = estado;
    }

    public int lanzarDados() {
        return estadoActual.lanzarDados(this);
    }

    public void verificarDescartesPorLadron() {
        estadoActual.verificarDescartesPorLadron(this);
    }

    public List<Jugador> moverLadron(int hexagonoId) {
        return estadoActual.moverLadron(this, hexagonoId);
    }

    public void robarCartaDe(Jugador victima) {
        estadoActual.robarCartaDe(this, victima);
    }

    public void construirPoblado(int verticeId) {
        estadoActual.construirPoblado(this, verticeId);
    }

    // METODOS PARA MÁS ADELANTE.
    /*public void construirCiudad(int verticeId) {
        estadoActual.construirCiudad(this, verticeId);
    }

    public void construirCarretera(int aristaId) {
        estadoActual.construirCarretera(this, aristaId);
    }*/

    public void finalizarTurno() {
        estadoActual.finalizarTurno(this);
    }


    public Vertice colocarPobladoInicialInterno(Jugador jugador, int verticeId) {
        Vertice vertice = tablero.encontrarVertice(verticeId);
        jugador.construirPobladoInicialEn(vertice);
        return vertice;
    }


    public boolean todosColocaronPrimerPoblado() {
        for (Jugador jugador : listaJugadores) {
            if(jugador.obtenerCantidadDeConstrucciones() < 1) return false;
        }
        return true;
    }

    public void darRecursosInicialesInterno(Jugador jugador, Vertice vertice) {
        tablero.darRecursosIniciales(jugador, vertice);
    }

    public int lanzarDadosInterno() {
        return dado.lanzar();
    }

    public void verificarDescartesPorLadronInterno() {
        for (Jugador jugador : listaJugadores) {
            if (jugador.obtenerCantidadTotalDeRecursos() > 7) {
                jugador.descartarMitadDeRecursos();
            }
        }
    }

    public List<Jugador> moverLadronInterno(int hexagonoId) {
        Hexagono destino = tablero.obtenerHexagono(hexagonoId);
        destino.ponerLadron();

        Set<Jugador> listaAfectados = new HashSet<>();

        destino.registrarPropietariosEn(listaAfectados);

        listaAfectados.remove(jugadorActual());
        return new ArrayList<>(listaAfectados);
    }

    public void robarCartaDeInterno(Jugador victima) {
        jugadorActual().robarCarta(victima);
    }

    public void construirPobladoInterno(int verticeId) {
        Vertice vertice = tablero.encontrarVertice(verticeId);
        Jugador jugador = jugadorActual();
        jugador.construirPobladoInicialEn(vertice);
    }

    public void producirRecursos(int numero) {
        tablero.producir(numero);
    }

    public Jugador jugadorActual() {
        return listaJugadores.get(indiceTurno);
    }

    public void pasarAlSiguienteJugador() {
        indiceTurno = (indiceTurno + 1) % listaJugadores.size();
    }

    public void pasarAlJugadorAnterior() {
        indiceTurno = (indiceTurno - 1 + listaJugadores.size()) % listaJugadores.size();
    }

    public boolean todosTerminaronColocacionesIniciales() {
        for (Jugador jugador : listaJugadores) {
            if (jugador.obtenerCantidadDeConstrucciones() < 2) {
                return false;
            }
        }
        return true;
    }

    public void construirCarreteraInicialInterno(Jugador jugador, int aristaId) {
        Arista arista = tablero.encontrarArista(aristaId);
        jugador.construirCarreteraInicialEn(arista);
    }

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
