package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.controllers.fasesJuego.AccionControlador;
import edu.fiuba.algo3.modelo.comercio.Banca;
import edu.fiuba.algo3.modelo.comercio.ComercioJugador;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaComercioBanca;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;

public class ComercioBancaControlador implements AccionControlador {
    private Juego juego;
    private ContenedorPrincipalVistas contenedor;
    private VistaJuegoGeneral vistaJuego;
    private Recurso recursoDeseado;
    private Recurso recursoAEntregar;
    private boolean recursoDeseadoElegido = false;
    private VistaComercioBanca vista;



    public ComercioBancaControlador(Juego juego, ContenedorPrincipalVistas contenedor, VistaJuegoGeneral vistaJuego){
        this.juego = juego;
        this.contenedor = contenedor;
        this.vistaJuego = vistaJuego;

    }

    public void setVistaComercio(VistaComercioBanca vista) {
        this.vista = vista;
    }


    public void elegirRecurso(Recurso recurso){
        if (!recursoDeseadoElegido){
            this.recursoDeseado = recurso;
            this.recursoDeseadoElegido = true;
            vista.activarEntrega();
        }
        this.recursoAEntregar = recurso;
        vista.activarBotonEjecutar();

    }

    @Override
    public void ejecutar(){
        ComercioJugador comercioJugador = new Banca(recursoDeseado, recursoAEntregar);
        juego.ejecutarComercioJugador(comercioJugador);
        contenedor.setContenido(vistaJuego);
    }


}


