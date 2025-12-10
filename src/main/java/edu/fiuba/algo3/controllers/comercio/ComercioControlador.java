package edu.fiuba.algo3.controllers.comercio;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.comercio.VistaComercioBanca;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.comercio.VistaComercioInterno;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import java.util.List;
import java.util.Map;

public class ComercioControlador {
    private Juego juego;
    private ContenedorPrincipalVistas contenedor;
    private VistaTablero vistaTablero;
    private VistaJuegoGeneral vistaJuego;
    private List<Recurso> recursos = List.of(new Madera(), new Lana(), new Ladrillo(), new Grano(), new Mineral());
    private Map<String, Integer> recursosJugadorActual;

    public ComercioControlador(Juego juego, VistaJuegoGeneral vistaJuego, ContenedorPrincipalVistas contenedor, VistaTablero vistaTablero){
        this.juego = juego;
        this.contenedor = contenedor;
        this.vistaJuego = vistaJuego;
        this.vistaTablero = vistaTablero;

        Jugador jugadorActual = juego.jugadorActual();
        this.recursosJugadorActual = jugadorActual.getRecursosInventario();
    }

    public void comerciarConBanca(){
        ComercioBancaControlador controlador = new  ComercioBancaControlador(juego, contenedor, vistaJuego);
        VistaComercioBanca vista = new VistaComercioBanca(contenedor, controlador, recursos, vistaJuego, recursosJugadorActual);
        controlador.setVistaComercio(vista);

        contenedor.setContenido(vista);
    }

    public void comerciarConJugadores(){
        ComercioInternoControlador controlador = new ComercioInternoControlador(juego, contenedor, vistaJuego, recursos, recursosJugadorActual);
        VistaComercioInterno vista = new VistaComercioInterno(contenedor, controlador, recursos, vistaJuego, recursosJugadorActual);
        controlador.setVistaComercio(vista);

        contenedor.setContenido(vista);
    }

    public void comerciarConPuertos(){
        //ComercioPuertoControlador controlador = new  ComercioPuertoControlador(juego, contenedor, vistaJuego);
        //VistaComercioPuerto vista = new VistaComercioPuerto(controlador);
        //contenedor.setContenido(vista);

    }


}

