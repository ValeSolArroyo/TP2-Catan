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
        // Todo el flujo esta bien, pero el tema es el método este. Porque como tenemos
        // esto de que eliminarRecurso en inventario recibe un STRING, complica todo...
        // y se vuelve a relacionar con esa cosa medio rara que hicimos en Inventario :(
        robado.asignarA(ladron);
    }

    public void agregarConstruccion(Construccion construccion) {
        construcciones.add(construccion);
    }

    public void construir(Construccion construccion, EspacioConstruible espacio) {
        construccion.validarEn(espacio, this);
        if (construcciones.size() >= 4) {
            inventario.consumirRecurso(construccion.costoConstruccion());
        }
        espacio.asignarConstruccion(construccion);
        this.agregarConstruccion(construccion);
        // TODO: ver tema PV --> proponemos verlo al final cuando lleguemos al momento de codear
        // el final del juego. Porque está la alternativa de que, por ejemplo, al final de cada
        // turno lo sumemos o que al final de tooodo el juego...

        //IDEA: hacer primero lo de las cartas de desarrollo antes de meternos con PV.

        //this.puntosVictoria += construccion.puntosVictoria(); // ciudad: 2, poblado: 1, carretera y null 0
    }

    public void recibirRecurso(Recurso recurso) {
        inventario.agregarRecurso(recurso);
    }
}
