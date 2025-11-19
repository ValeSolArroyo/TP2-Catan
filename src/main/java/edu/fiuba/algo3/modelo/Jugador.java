package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.construcciones.Poblado;

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

    public int obtenerCantidadDeConstrucciones() {
        return construcciones.size();
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
        robado.asignarA(ladron); // Solo le decimos al recurso que se asigne, si es NullRecurso no hace nada
    }

    public void construirPobladoEn(Vertice v) {
        v.validarConstruccionPoblado();
        Poblado poblado = new Poblado(this);
        this.agregarConstruccion(poblado);
        v.asignarConstruccion(poblado);
    }

    public int obtenerCantidadTotalDeRecursos() {
        return this.inventario.cantidadTotal();
    }
}
