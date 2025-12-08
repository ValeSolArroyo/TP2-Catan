package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaComercioBanca;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaProgresoMonopolio;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;

public class ComercioControlador {
    private Juego juego;
    private ContenedorPrincipalVistas contenedor;
    private VistaTablero vistaTablero;
    private VistaJuegoGeneral vistaJuego;

    public ComercioControlador(Juego juego, VistaJuegoGeneral vistaJuego, ContenedorPrincipalVistas contenedor, VistaTablero vistaTablero){
        this.juego = juego;
        this.contenedor = contenedor;
        this.vistaJuego = vistaJuego;
        this.vistaTablero = vistaTablero;
    }

    public void comerciarConBanca(){
        ComercioBancaControlador controlador = new  ComercioBancaControlador(juego, contenedor, vistaJuego);

        VistaComercioBanca vista = new VistaComercioBanca(controlador);
        controlador.setVistaComercio(vista);

        contenedor.setContenido(vista);

    }

    public void comerciarConJugadores(){
        //ComercioInternoControlador controlador = new  ComercioInternoControlador(juego, contenedor, vistaJuego);
        //VistaComercioInterno vista = new VistaComercioInterno(controlador);
        //contenedor.setContenido(vista);

    }

    public void comerciarConPuertos(){
        //ComercioPuertoControlador controlador = new  ComercioPuertoControlador(juego, contenedor, vistaJuego);
        //VistaComercioPuerto vista = new VistaComercioPuerto(controlador);
        //contenedor.setContenido(vista);

    }


}

