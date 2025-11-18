package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.recursos.*;

import java.util.*;

public class Jugador {
    private final int id;
    private final String nombre;
    private final Inventario inventario;
    private List<Construccion> construcciones;

    public Jugador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.inventario = new Inventario();
        this.construcciones = new ArrayList<>();
    }

    public void recibir(Recurso recurso) {
        inventario.agregar(recurso);
    }

    public void agregarConstruccion(Construccion construccion) {
        construcciones.add(construccion);
    }

    public void construirPoblado(Vertice vertice) {
        inventario.gastarRecursosPoblado();
        vertice.construirPoblado(this);
    }

    public void construirCiudad(Vertice vertice) {
        inventario.gastarRecursosCiudad();
        vertice.construirCiudad(this);
    }

    public void descartarMitadDeRecursos() {
        inventario.descartarMitad();
    }

    public void robarCarta(Jugador victima) {
        victima.serRobadoPor(this);
    }

    public void serRobadoPor(Jugador ladron) {
        Recurso robado = this.inventario.quitarRecursoAlAzar();
        if (robado != null) {
            ladron.recibir(robado);
        }
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerCantidadDeConstrucciones() {
        return construcciones.size();
    }

    public int obtenerCantidadDeRecurso(Recurso tipoRecurso) {
        return 0; //Por ahora
    }

    public int obtenerCantidadTotalDeRecursos() {
        return inventario.cantidadTotal();
    }
}
