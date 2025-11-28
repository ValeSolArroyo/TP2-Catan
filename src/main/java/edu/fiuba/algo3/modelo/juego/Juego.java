package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.cartasBonificacion.GranCaballeria;
import edu.fiuba.algo3.modelo.cartasBonificacion.GranRutaComercial;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.PuntoVictoria;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.excepciones.AccionNoPermitidaError;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.juegoState.EstadoJuego;
import edu.fiuba.algo3.modelo.juegoState.EstadoPrimerasColocaciones;
import edu.fiuba.algo3.modelo.juegoState.EstadoDados;
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
        this.estadoActual = new EstadoPrimerasColocaciones();
        this.cartasDesarrollo = cartasDesarrollo;
        this.granCaballeria = new GranCaballeria();
        this.granRutaComercial = new GranRutaComercial();

    }

    public void establecerEstado(EstadoJuego estado) {
        this.estadoActual = estado;
    }

    public void colocarPobladoInicial(Juego juego, Vertice vertice, Arista arista) {
        // TODO: La lógica concreta de colocación inicial no está modelada en los estados.
        // Por ahora solo validamos la acción para evitar referencias a métodos inexistentes.
        if (!estadoActual.puedeColocarPobladoInicial()) {
            throw new AccionNoPermitidaError("No se puede colocar poblado inicial en el estado actual");
        }
    }

    public int lanzarDados() {
        if (!estadoActual.puedeLanzarDados()) {
            throw new AccionNoPermitidaError("No se pueden lanzar los dados en el estado actual");
        }
        return dado.lanzarDados();
    }


    public void activarLadron(Accion accionActivarLadron, Hexagono nuevoLugarLadron, Jugador victima) {
        if (!estadoActual.puedeUsarLadron()){
            throw new AccionNoPermitidaError("No se puede activar al ladrón en el estado actual");
        }
        Jugador jugadorActual = this.jugadorActual();
        accionActivarLadron.ejecutar(
                this,
                jugadorActual,
                new Vertice(),
                new Arista(new Vertice(), new Vertice()),
                dado,
                new NullConstruccion(),
                new Arista(new Vertice(), new Vertice()),
                cartasDesarrollo,
                new PuntoVictoria(),
                victima,
                null,
                null,
                null,
                listaJugadores,
                nuevoLugarLadron
        );

    }

    public void finalizarTurno() {
        if (!estadoActual.puedeFinalizarTurno()) {
            throw new AccionNoPermitidaError("No se puede finalizar el turno en el estado actual");
        }
        avanzarTurno();
    }

    public void construir(Construccion construccion, EspacioConstruible espacio) {
        if (!estadoActual.puedeConstruir()) {
            throw new AccionNoPermitidaError("No se puede construir en el estado actual");
        }
        jugadorActual().construir(construccion, espacio);
    }

    public void comprarCartaDesarrollo(Jugador jugador){
        if (!estadoActual.puedeComprarCartaDesarrollo()) {
            throw new AccionNoPermitidaError("No se puede comprar carta de desarrollo en el estado actual");
        }
        // TODO: por ahora no realizamos acción para evitar referencias invalidas.
    }

    public void jugarCartaDesarrollo(Juego juego, CartaDesarrollo cartaDesarrollo, Jugador victima,
                                     List<Arista> carreterasAConstruir, List<Recurso> recursosDeBanca,
                                     Recurso recursoAnunciado, List<Jugador> jugadores, Hexagono nuevoLugarLadron){
        if (!estadoActual.puedeJugarCartaDesarrollo()) {
            throw new AccionNoPermitidaError("No se puede jugar carta de desarrollo en el estado actual");
        }
        // TODO: delegar a comandos especificos según la carta.
    }


    public void producirRecursos(int numero) {
        tablero.producir(numero);
    }

    public void darRecursosIniciales(Vertice vertice){
        tablero.darRecursosIniciales(vertice);
    }

    public void primeraColocacionRealizada(){
        if (todosColocaronPrimerPoblado()) {
            // EstadoSegundaColocacion no existe en el proyecto; usamos un estado existente válido.
            establecerEstado(new EstadoDados());
        } else {
            avanzarTurno();
        }
    }

    public void segundaColocacionRealizada(){
        if (todosTerminaronColocacionesIniciales()) {
            // EstadoTirarDados no existe; pasamos a EstadoDados para habilitar tirada inicial.
            establecerEstado(new EstadoDados());
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
