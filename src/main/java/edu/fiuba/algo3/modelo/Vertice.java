package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import  java.util.List;
import java.util.Set;

import edu.fiuba.algo3.modelo.comercio.NullPuerto;
import edu.fiuba.algo3.modelo.comercio.Puerto;
import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.CiudadSinPobladoError;
import edu.fiuba.algo3.modelo.excepciones.ConstruccionAjenaError;
import edu.fiuba.algo3.modelo.excepciones.ReglaDeDistanciaError;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

public class Vertice {
    private Construccion construccion;
    private List<Vertice> vecinos;
    private List<Arista> aristas;
    private final int id;
    private Puerto puerto;

    public Vertice(int id) {
        this.construccion = new NullConstruccion();
        this.vecinos = new ArrayList<>();
        this.aristas = new ArrayList<>();
        this.id = id;
        this.puerto = new NullPuerto();
    }
    // COMPORTAMIENTO 

    public void agregarVecino(Vertice vecino) {
        if (!vecinos.contains(vecino)) vecinos.add(vecino);
    }
    public void agregarArista(Arista arista) {
        if (!aristas.contains(arista)) aristas.add(arista);
    }

    public void entregarPuertoA(Jugador jugador) {
        jugador.agregarPuerto(this.puerto);
    }

    public void asignarPuerto(Puerto puerto) {
        this.puerto = puerto;
    }

    public void construirPoblado(Jugador jugador) {
        construccion.validarLugarLibre();
        this.validarDistancia();
        this.construccion = new Poblado(jugador);
    }

    public void mejorarPoblado(Jugador jugador) {
        this.construccion = new Ciudad(jugador);
    }

    public void asignarConstruccion(Construccion construccion) {
        this.construccion = construccion;
    }

    public void registrarPropietarioEn(Set<Jugador> jugadores) {
        construccion.registrarPropietarioEn(jugadores);
    }

    public void producirSegunTerreno(Terreno terreno) {
        terreno.producirPara(construccion);
    }

    // VALIDACIONES

    public boolean tieneConstruccionPropia(Jugador jugador) {
        return construccion.esPropiedadDe(jugador);
    }

     private void validarSiEstaLibre() {
        construccion.validarLugarLibre();
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

    public void validarConstruccionPoblado() {
        validarSiEstaLibre();
        validarDistancia();
    }

    public void validarConstruccionCiudad(Jugador jugador) {
        if (!(construccion instanceof Poblado)) {
            throw new CiudadSinPobladoError("Solo se puede construir una ciudad sobre un poblado");
        }

        if (!construccion.esPropiedadDe(jugador)) {
            throw new ConstruccionAjenaError("Solo puedes mejorar tu propio poblado");
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

    // GETTERS NECESARIOS PARA INICIALIZACION DEL TBLERO Y TESTEOS, NO SE USAN EN LOGICAS O CONSULTAS EXTERNAS

    public Construccion obtenerConstruccion() {
        return construccion;
    }

    public int obtenerId() {
        return id;
    }

}