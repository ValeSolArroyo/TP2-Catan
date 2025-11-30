package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.Ladrillo;
import edu.fiuba.algo3.modelo.recursos.Madera;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Hexagono;

import java.util.List;

public class ProgresoConstruccion implements CartaDesarrollo {

    @Override
    public void ejecutar(Juego juego, Jugador victima, Jugador jugador, Hexagono nuevoLugar, List<Arista> carreterasAConstruir, List<Recurso> recursosDeBanca, Recurso recursoDeseado) {
        for (Arista arista: carreterasAConstruir) {
            List<Recurso> recursosNecesarios = List.of(new Madera(), new Ladrillo());
            for (Recurso recurso : recursosNecesarios) {
                jugador.recibirRecurso(recurso);
            }

            jugador.construir(new Carretera(jugador), arista);
            juego.revisarGranRutaComercial(jugador);
        }
    }
}
