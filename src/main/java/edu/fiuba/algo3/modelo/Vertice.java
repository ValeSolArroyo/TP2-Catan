package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import  java.util.List;

import edu.fiuba.algo3.modelo.excepciones.AsentamientoExistenteError;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;


public class Vertice {
    // ATRIBUTOS DE CLASE

    // ATRIBUTOS
    private final int id;
    private Construccion construccion;
    private final List<Arista> aristasAdyacentes;
    private final List<Hexagono> hexagonosAdyacentes;
    private final List<Vertice> verticesAdyacentes; // vecinos directos

    // CONSTRUCTORES
    public Vertice(int id) {
        this.id = id;
        this.aristasAdyacentes = new ArrayList<>();
        this.hexagonosAdyacentes = new ArrayList<>();
        this.verticesAdyacentes = new ArrayList<>();
    }

    // MÉTODOS DE CLASE

    // MÉTODOS GENERALES

    // MÉTODOS DE COMPORTAMIENTO
    public void agregarArista(Arista arista) {
        if (!aristasAdyacentes.contains(arista)) {
            aristasAdyacentes.add(arista);
        }
    }

    public void agregarHexagono(Hexagono h) {
        if (!hexagonosAdyacentes.contains(h)) {
            hexagonosAdyacentes.add(h);
        }
    }

    public void agregarVerticeAdyacente(Vertice v) {
        if (!verticesAdyacentes.contains(v) && v != this) {
            verticesAdyacentes.add(v);
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

    // GETTERS
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

    // SETTERS
}