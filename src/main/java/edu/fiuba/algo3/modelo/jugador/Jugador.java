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

    // VALIDACIONES

    public boolean yaColocoPrimeraConstruccion() {
        return this.construcciones.size() >= 1;
    }

    public boolean yaColocoConstruccionesInicialesCompletas() {
        return this.construcciones.size() >= 2;
    }

    // ROBOS Y DESCARTE
    public void descartarSiExcedeLimiteDeCartas() {
        if (this.inventario.cantidadTotal() > 7) {
            this.inventario.descartarMitad();
        }
    }

    public void robarCarta(Jugador victima) {
        victima.serRobadoPor(this);
    }

    public void serRobadoPor(Jugador ladron) {
        Recurso robado = this.inventario.quitarRecursoAlAzar();
        robado.asignarA(ladron); // Solo le decimos al recurso que se asigne, si es NullRecurso no hace nada
    }

    // CONSTRUCCIONES

    public void agregarConstruccion(Construccion construccion) {
        construcciones.add(construccion);
    }

    public void construirPoblado(Vertice vertice) {
        vertice.validarConstruccionPoblado();
        inventario.gastarRecursosPoblado();
        Poblado poblado = new Poblado(this);
        this.agregarConstruccion(poblado);
        vertice.asignarConstruccion(poblado);
        vertice.entregarPuertoA(this);
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
        vertice.entregarPuertoA(this);
        this.sumarPuntoDeVictoria();
    }

    public void construirCarreteraInicialEn(Arista arista) {
        Carretera carretera = new Carretera(this);
        this.agregarConstruccion(carretera);
        arista.asignarConstruccion(carretera);
    }

    // COMERCIO

    public void gastar(Recurso recurso, int cantidad) {
        inventario.gastar(recurso, cantidad);
    }

    public void agregarPuerto(Puerto puerto) {
        this.puertos.add(puerto);
    }

    public int obtenerTasaDeCambioPara(Recurso recurso) {
        int mejorTasa = 4; // Tasa base del banco

        for (Puerto puerto : puertos) {
            int tasaDelPuerto = recurso.obtenerTasaEn(puerto);

            if (tasaDelPuerto < mejorTasa) {
                mejorTasa = tasaDelPuerto;
            }
        }
        return mejorTasa;
    }

    // PUNTUACION Y RECURSOS 

    private void sumarPuntoDeVictoria() {
        this.puntosVictoria++;
    }

    public void recibir(Recurso recurso) {
        inventario.agregar(recurso);
    }

    // GETTERS NECESARIOS PARA TESTEO

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
