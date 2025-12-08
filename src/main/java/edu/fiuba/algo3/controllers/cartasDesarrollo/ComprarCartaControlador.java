package edu.fiuba.algo3.controllers.cartasDesarrollo;

import edu.fiuba.algo3.controllers.fasesJuego.AccionControlador;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.excepciones.NoHayCartasDesarrolloError;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.AccionComprarCartaDesarrollo;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.componentes.VistaMostrarCarta;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;

public class ComprarCartaControlador implements AccionControlador {
    private Juego juego;
    private VistaJuegoGeneral vistaJuego;
    private ContenedorPrincipalVistas contenedor;

    public ComprarCartaControlador(Juego juego, VistaJuegoGeneral vistaJuego, ContenedorPrincipalVistas contenedor) {
        this.juego = juego;
        this.vistaJuego = vistaJuego;
        this.contenedor = contenedor;
    }

    @Override
    public void ejecutar() {
        AccionComprarCartaDesarrollo accion = new AccionComprarCartaDesarrollo(juego);
        try {
            juego.ejecutarAccion(accion);
        } catch (RecursosInsuficientesError | NoHayCartasDesarrolloError e) {
            PopUpError.mostrar(e.getMessage());
            return;
        }

        CartaDesarrollo carta = juego.getCartaComprada();
        String nombreCarta = carta.getCarta();
        String textoAMostrar = "¡Obtuviste la Carta " + nombreCarta + "!";
        VistaMostrarCarta vistaCartas = new VistaMostrarCarta(textoAMostrar, nombreCarta, contenedor, vistaJuego);

        contenedor.setContenido(vistaCartas);

    }
}