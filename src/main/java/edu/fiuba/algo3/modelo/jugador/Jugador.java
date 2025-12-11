package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.cartasBonificacion.CartaBonificacion;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.comercio.interno.ComercioInterno;
import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.tablero.Arista;
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
    private int puntosVictoriaCartas;
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
        this.puntosVictoriaCartas = 0;
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
        this.puntosVictoria += construccion.puntosVictoria();
    }

    public void eliminarConstruccion(Construccion construccion) {
        construcciones.remove(construccion);
    }

    public void construir(Construccion construccion, EspacioConstruible espacio) {
        construccion.aplicarCambio(this, espacio);

        this.agregarConstruccion(construccion);
    }

    public void cobrarConstruccion(Construccion construccion) {
        construccion.cobrar(inventario);
    }

    public void construirPrimerosPoblados(Poblado poblado, Vertice vertice) {
        poblado.aplicarCambioPrimerasColocaciones(this, vertice);
        this.agregarConstruccion(poblado);
    }

    public void construirPrimerasCarreteras(Carretera carretera, Arista arista) {
        arista.construirCarreteraPrimerasColocaciones(this, carretera);
        this.agregarConstruccion(carretera);
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

    public void entregaMonopolio(Recurso recursoDeseado, Jugador jugador) {

        List<Recurso> recursosAEntregar = inventario.consumirTodosRecursos(recursoDeseado);
        for(Recurso recurso: recursosAEntregar) {
            jugador.recibirRecurso(recurso);
        }

    }

    public int conseguirCartasCaballeroJugadas() {
        return cartasCaballeroJugadas;
    }

    public void recibirCartaBonificacion(CartaBonificacion cartaBonificacion){
        this.cartasBonificacion.add(cartaBonificacion);
        this.puntosVictoriaCartas += 2;
        notificarObservadores();
    }

    public void perderCartaBonificacion(CartaBonificacion cartaBonificacion){
        this.cartasBonificacion.remove(cartaBonificacion);
        this.puntosVictoriaCartas -= 2;
        notificarObservadores();
    }

    public boolean evaluarSiEsGanador() {
        int puntos = conseguirPuntosDeVictoriaTotales();
        if (puntos >= 10) {
            return true;
        }
        return false;
    }

    public int conseguirPuntosDeVictoriaTotales(){
        return this.puntosVictoria + this.puntosVictoriaCartas;
    }

    public void sumarCartaCaballeroJugada() {
        this.cartasCaballeroJugadas += 1;
    }

    public String getNombre() { return this.nombre; }

    public Color getColor() {
        return this.color;
    }

    public int getPuntosVictoria() { return this.puntosVictoria; }

    public int getPuntosVictoriaCartas() {
        return this.puntosVictoriaCartas;
    }

    public int getId() {
        return this.id;
    }

    public void sumarPVPorCartaDesarollo() {
        this.puntosVictoriaCartas++;
    }

    public Map<String, Integer> getRecursosInventario(){
        return this.inventario.getRecursos();
    }

    public List<CartaDesarrollo> getCartasDesarrollo() { return this.cartasDesarrollo; }

    public List<CartaBonificacion> getCartasBonificacion() { return this.cartasBonificacion; }

    public void habilitarCartasCompradas() {
        for (CartaDesarrollo carta: cartasDesarrollo) {
            carta.habilitarCarta();
        }
    }

}
