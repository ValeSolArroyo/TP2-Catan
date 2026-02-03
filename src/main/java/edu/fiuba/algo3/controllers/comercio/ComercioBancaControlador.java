package edu.fiuba.algo3.controllers.comercio;

import edu.fiuba.algo3.controllers.fasesJuego.AccionControlador;
import edu.fiuba.algo3.modelo.comercio.interno.Banca;
import edu.fiuba.algo3.modelo.comercio.interno.ComercioJugador;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.recursos.Recurso;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.comercio.VistaComercioBanca;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpExito;

public class ComercioBancaControlador implements AccionControlador {
    private Juego juego;
    private ContenedorPrincipalVistas contenedor;
    private VistaJuegoGeneral vistaJuego;
    private Recurso recursoDeseado;
    private Recurso recursoAEntregar;
    private VistaComercioBanca vista;

    public ComercioBancaControlador(Juego juego, ContenedorPrincipalVistas contenedor, VistaJuegoGeneral vistaJuego){
        this.juego = juego;
        this.contenedor = contenedor;
        this.vistaJuego = vistaJuego;
    }

    public void setVistaComercio(VistaComercioBanca vista) {
        this.vista = vista;
    }

    @Override
    public void ejecutar(){
        try {
            ComercioJugador comercioJugador = new Banca(recursoAEntregar, recursoDeseado);
            juego.ejecutarComercioJugador(comercioJugador, juego.jugadorActual());
            contenedor.setContenido(vistaJuego);
            PopUpExito.mostrar("Intercambiaste 4 " + recursoAEntregar.getNombreRecurso() + " por 1 " + recursoDeseado.getNombreRecurso());
        } catch (RecursosInsuficientesError e) {
            PopUpError.mostrar(e.getMessage());
            vista.desactivarBotonEjecutar();
            vista.activarBotonesBanca();
        }
    }

    public void obtenerRecursoBanca(Recurso recurso) {
        this.recursoDeseado = recurso;
        vista.activarBotonesElegir();
        vista.desactivarBotonesBanca();
    }

    public void obtenerRecursoAEntregar(Recurso recurso) {
        this.recursoAEntregar = recurso;
        vista.desactivarBotonesElegir();
        vista.activarBotonEjecutar();
    }
}


