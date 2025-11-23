package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.comercio.Puerto;
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
    private List<Puerto> puertos;

    public Jugador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.puntosVictoria = 0;
        this.inventario = new Inventario();
        this.construcciones = new ArrayList<>();
        this.puertos = new ArrayList<>();
    }

    public boolean primeraColocacion() {
        return this.construcciones.size() == 2;
    }

    public boolean segundaColocacion() {
        return this.construcciones.size() == 4;
    }
    
    public void descartar() {
        this.inventario.descartarMitadRecursos();
    }

    public void robarCarta(Jugador victima) {
        victima.serRobadoPor(this);
    }

    public void serRobadoPor(Jugador ladron) {
        Recurso robado = this.inventario.quitarRecursoAlAzar();
        robado.asignarA(ladron); // Solo le decimos al recurso que se asigne, si es NullRecurso no hace nada
    }

    public void agregarConstruccion(Construccion construccion) {
        construcciones.add(construccion);
    }

    public void construirPoblado(Vertice vertice) {
        vertice.validarConstruccionPoblado();
        inventario.consumirRecurso(new Madera());
        Poblado poblado = new Poblado(this);
        this.agregarConstruccion(poblado);
        vertice.asignarConstruccion(poblado);
        this.sumarPuntoDeVictoria();
    }

    public void construirCiudad(Vertice vertice) {
        vertice.validarConstruccionCiudad(this);
        inventario.gastarRecursosCiudad();
        vertice.mejorarPoblado(this);
        this.sumarPuntoDeVictoria();
    }

    public void construirCarretera(Arista arista) {
        inventario.gastarRecursosCarretera();
        arista.validarConstruccionCarretera(this);
        Carretera carretera = new Carretera(this);
        arista.asignarConstruccion(carretera);
        this.agregarConstruccion(carretera);
    }

    public void construirPobladoInicialEn(Vertice vertice) {
        vertice.validarConstruccionPoblado();
        Poblado poblado = new Poblado(this);
        this.agregarConstruccion(poblado);
        vertice.asignarConstruccion(poblado);
        this.sumarPuntoDeVictoria();
    }

    public void construirCarreteraInicialEn(Arista arista) {
        Carretera carretera = new Carretera(this);
        this.agregarConstruccion(carretera);
        arista.asignarConstruccion(carretera);
    }

    private void sumarPuntoDeVictoria() {
        this.puntosVictoria++;
    }

    public void recibirRecurso(Recurso recurso) {
        inventario.agregarRecurso(recurso);
    }

    // TODO: BORRAR Y PREGUNTAR PORQUE LO USAMOS EN CADA TEST. GETTERS NECESARIOS PARA TESTEO
    public int obtenerCantidadTotalDeRecursos() { 
        return this.inventario.cantidadTotal(); 
    }

    public int obtenerPuntosDeVictoria() {
        return puntosVictoria;
    }

    public int obtenerCantidadDeConstrucciones(){
        return this.construcciones.size();
    }
}
