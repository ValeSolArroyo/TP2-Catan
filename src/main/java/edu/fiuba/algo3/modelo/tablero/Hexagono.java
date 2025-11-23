package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.hexagonoStrategy.StrategyConLadron;
import edu.fiuba.algo3.modelo.hexagonoStrategy.StrategyHexagono;
import edu.fiuba.algo3.modelo.hexagonoStrategy.StrategySinLadron;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.terrenos.Terreno;
import edu.fiuba.algo3.modelo.terrenos.Desierto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Hexagono {
    private final Terreno terreno;
    private final int numeroFicha;
    private final List<Arista> aristas;
    private final int id;
    private List<Vertice> vertices;
    private StrategyHexagono estrategiaActual;


    public Hexagono(int id, Terreno terreno, int numeroFicha) {
        this.terreno = terreno;
        this.numeroFicha = numeroFicha;
        this.vertices = new ArrayList<>();
        this.id = id;
        this.estrategiaActual = new StrategySinLadron();
        this.aristas = new ArrayList<>();
    }

    public boolean contieneVertice(Vertice vertice) {
        return this.vertices.contains(vertice);
    }

    public void ponerLadron() {
        this.estrategiaActual = new StrategyConLadron();
    }

    public void quitarLadron() {
        this.estrategiaActual = new StrategySinLadron();
    }

    public void agregarVertice(Vertice vertice) {
        if (!vertices.contains(vertice) && vertices.size() < 6) {
            vertices.add(vertice);
        }
    }

    public void agregarArista(Arista arista) {
        if (!aristas.contains(arista) && aristas.size() < 6) {
            aristas.add(arista);
        }
    }

    public void registrarPropietariosEn(Set<Jugador> jugadores) {
        for (Vertice vertice : vertices) {
            vertice.registrarPropietarioEn(jugadores);
        }
    }

    public void producirRecursos(int numero) {
        if (numero == this.numeroFicha) {
            estrategiaActual.producirRecursos(vertices, terreno);
        }
    }

    public void entregarRecursoInicialA(Jugador jugador) {
        for (Vertice vertice : vertices) {
            if (vertice.esPropiedadDe(jugador)) {
                vertice.producirSegunTerreno(this.terreno);
            }
        }
    }

    // GETTERS NECESARIOS PARA INICIALIZACION DEL TABLERO, NO SE USAN EN LOGICAS O CONSULTAS EXTERNAS
    public int obtenerId(){
        return this.id;
    }

    public Terreno obtenerTipoTerreno(){
        return this.terreno;
    }

    public int obtenerNumeroFicha(){
        return this.numeroFicha;
    }

}
