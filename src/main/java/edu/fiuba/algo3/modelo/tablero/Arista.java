package edu.fiuba.algo3.modelo.tablero;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.YaHayCarreteraError;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public class Arista implements EspacioConstruible {

    private final Vertice vertice1;
    private final Vertice vertice2;
    private Construccion construccion;

    public Arista(Vertice vertice1, Vertice vertice2) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.construccion = new NullConstruccion();

        vertice1.agregarVecino(vertice2);
        vertice2.agregarVecino(vertice1);
        vertice1.agregarArista(this);
        vertice2.agregarArista(this);
    }

    public boolean conectaVertices(Vertice v1, Vertice v2) {
        return (this.vertice1 == v1 && this.vertice2 == v2) ||
                (this.vertice1 == v2 && this.vertice2 == v1);
    }

    @Override
    public void validarPoblado(Jugador jugador) {
        throw new ConstruccionInvalidaError("No se puede construir un poblado en un arista");
    }

    @Override
    public void validarCiudad(Jugador jugador) {
        throw new ConstruccionInvalidaError("No se puede construir una ciudad en un vértice");
    }

    @Override
    public void validarCarretera(Jugador jugador) {
        this.construccion.ocupar();
        if (!this.vertice1.validarConstruccionesProximas(jugador)
                && !this.vertice2.validarConstruccionesProximas(jugador)) {
            if (!this.vertice1.validarCarreterasProximas(jugador) && !this.vertice2.validarCarreterasProximas(jugador)) {
                throw new ConstruccionInvalidaError("No se puede colocar la carretera porque no cumple con las condiciones.");
            }
        }
    }

    public boolean validarCarreteraPropia(Jugador jugador) {
        try {
            this.construccion.ocupar();
        } catch (YaHayCarreteraError e) {
            return construccion.tieneDePropietarioA(jugador);
        }
        return false;
    }

    @Override
    public void asignarConstruccion(Construccion construccion) {
        this.construccion = construccion;
    }

    @Override
    public void reemplazarConstruccion(Jugador jugador, Construccion nuevaConstruccion) {
        // No hace nada
    }
}