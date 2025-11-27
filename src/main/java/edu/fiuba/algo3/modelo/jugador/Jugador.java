package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.cartasBonificacion.CartaBonificacion;
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
    private List<CartaBonificacion> cartasBonificacion;
    private int cartasCaballeroJugadas;


    public Jugador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.puntosVictoria = 0;
        this.inventario = new Inventario();
        this.construcciones = new ArrayList<>();
        this.cartasDesarrollo = new ArrayList<>();
        this.cartasBonificacion = new ArrayList<>();
        this.cartasCaballeroJugadas = 0;
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

    public void comerciarCartasRecurso(Comercio comercio, List<Recurso> recursosEntregados, Recurso recursoDeseado) {
        comercio.validar(this, recursosEntregados, List.of(recursoDeseado));
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
        comercioInterno.validar(this, recursosAEntregarPorOferente, recursosDeseadosPorOferente);
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

    public void  registrarCaballeroJugado(){
        this.cartasCaballeroJugadas =  this.cartasCaballeroJugadas + 1;
    }


    public int conseguirCartasCaballeroJugadas(){
        return cartasCaballeroJugadas;
    }

    public int conseguirRutaMasLarga(){
        return 0; //implmentar para que consiga la ruta más larga y devuelva la cant de carreteras
    }

    public void recibirCartaBonificacion (CartaBonificacion cartaBonificacion){
        this.cartasBonificacion.add(cartaBonificacion);

    }

    public void perderCartaBonificacion (CartaBonificacion cartaBonificacion){
        this.cartasBonificacion.remove(cartaBonificacion);
    }

    private int puntosPorConstrucciones() {
        int total = 0;
        for (Construccion construccion : construcciones) {
            total += construccion.puntosVictoria();
        }
        return total;
    }

    private int puntosPorCartasDesarrollo(){
        int total = 0;
        for (CartaDesarrollo carta : cartasDesarrollo) {
            total += carta.puntosVictoria();
        }
        return total;

    }

    public void conseguirPuntosDeVictoria(){
        int puntosConstruccion = puntosPorConstrucciones();
        int puntosCartasPV = puntosPorCartasDesarrollo();
        int puntosCartasBonificacion = cartasBonificacion.size() * 2;

        this.puntosVictoria = puntosConstruccion + puntosCartasBonificacion; //los PV de la carta desarrollo estan ocultos

        int puntosVictoriaFinales = puntosConstruccion + puntosCartasPV + puntosCartasBonificacion;


    }



}
