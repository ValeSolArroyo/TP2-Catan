package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.controllers.cartasDesarrollo.CartasDesarrolloControlador;
import edu.fiuba.algo3.controllers.cartasDesarrollo.ComprarCartaControlador;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaCartasDesarrollo;
import edu.fiuba.algo3.vistas.VistaConstruir;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JuegoControlador {
    private Stage stage;
    private ContenedorPrincipalVistas contenedor;
    private Juego juego;
    private VistaTablero vistaTablero;
    private VistaJuegoGeneral vistaJuego;

    public JuegoControlador(Stage stage, ContenedorPrincipalVistas contenedor, Juego juego, VistaTablero vistaTablero, VistaJuegoGeneral vistaJuego) {
        this.stage = stage;
        this.contenedor = contenedor;
        this.juego = juego;
        this.vistaTablero = vistaTablero;
        this.vistaJuego = vistaJuego;
    }

    public void construir() {
        VistaConstruir vista = new VistaConstruir(this.stage, this.contenedor, this.juego, this.vistaTablero, this.vistaJuego);
        contenedor.setContenido(vista);
    }

    public void comerciar() {
        // Que aparezcan 3 botones:
            // Comerciar con jugadores
            // Comerciar con banca
            // Comerciar con puertos
        // --> SE TIENEN QUE VER LOS PUERTOS Y DEBEN TENER UN TEXTO CORRESPONDIENTE
        // TIPO 3:1, 2:1...

        //ControladorComercio cc = new ControladorComercio(juego, this);
        //VistaComercio vista = new VistaComercio(cc);
        //contenedor.setContenido(vista);
    }

    public void comprarCarta() {
        ComprarCartaControlador controlador = new ComprarCartaControlador(juego, vistaJuego, contenedor);
        controlador.ejecutar();
    }

    public void jugarCarta() {
        Jugador jugadorActual = juego.jugadorActual();
        List<CartaDesarrollo> cartasDesarrollo = jugadorActual.getCartasDesarrollo();

        Map<String, Integer> contadorCartas = new HashMap<>();

        contadorCartas.put("Caballero", 0);
        contadorCartas.put("Progreso de Construccion", 0);
        contadorCartas.put("Progreso de Descubrimiento", 0);
        contadorCartas.put("Progreso Monopolio", 0);

        for (CartaDesarrollo cartaDesarrollo : cartasDesarrollo) {
            String carta = cartaDesarrollo.getCarta();
            contadorCartas.put(carta, contadorCartas.get(carta) + 1);
        }

        CartasDesarrolloControlador controlador = new CartasDesarrolloControlador(stage, juego, vistaTablero, vistaJuego, contenedor);

        VistaCartasDesarrollo vista = new VistaCartasDesarrollo(this.juego, this.contenedor, contadorCartas, vistaJuego,controlador);
        contenedor.setContenido(vista);

    }
}
