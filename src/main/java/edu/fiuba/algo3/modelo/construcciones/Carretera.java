package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.YaHayCarreteraError;
import edu.fiuba.algo3.modelo.jugador.Inventario;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Ladrillo;
import edu.fiuba.algo3.modelo.recursos.Madera;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;
import java.util.List;


public class Carretera implements Construccion {
    private final Jugador propietario;
    private final List<Recurso> costo = List.of(new Madera(), new Ladrillo());

    public Carretera(Jugador propietario) {
        this.propietario = propietario;
    }

    @Override
    public void producir(Recurso recurso) {}

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
        throw new YaHayCarreteraError("No se puede colocar");
    }

    @Override
    public int puntosVictoria() {
        return 0;
    }

    @Override
    public void aplicarCambio(Jugador jugador, EspacioConstruible espacio) {
        espacio.construirCarretera(jugador, this);
        espacio.asignarConstruccion(this);
    }
}