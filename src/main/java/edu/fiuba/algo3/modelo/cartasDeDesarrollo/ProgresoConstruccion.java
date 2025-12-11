package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.YaHayCarreteraError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Ladrillo;
import edu.fiuba.algo3.modelo.recursos.Madera;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Arista;

import java.util.List;

public class ProgresoConstruccion implements CartaDesarrollo {

    private Juego juego;
    private Jugador jugadorActual;
    private  List<Arista> carreterasAConstruir;
    private boolean cartaHabilitada= false;


    public ProgresoConstruccion(Juego juego, Jugador jugador, List<Arista> carreterasAConstruir) {
        this.juego = juego;
        this.jugadorActual = jugador;
        this.carreterasAConstruir = carreterasAConstruir;
    }

    public ProgresoConstruccion() {}

    @Override
    public void ejecutarAlGuardar(Jugador jugador) {}

    @Override
    public void ejecutar() {
        for (Arista arista: carreterasAConstruir) {
            arista.construirCarreteraPrimerasColocaciones(jugadorActual, new Carretera(jugadorActual));
        }
        jugadorActual.eliminarCarta(new ProgresoConstruccion());
    }

    public String getCarta(){
        return "Progreso de Construccion";
    }

    @Override
    public void habilitarCarta() {
        this.cartaHabilitada = true;

    }

    @Override
    public boolean getHabilitacion() {
        return cartaHabilitada;
    }

    @Override
    public boolean coincideCon(CartaDesarrollo cartaAComparar) {
        return cartaAComparar.coincideConProgresoConstruccion();
    }

    @Override
    public boolean coincideConCaballero() {
        return false;
    }

    @Override
    public boolean coincideConProgresoConstruccion() {
        return true;
    }

    @Override
    public boolean coincideConProgresoDescubrimiento() {
        return false;
    }

    @Override
    public boolean coincideConProgresoMonopolio() {
        return false;
    }

    @Override
    public boolean coincideConPuntoVictoria() {
        return false;
    }
}
