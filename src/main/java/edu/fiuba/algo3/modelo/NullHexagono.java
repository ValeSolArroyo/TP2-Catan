package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.terrenos.Desierto;


public class NullHexagono extends Hexagono {

    public NullHexagono() {
        super(-1, new Desierto(), 0);
    }

    @Override
    public boolean contieneVertice(Vertice vertice) {
        return false;
    }

    @Override
    public void ponerLadron() { /* No hace nada */ }

    @Override
    public void quitarLadron() { /* No hace nada */ }

    @Override
    public void producirRecursos(int numero) { /* No produce nada */ }

    @Override
    public void entregarRecursoInicialA(Jugador jugador) { /* No entrega nada */ }


    // La Fábrica llama a estos métodos para construir el tablero,
    // pero si alguien intenta llamarlos en NullHexagono, deben ser inofensivos.
    @Override
    public void agregarVertice(Vertice vertice) {
        // No hace nada
    }

    @Override
    public void agregarArista(Arista arista) {
        // No hace nada
    }

}
