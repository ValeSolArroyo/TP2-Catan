package edu.fiuba.algo3.controllers.fasesJuego;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaCaballero;

public interface RoboControlador extends AccionControlador {
    void elegirLugarLadron(VistaCaballero vistaCaballero);
    void conseguirVictima(Jugador victima);
    void elegirVictima();
}
