package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.patronHexagono.Hexagono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Juego {
    private final List<Jugador> listaJugadores;
    private Tablero tablero;
    private int indiceTurno;
    private final Dado dado;
    private Ladron ladron;

    public Juego(List<Jugador> jugadores, Tablero tablero) {
        if (jugadores == null || jugadores.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un jugador.");
        }
        this.listaJugadores = jugadores;
        this.dado = new Dado();
        this.tablero = tablero;
        this.indiceTurno = 0;
        this.ladron = new Ladron();
    }

    public int lanzarDados() {
        int resultado = dado.lanzar();
        if (resultado == 7) {
            verificarDescartesPorLadron();
        }
        return resultado;
    }

    public void colocarPobladoInicial(Jugador jugador, int verticeId) {
        Vertice vertice = tablero.encontrarVertice(verticeId);

        vertice.construirPoblado(jugador);
        jugador.agregarConstruccion(new Poblado(jugador));

        if (jugador.obtenerCantidadDeConstrucciones() == 2) {
            darRecursosIniciales(jugador, vertice);
        }
    }

    public void darRecursosIniciales(Jugador jugador, Vertice vertice) {
        for (Hexagono hexagono : vertice.obtenerHexagonosAdyacentes()) {
            if (!hexagono.esDesierto()) {
                jugador.agregarRecursos(hexagono.obtenerTipoTerreno(), 1);
            }
        }
    }

    public void verificarDescartesPorLadron() {
        for (Jugador jugador : listaJugadores) {
            if (jugador.obtenerCantidadTotalDeRecursos() > 7) {
                jugador.descartarMitadDeRecursos();
            }
        }
    }

    public List<Jugador> moverLadron(int hexagonoId){
        Hexagono destino = tablero.obtenerHexagono(hexagonoId);
        ladron.moverA(destino);

        // Encuentra jugadores con construcciones alrededor del hexagono
        Set<Jugador> jugadoresAfectados = new HashSet<>();

        for (Vertice vertice : destino.obtenerVertices()) {
            if(vertice.yaTieneConstruccion()) {
                Jugador propietario = vertice.obtenerConstruccion().obtenerPropietario();
                jugadoresAfectados.add(propietario);
            }
        }

        // No se puede robar a uno mismo
        jugadoresAfectados.remove(obtenerTurnoActual());

        // Devolvemos la lista de posibles víctimas para que la interfaz decida
        return new ArrayList<>(jugadoresAfectados);
    }

    public void robarCartaDe(Jugador victima) {
        obtenerTurnoActual().robarCarta(victima);
    }

    public void producirRecursos(int numeroLanzado) {
        tablero.producirRecursos(numeroLanzado);
    }

    public Tablero obtenerTablero() {
        return tablero;
    }

    public Jugador obtenerTurnoActual() {
        return listaJugadores.get(indiceTurno);
    }

}
