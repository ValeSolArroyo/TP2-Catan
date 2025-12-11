package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public interface CartaDesarrollo extends Accion {
    void ejecutarAlGuardar(Jugador jugador);
    String getCarta();
    void habilitarCarta();
    boolean getHabilitacion();
    boolean coincideCon(CartaDesarrollo cartaAComparar);
    boolean coincideConCaballero();
    boolean coincideConProgresoConstruccion();
    boolean coincideConProgresoDescubrimiento();
    boolean coincideConProgresoMonopolio();
    boolean coincideConPuntoVictoria();
}