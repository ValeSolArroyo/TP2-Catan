package edu.fiuba.algo3.modelo.tablero;

import java.util.ArrayList;
import  java.util.List;
import java.util.Set;

import edu.fiuba.algo3.modelo.comercio.Puerto;
import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

public class Vertice implements EspacioConstruible {
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
        this.validarDistancia();
        this.construccion = new Poblado(jugador);
    }

    public void mejorarPoblado(Jugador jugador) {
        this.construccion = new Ciudad(jugador);
    }

    @Override
    public void validarPoblado(Jugador jugador) {
        this.construccion.construir();
        this.validarDistancia();

    }

    @Override
    public void validarCiudad(Jugador jugador) {

    }

    @Override
    public void validarCarretera(Jugador jugador) {
        throw new ConstruccionInvalidaError("No se puede construir una carretera en un vértice");
    }

    @Override
    public void asignarConstruccion(Construccion construccion) {
        this.construccion = construccion;
    }

    public void registrarPropietarioEn(Set<Jugador> jugadores) {
        construccion.registrarPropietarioEn(jugadores);
    }

    public void producirSegunTerreno(Terreno terreno) {
        terreno.producirPara(construccion);
    }

    public boolean tieneConstruccionPropia(Jugador jugador) {
        return construccion.esPropiedadDe(jugador);
    }


    private void validarDistancia() {
        for (Vertice vertice : vecinos) {
            try {
                vertice.validarSiEstaLibre();
            } catch (Exception e) {
                throw new ReglaDeDistanciaError("TODO: personalizar");
            }
        }
    }


    public boolean tieneCarreteraPropiaAdyacente(Jugador jugador) {
        for (Arista aristaAdyacente : this.aristas) { 
            if (aristaAdyacente.tieneCarreteraPropia(jugador)) {
                return true;
            }
        }
        return false;
    }

    public boolean esPropiedadDe(Jugador jugador) {
        return this.construccion.esPropiedadDe(jugador);
    }

    public int obtenerId() {
        return id;
    }


}