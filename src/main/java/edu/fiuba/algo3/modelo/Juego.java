package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Juego {
    private final List<Jugador> listaJugadores;
    private Tablero tablero;
    private int indiceTurno;
    private final Dado dados;
    private Ladron ladron;

    public Juego(List<Jugador> jugadores, Tablero tablero) {
        if (jugadores == null || jugadores.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un jugador.");
        }
        this.listaJugadores = jugadores;
        this.dados = new Dado();
        this.tablero = tablero;
        this.indiceTurno = 0;
    }

    public Jugador obtenerTurnoActual() {
        return listaJugadores.get(indiceTurno);
    }


    public int lanzarDados() {
        int resultado = dados.lanzar();
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
        for (Hexagono h : vertice.obtenerHexagonosAdyacentes()) {
            if (!h.esDesierto()) {
                jugador.agregarRecursos(h.obtenerTipoTerreno(), 1);
            }
        }
    }

    public Tablero obtenerTablero() {
        return tablero;
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

        for (Vertice v : destino.obtenerVertices()) {
            if(v.yaTieneConstruccion()) {
                Jugador propietario = v.obtenerConstruccion().obtenerPropietario();
                jugadoresAfectados.add(propietario);
            }
        }

        // No se puede robar a uno mismo
        jugadoresAfectados.remove(obtenerTurnoActual());

        // Devolvemos la lista de posibles víctimas para que la interfaz decida
        return new ArrayList<>(jugadoresAfectados);
    }

    public void producirRecursos(int numeroLanzado) {
        tablero.producirRecursos(numeroLanzado);
    }


}
