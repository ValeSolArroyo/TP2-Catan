package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.comercio.Puerto;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.construcciones.Carretera;

import java.util.*;

public class Jugador {
    private final int id;
    private final String nombre;
    private int puntosVictoria;
    private final Inventario inventario;
    private List<Construccion> construcciones;

    public Jugador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.puntosVictoria = 0;
        this.inventario = new Inventario();
        this.construcciones = new ArrayList<>();
    }

    public boolean primeraColocacion() {
        return this.construcciones.size() == 2;
    }

    public boolean segundaColocacion() {
        return this.construcciones.size() == 4;
    }
    
    public void descartar() {
        this.inventario.descartarMitadRecursos();
    }

    public void robarCarta(Jugador victima) {
        victima.serRobadoPor(this);
    }

    public void serRobadoPor(Jugador ladron) {
        Recurso robado = this.inventario.quitarRecursoAlAzar();
        robado.asignarA(ladron); // Solo le decimos al recurso que se asigne, si es NullRecurso no hace nada
    }

    public void agregarConstruccion(Construccion construccion) {
        construcciones.add(construccion);
    }

    public void construir(Construccion construccion, EspacioConstruible espacio) {
        // Para construir en las primeras dos rondas
        if (construcciones.size() < 4) {

        }

        construccion.validarEn(espacio, this);


        // Para construir en general
        inventario.consumirRecurso(construccion.costoConstruccion());
        espacio.validarConstruccion(construccion, this);
        espacio.asignarConstruccion(construccion);

        this.agregarConstruccion(construccion);
        this.puntosVictoria += construccion.puntosVictoria();
    }

    private void sumarPuntoDeVictoria() {
        this.puntosVictoria++;
    }

    public void recibirRecurso(Recurso recurso) {
        inventario.agregarRecurso(recurso);
    }

    // TODO: BORRAR Y PREGUNTAR PORQUE LO USAMOS EN CADA TEST. GETTERS NECESARIOS PARA TESTEO
    public int obtenerCantidadTotalDeRecursos() { 
        return this.inventario.cantidadTotal(); 
    }

    public int obtenerPuntosDeVictoria() {
        return puntosVictoria;
    }

    public int obtenerCantidadDeConstrucciones(){
        return this.construcciones.size();
    }
}
