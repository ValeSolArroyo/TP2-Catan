package edu.fiuba.algo3.modelo.cartasDeDesarrollo;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
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
            List<Recurso> recursosNecesarios = List.of(new Madera(), new Ladrillo());
            for (Recurso recurso : recursosNecesarios) {
                jugadorActual.recibirRecurso(recurso);
            }

            jugadorActual.construir(new Carretera(jugadorActual), arista);
            juego.revisarGranRutaComercial(jugadorActual);
        }

    }

    public String getCarta(){
        return "Progreso de Construccion";
    }
}
