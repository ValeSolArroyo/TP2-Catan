package edu.fiuba.algo3.modelo.tablero;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

public class Vertice implements EspacioConstruible {
    private Construccion construccion;
    private List<Vertice> vecinos;
    private List<Arista> aristas;

    public Vertice() {
        this.construccion = new NullConstruccion();
        this.vecinos = new ArrayList<>();
        this.aristas = new ArrayList<>();
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

    @Override
    public void validarPoblado(Jugador jugador) {
        this.construccion.ocupar();
        this.validarDistancia();
    }

    // TODO: PREGUNTAR EL VIERNES SI ES MUY TERRIBLE MANEJARNOS CON EXCEPCIONES...
    @Override
    public void validarCiudad(Jugador jugador) {
        try {
            this.construccion.ocupar();
        } catch (YaHayPobladoError e) {
            if (!this.construccion.tieneDePropietarioA(jugador)) {
                throw new ConstruccionInvalidaError("No se puede mejorar a ciudad un poblado ajeno.");
            }
        } catch (YaHayCiudadError e) {
            throw new ConstruccionInvalidaError("No se puede mejorar donde ya hay una ciudad.");
        }
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

    private void validarDistancia() {
        for (Vertice verticeVecino : vecinos) {
            verticeVecino.validarReglaDistancia();
        }
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
            return construccion.tieneDePropietarioA(jugador);
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

    public void validarPuerto(Jugador jugador) {
        if (!(this.construccion.tieneDePropietarioA(jugador))){
            throw new ComercioInvalidoError("No tiene acceso al puerto 3:1");
        }

    }
}