package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.construcciones.NullConstruccion;
import edu.fiuba.algo3.modelo.terrenos.Terreno;

import java.util.Set;

public class NullVertice extends Vertice {

    public NullVertice() {
        super(-1);
    }

    @Override
    public void construirPoblado(Jugador jugador) {
        // No hace nada (comportamiento seguro) o lanza una excepción si se quiere
        // indicar que la operación es inválida, pero el objetivo del Null Object
        // es evitar errores. Mantengamos el "no hace nada".
    }

    @Override
    public void construirCiudad(Jugador jugador) {
        // No hace nada
    }

    @Override
    public void agregarVecino(Vertice vecino) {
        // No hace nada
    }

    @Override
    public void agregarArista(Arista arista) {
        // No hace nada
    }

    // Sobrescribe el método público que llama a los métodos privados.
    // Esto asegura que si el código cliente lo llama, no haga nada.
    @Override
    public void validarConstruccionPoblado() {
        // No hace nada
    }


    @Override
    public void asignarConstruccion(Construccion construccion) {
        // No hace nada
    }

    @Override
    public Construccion obtenerConstruccion() {
        return new NullConstruccion();
    }

    @Override
    public void registrarPropietarioEn(Set<Jugador> jugadores) {
        // No hace nada
    }

    @Override
    public boolean esPropiedadDe(Jugador jugador) {
        return false; // Nunca es propiedad de nadie.
    }

    @Override
    public void producirSegunTerreno(Terreno terreno) {
        // No hace nada
    }

}

