package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.cartasBonificacion.CartaBonificacion;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.comercio.interno.ComercioInterno;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.tablero.EspacioConstruible;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.observer.Observable;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.List;

public class Jugador extends Observable {
    private final int id;
    private final String nombre;
    private final Color color;
    private int puntosVictoria;
    private int puntosVictoriaCartaDesarrollo;
    private final Inventario inventario;
    private List<Construccion> construcciones;
    private List<CartaDesarrollo> cartasDesarrollo;
    private List<CartaBonificacion> cartasBonificacion;
    private int cartasCaballeroJugadas;


    public Jugador(int id, String nombre, Color color) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.puntosVictoria = 0;
        this.puntosVictoriaCartaDesarrollo = 0;
        this.inventario = new Inventario(this);
        this.construcciones = new ArrayList<>();
        this.cartasDesarrollo = new ArrayList<>();
        this.cartasBonificacion = new ArrayList<>();
        this.cartasCaballeroJugadas = 0;
    }

    public void descartar() {
        this.inventario.descartarMitadRecursos();
    }

    public void robarCarta(Jugador victima) {
        victima.serRobadoPor(this);
        notificarObservadores();
    }

    private void serRobadoPor(Jugador ladron) {
        Recurso robado = this.inventario.quitarRecursoAlAzar();
        ladron.recibirRecurso(robado);
    }

    public void agregarConstruccion(Construccion construccion) {
        construcciones.add(construccion);
    }

    public void eliminarConstruccion(Construccion construccion) {
        construcciones.remove(construccion);
    }

    public void construir(Construccion construccion, EspacioConstruible espacio) {
        if (construcciones.size() >= 4) {
            construccion.cobrar(inventario);
        }
        construccion.aplicarCambio(this, espacio);
        this.agregarConstruccion(construccion);
    }

    public void construirPrimerosPoblados(Poblado poblado, Vertice vertice) {
        poblado.aplicarCambioPrimerasColocaciones(this, vertice);
        this.agregarConstruccion(poblado);
    }

    public void recibirRecurso(Recurso recurso) {
        inventario.agregarRecurso(recurso);
    }

    public void comerciarConPuerto(Vertice verticePuerto, List<Recurso> recursosEntregados, Recurso recursoDeseado) {
        verticePuerto.ejecutarComercio(this, recursosEntregados, List.of(recursoDeseado));
    }

    public void aceptarOferta(Jugador oferente, List<Recurso> recursosDeseadosPorOferente, List<Recurso> recursosAEntregarPorOferente) {
        ComercioInterno comercioInterno = new ComercioInterno(oferente, recursosDeseadosPorOferente, recursosAEntregarPorOferente);
        comercioInterno.ejecutar(this);
    }

    public void entregarRecursos(List<Recurso> entregados) {
        inventario.consumirRecurso(entregados);
    }

    public void guardarCartaDesarrollo(CartaDesarrollo cartaDesarrollo, List<Recurso> costoCarta){
        inventario.consumirRecurso(costoCarta);
        cartasDesarrollo.add(cartaDesarrollo);
        cartaDesarrollo.ejecutarAlGuardar(this);
        notificarObservadores();
    }

    public void eliminarCarta(CartaDesarrollo cartaDesarrollo) {
        CartaDesarrollo cartaABorrar;
        for (CartaDesarrollo cartaEnLista : cartasDesarrollo) {
            if (cartaDesarrollo.coincideCon(cartaEnLista)) {
                cartaABorrar = cartaEnLista;
                cartasDesarrollo.remove(cartaABorrar);
                break;
            }
        }
    }

    public void registrarCaballeroJugado(){
        this.cartasCaballeroJugadas =  this.cartasCaballeroJugadas + 1;
    }

    public int conseguirCartasCaballeroJugadas() {
        return cartasCaballeroJugadas;
    }


    public void recibirCartaBonificacion (CartaBonificacion cartaBonificacion){
        this.cartasBonificacion.add(cartaBonificacion);
        notificarObservadores();
    }

    public void perderCartaBonificacion (CartaBonificacion cartaBonificacion){
        this.cartasBonificacion.remove(cartaBonificacion);
        notificarObservadores();
    }

    private int puntosPorConstrucciones() {
        int total = 0;
        for (Construccion construccion : construcciones) {
            total += construccion.puntosVictoria();
        }
        return total;
    }

    public void evaluarSiEsGanador() {
        int puntos = conseguirPuntosDeVictoriaTotales();
        if (puntos >= 10) {
            notificarObservadores();
        }
    }
    
    public int conseguirPuntosDeVictoriaTotales(){
        int puntosConstruccion = puntosPorConstrucciones();
        int puntosCartasBonificacion = cartasBonificacion.size() * 2;

        this.puntosVictoria = puntosConstruccion + puntosCartasBonificacion;

        return puntosConstruccion + puntosCartasBonificacion + puntosVictoriaCartaDesarrollo;
    }

    public String getNombre() { return this.nombre; }

    public Color getColor() {
        return this.color;
    }

    public int getPuntosVictoria() { return this.puntosVictoria; }

    public int getPuntosVictoriaCartaDesarrollo() {
        return this.puntosVictoriaCartaDesarrollo;
    }

    public int getId() {
        return this.id;
    }

    public void sumarPVPorCartaDesarollo() {
        this.puntosVictoriaCartaDesarrollo++;
    }

    public Map<String, Integer> getRecursosInventario(){
        return this.inventario.getRecursos();
    }

    public List<CartaDesarrollo> getCartasDesarrollo(){ return this.cartasDesarrollo; }
}
