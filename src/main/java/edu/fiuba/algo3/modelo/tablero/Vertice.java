package edu.fiuba.algo3.modelo.tablero;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.comercio.puertos.ComercioPuerto;
import edu.fiuba.algo3.modelo.comercio.puertos.NullPuerto;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

public class Vertice implements EspacioConstruible {
    private int id;
    private Construccion construccion;
    private List<Vertice> vecinos;
    private List<Arista> aristas;
    private ComercioPuerto puerto;

    public Vertice() {
        this.construccion = new NullConstruccion();
        this.vecinos = new ArrayList<>();
        this.aristas = new ArrayList<>();
        this.puerto = new NullPuerto();
    }

    public void agregarVecino(Vertice vecino) {
        if (!vecinos.contains(vecino)) {
            vecinos.add(vecino);
        }
    }

    public void agregarArista(Arista arista) {
        if (!aristas.contains(arista)) {
            aristas.add(arista);
        }
    }

    public void asignarPuerto(ComercioPuerto puerto) {
        this.puerto = puerto;
    }

    @Override
    public void construirPoblado(Jugador jugador, Construccion construccion) {
        this.construccion.ocupar();
        for (Vertice verticeVecino : vecinos) {
            verticeVecino.validarReglaDistancia();
        }
        if (!validarCarreterasProximas(jugador)) {
            throw new ConstruccionInvalidaError("No se puede construir si no hay carreteras");
        }
        this.construccion = construccion;
    }

    public void construirPobladoPrimerasColocaciones(Jugador jugador, Poblado construccion) {
        this.construccion.ocupar();
        for (Vertice verticeVecino : vecinos) {
            verticeVecino.validarReglaDistancia();
        }
        this.construccion = construccion;
    }

    @Override
    public void construirCiudad(Jugador jugador, Construccion nuevaConstruccion) {
        try {
            this.construccion.ocupar();
        } catch (YaHayPobladoError e) {
           this.construccion.tieneDePropietarioA(jugador);
            Construccion antigua = this.construccion;
            this.construccion = nuevaConstruccion;
            jugador.eliminarConstruccion(antigua);
            return;
        } catch (YaHayCiudadError e) {
            throw new ConstruccionInvalidaError("No se puede mejorar donde ya hay una ciudad.");
        }
        throw new ConstruccionInvalidaError("No se puede mejor una ciudad si no hay poblado");
    }

    @Override
    public void construirCarretera(Jugador jugador, Construccion construccion) {
        throw new ConstruccionInvalidaError("No se puede construir una carretera en un vértice");
    }

    public void producirSegunTerreno(Terreno terreno) {
        terreno.producirPara(construccion);
    }

    private void validarReglaDistancia() {
        try {
            this.construccion.ocupar();
        } catch (YaHayCiudadError | YaHayPobladoError e) {
            throw new ReglaDeDistanciaError("No se puede construir tan cerca de otra construcción.");
        }
    }

    public boolean validarConstruccionesProximas(Jugador jugador) {
        try {
            this.construccion.ocupar();
        } catch (YaHayCiudadError | YaHayPobladoError e) {
            try {
                this.construccion.tieneDePropietarioA(jugador);
                return true;
            } catch (ConstruccionInvalidaError error) {
                return false;
            }
        }
        return false;
    }

    public boolean validarCarreterasProximas(Jugador jugador) {
        for (Arista arista : aristas) {
            if (arista.validarCarreteraPropia(jugador)) {
                return true;
            }
        }
        return false;
    }

    public void ejecutarComercio(Jugador jugador, List<Recurso> recursosEntregados, List<Recurso> recursoDeseado) {
        this.puerto.ejecutar(jugador, this, recursosEntregados, recursoDeseado);
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public List<Arista>  getAristas(){
        return this.aristas;
    }

    public int getIdPropietario() {
        return construccion.getIdPropietario();
    }
}