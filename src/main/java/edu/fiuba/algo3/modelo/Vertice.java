package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import  java.util.List;

import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.AsentamientoExistenteError;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;


public class Vertice {
    private final int id;
    private Construccion construccion;
    private final List<Arista> aristasAdyacentes;
    private final List<Hexagono> hexagonosAdyacentes;
    private final List<Vertice> verticesAdyacentes; // vecinos directos

    public Vertice(int id) {
        this.id = id;
        this.aristasAdyacentes = new ArrayList<>();
        this.hexagonosAdyacentes = new ArrayList<>();
        this.verticesAdyacentes = new ArrayList<>();
    }

    public void agregarArista(Arista arista) {
        if (!aristasAdyacentes.contains(arista)) {
            aristasAdyacentes.add(arista);
        }
    }

    public void agregarHexagono(Hexagono hexagono) {
        if (!hexagonosAdyacentes.contains(hexagono)) {
            hexagonosAdyacentes.add(hexagono);
        }
    }

    public void agregarVerticeAdyacente(Vertice vertice) {
        if (!verticesAdyacentes.contains(vertice) && vertice != this) {
            verticesAdyacentes.add(vertice);
        }
    }

    public boolean yaTieneConstruccion() {
        return construccion != null;
    }

    private boolean hayConstruccionEnVecinos() {
        for (Vertice v : verticesAdyacentes) {
            if (v.yaTieneConstruccion()) return true;
        }
        return false;
    }

    public void construirPoblado(Jugador propietario) {
        if (propietario == null) {
            throw new IllegalArgumentException("El poblado debe tener un propietario válido.");
        }

        if (yaTieneConstruccion()) {
            throw new AsentamientoExistenteError("No se puede construir asentamiento: ya hay un asentamiento existente en este vértice.");
        }

        if (hayConstruccionEnVecinos()) {
            throw new ReglaDeDistanciaError("No se puede construir asentamiento: hay un poblado o ciudad demasiado cerca.");
        }

        this.construccion = new Poblado(propietario);
    }

    public void construirCiudad(Jugador propietario) {
        if (!yaTieneConstruccion()) {
            throw new AsentamientoExistenteError("No se puede construir ciudad: no hay un poblado previo.");
        }

        Construccion actual = this.construccion;

        if (!(actual instanceof Poblado)) {
            throw new AsentamientoExistenteError("No se puede construir ciudad: ya hay una ciudad en este vértice.");
        }

        if (!actual.esPropietario(propietario)) {
            throw new AsentamientoExistenteError("No se puede construir ciudad: el poblado pertenece a otro jugador.");
        }

        this.construccion = new Ciudad(propietario);
    }

    public boolean tieneId(int id) {
        return this.id == id;
    }

    public int obtenerId() {
        return id;
    }

    public List<Vertice> obtenerVerticesAdyacentes() {
        return verticesAdyacentes;
    }

    public List<Hexagono> obtenerHexagonosAdyacentes() {
        return hexagonosAdyacentes;
    }

    public Construccion obtenerConstruccion() {
        return construccion;
    }
}