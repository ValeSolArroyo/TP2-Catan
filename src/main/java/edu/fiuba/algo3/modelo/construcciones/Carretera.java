package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.excepciones.YaHayCarreteraError;
import edu.fiuba.algo3.modelo.jugador.Inventario;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Ladrillo;
import edu.fiuba.algo3.modelo.recursos.Madera;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;

import java.util.List;
import java.util.Set;

public class Carretera implements Construccion {
    private final Jugador propietario;

    public Carretera(Jugador propietario) {
        this.propietario = propietario;
    }

    @Override
    public void producir(Recurso recurso) {
        // No produce
    }

    @Override
    public void registrarPropietarioEn(Set<Jugador> jugadores) {
        // No registra
    }

    @Override
    public boolean tieneDePropietarioA(Jugador jugador) {
        return this.propietario.equals(jugador);
    }

    @Override
    public void validarEn(EspacioConstruible espacio, Jugador jugador) {
        espacio.validarCarretera(jugador);
    }

    @Override
    public void cobrar(Jugador jugador) {
        jugador.darRecursos(new Madera(), 1);
        jugador.darRecursos(new Ladrillo(), 1);
    }

    @Override
    public void ocupar() {
        throw new YaHayCarreteraError("No se puede colocar");
    }

    @Override
    public int puntosVictoria() {
        return 0;
    }

    @Override
    public void aplicarCambio(Jugador jugador, EspacioConstruible espacio) {
        espacio.asignarConstruccion(this);
    }
}