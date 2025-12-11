package edu.fiuba.algo3.modelo.tablero;
import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.YaHayCarreteraError;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public class Arista implements EspacioConstruible {
    private int id;
    private final Vertice vertice1;
    private final Vertice vertice2;
    private Construccion construccion;
    private Jugador propietario;

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

    
    public void construirPoblado(Jugador jugador, Construccion construccion) {
        throw new ConstruccionInvalidaError("No se puede construir un poblado en un arista");
    }

    public void construirCiudad(Jugador jugador, Construccion construccion) {
        throw new ConstruccionInvalidaError("No se puede construir una ciudad en un vértice");
    }

    public void construirCarretera(Jugador jugador, Construccion construccion) {
        this.construccion.ocupar();
        if (!this.vertice1.validarConstruccionesProximas(jugador) && !this.vertice2.validarConstruccionesProximas(jugador)) {
            if (!this.vertice1.validarCarreterasProximas(jugador) && !this.vertice2.validarCarreterasProximas(jugador)) {
                throw new ConstruccionInvalidaError("No se puede colocar la carretera porque no cumple con las condiciones.");
            }
        }
        jugador.cobrarConstruccion(construccion);
        this.construccion = construccion;
    }

    public void construirCarreteraPrimerasColocaciones(Jugador jugador, Carretera construccion) {
        this.construccion.ocupar();
        if (!this.vertice1.validarConstruccionesProximas(jugador) && !this.vertice2.validarConstruccionesProximas(jugador)) {
            if (!this.vertice1.validarCarreterasProximas(jugador) && !this.vertice2.validarCarreterasProximas(jugador)) {
                throw new ConstruccionInvalidaError("No se puede colocar la carretera porque no cumple con las condiciones.");
            }
        }
        this.construccion = construccion;
    }

    public boolean validarCarreteraPropia(Jugador jugador) {
        try {
            this.construccion.ocupar();
        } catch (YaHayCarreteraError e) {
            try {
                construccion.tieneDePropietarioA(jugador);
                return true;
            } catch (ConstruccionInvalidaError error) {
                return false;
            }
        }
        return false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vertice getVertice1() {
        return this.vertice1;
    }

    public Vertice getVertice2() {
        return this.vertice2;
    }

    public int getId() {
        return this.id;
    }
}