package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import  java.util.List;
import java.util.Set;

import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

public class Vertice {
    private Construccion construccion;
    private List<Vertice> vecinos;
    private List<Arista> aristas;
    private final int id;

    public Vertice(int id) {
        this.construccion = new NullConstruccion();
        this.vecinos = new ArrayList<>();
        this.aristas = new ArrayList<>();
        this.id = id;
    }

    public void agregarVecino(Vertice vecino) {
        if (!vecinos.contains(vecino)) vecinos.add(vecino);
    }
    public void agregarArista(Arista arista) {
        if (!aristas.contains(arista)) aristas.add(arista);
    }

    public void construirPoblado(Jugador jugador) {
        construccion.validarLugarLibre();

        for (Vertice vecino : vecinos) {
            try {
                vecino.validarSiEstaLibre();
            } catch (RuntimeException e) {
                throw new ReglaDeDistanciaError("Hay un edificio adyacente. No se puede construir.");
            }
        }

        this.construccion = new Poblado(jugador);
    }

    public void construirCiudad(Jugador jugador) {
        // TODO: validacion
        this.construccion = new Ciudad(jugador);
    }

    private void validarSiEstaLibre() {
        construccion.validarLugarLibre();
    }

    private void validarDistancia() {
        for (Vertice v : vecinos) {
            try {
                v.validarSiEstaLibre();
            } catch (Exception e) {
                throw new ReglaDeDistanciaError("TODO: personalizar");
            }
        }
    }

    public void validarConstruccionPoblado() {
        validarSiEstaLibre();
        validarDistancia();
    }


    public void asignarConstruccion(Construccion construccion) {
        this.construccion = construccion;
    }

    public Construccion obtenerConstruccion() {
        return construccion;
    }

    public int obtenerId() {
        return id;
    }

    public void registrarPropietarioEn(Set<Jugador> jugadores) {
        construccion.registrarPropietarioEn(jugadores);
    }

    public boolean esPropiedadDe(Jugador jugador) {
        return this.construccion.esPropiedadDe(jugador);
    }

    public void producirSegunTerreno(Terreno terreno) {
        terreno.producirPara(construccion);
    }
}