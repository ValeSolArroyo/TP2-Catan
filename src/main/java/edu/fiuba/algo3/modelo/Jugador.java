package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.construcciones.Carretera;

import java.util.*;

public class Jugador {
    private final int id;
    private final String nombre;
    private int puntosVictoria;
    private final Inventario inventario;
    private List<Construccion> construcciones;

    public Jugador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.puntosVictoria = 0;
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
        vertice.validarConstruccionPoblado();
        inventario.gastarRecursosPoblado();
        Poblado poblado = new Poblado(this);
        this.agregarConstruccion(poblado);
        vertice.asignarConstruccion(poblado);
        this.sumarPuntoDeVictoria(1);
    }

    public void construirCiudad(Vertice vertice) {
        vertice.validarConstruccionCiudad(this);
        inventario.gastarRecursosCiudad();
        vertice.mejorarPoblado(this);
        this.sumarPuntoDeVictoria(1);
    }

    public void construirCarretera(Arista arista) {
        inventario.gastarRecursosCarretera();
        arista.validarConstruccionCarretera(this);
        Carretera carretera = new Carretera(this);
        arista.asignarConstruccion(carretera);
        this.agregarConstruccion(carretera);
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

    public void construirPobladoInicialEn(Vertice vertice) {
        vertice.validarConstruccionPoblado();
        Poblado poblado = new Poblado(this);
        this.agregarConstruccion(poblado);
        vertice.asignarConstruccion(poblado);
        this.sumarPuntoDeVictoria(1);
    }

    public int obtenerCantidadTotalDeRecursos() {
        return this.inventario.cantidadTotal();
    }

    // TODO: ver si nos conviene esto o solo hacer pv++; en donde lo usamos lol
    private void sumarPuntoDeVictoria(int cantidad) {
        this.puntosVictoria += cantidad;
    }

    public void construirCarreteraInicialEn(Arista arista) {
        Carretera carretera = new Carretera(this);
        this.agregarConstruccion(carretera);
        arista.asignarConstruccion(carretera);
    }

    // !!!!! REVISAR !!!!!
    public int obtenerPuntosDeVictoria() {
        return puntosVictoria;
    }
}
