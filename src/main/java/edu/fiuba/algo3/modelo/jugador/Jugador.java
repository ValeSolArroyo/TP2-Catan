package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.comercio.Comercio;
import edu.fiuba.algo3.modelo.comercio.ComercioInterno;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.tablero.Vertice;

import java.util.*;

public class Jugador {
    private final int id;
    private final String nombre;
    private int puntosVictoria;
    private final Inventario inventario;
    private List<Construccion> construcciones;
    private List<CartaDesarrollo> cartasDesarrollo;


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
        robado.asignarA(ladron);
    }

    public void agregarConstruccion(Construccion construccion) {
        construcciones.add(construccion);
    }

    public void construir(Construccion construccion, EspacioConstruible espacio) {
        construccion.validarEn(espacio, this);
        if (construcciones.size() >= 4) {
            construccion.cobrar(inventario);
        }
        espacio.asignarConstruccion(construccion);
        this.agregarConstruccion(construccion);
    }

    public void recibirRecurso(Recurso recurso) {
        inventario.agregarRecurso(recurso);
    }

    public void comerciarCartasRecurso(Comercio comercio, Vertice verticePuerto, List<Recurso> recursosEntregados, Recurso recursoDeseado) {
        comercio.validar(this, verticePuerto, recursosEntregados, List.of(recursoDeseado));
        comercio.ejecutar(this, recursosEntregados, List.of(recursoDeseado));
    }

    public void anunciarOferta(List<Recurso> recursosDeseados, List<Recurso> recursosAEntregar){
        // la idea es que se muestren los recursos que ofrece y los que pide el jugador actual
    }


    public void aceptarOferta(Jugador oferente, List<Recurso> recursosDeseadosPorOferente, List<Recurso> recursosAEntregarPorOferente){
        ComercioInterno comercioInterno = new ComercioInterno(oferente);
        this.comerciarInternamente( comercioInterno, recursosAEntregarPorOferente, recursosDeseadosPorOferente);
    }

    private void comerciarInternamente(ComercioInterno comercioInterno, List<Recurso> recursosAEntregarPorOferente, List<Recurso> recursosDeseadosPorOferente) {
        comercioInterno.validar(this, new Vertice(), recursosAEntregarPorOferente, recursosDeseadosPorOferente);
    }


    public void tieneRecursos(Recurso recurso, int cantidad) {
        inventario.validarRecursos(recurso, cantidad);
    }

    public void entregarRecursos(List<Recurso> entregados) {
        inventario.consumirRecurso(entregados);
    }

    public void tieneConstruccionEn(Vertice puerto) {
        puerto.validarPuerto(this);
    }

    public void guardarCartaDesarrollo(CartaDesarrollo cartaDesarrollo, List<Recurso> costoCarta){
        inventario.ejecturarCompra(costoCarta);
        cartasDesarrollo.add(cartaDesarrollo);
    }
}