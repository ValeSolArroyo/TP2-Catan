package edu.fiuba.algo3.modelo.construcciones;

import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.YaHayCiudadError;
import edu.fiuba.algo3.modelo.jugador.Inventario;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Grano;
import edu.fiuba.algo3.modelo.recursos.Mineral;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;
import java.util.List;

public class Ciudad implements Construccion {
    private final Jugador propietario;
    private final List<Recurso> costo = List.of(new Grano(), new Grano(),
            new Mineral(), new Mineral(), new Mineral());

    public Ciudad(Jugador propietario) {
        this.propietario = propietario;
    }

    @Override
    public void producir(Recurso recurso) {
        propietario.recibirRecurso(recurso);
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
        throw new YaHayCiudadError("No se puede colocar porque ya hay una ciudad");
    }

    @Override
    public int puntosVictoria() {
        return 1;
    }

    @Override
    public void aplicarCambio(Jugador jugador, EspacioConstruible espacio) {
        espacio.construirCiudad(jugador, this);
    }

    @Override
    public int getIdPropietario() {
        return propietario.getId();
    }
}
