package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.YaHayPobladoError;
import edu.fiuba.algo3.modelo.jugador.Inventario;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Ladrillo;
import edu.fiuba.algo3.modelo.recursos.Lana;
import edu.fiuba.algo3.modelo.recursos.Madera;
import edu.fiuba.algo3.modelo.recursos.Grano;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.List;

public class Poblado implements Construccion {
    private final Jugador propietario;
    private final List<Recurso> costo = List.of(new Madera(), new Ladrillo(), new Lana(), new Grano());

    public Poblado(Jugador propietario) {
        this.propietario = propietario;
    }

    @Override
    public void producir(Recurso recurso) {
        propietario.recibirRecurso(recurso);
    }

    @Override
    public void tieneDePropietarioA(Jugador jugador) {
        if (!(this.propietario.equals(jugador))){
            throw new ConstruccionInvalidaError("No se puede mejorar a ciudad un poblado ajeno.");
        }
    }

    @Override
    public void cobrar(Inventario inventario) {
        inventario.consumirRecurso(costo);
    }

    @Override
    public void ocupar() {
        throw new YaHayPobladoError("No se puede colocar porque ya hay un poblado");
    }

    @Override
    public int puntosVictoria() {
        return 1;
    }

    @Override
    public void aplicarCambio(Jugador jugador, EspacioConstruible espacio) {
        espacio.construirPoblado(jugador, this);
    }

    public void aplicarCambioPrimerasColocaciones(Jugador jugador, Vertice vertice) {
        vertice.construirPobladoPrimerasColocaciones(jugador, this);
    }
}