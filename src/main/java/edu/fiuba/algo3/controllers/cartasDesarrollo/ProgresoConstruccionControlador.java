package edu.fiuba.algo3.controllers.cartasDesarrollo;

import edu.fiuba.algo3.controllers.fasesJuego.AccionesTableroControlador;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.ProgresoConstruccion;
import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.YaHayCarreteraError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.VistaProgresoConstruccion;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class   ProgresoConstruccionControlador implements AccionesTableroControlador {
    private Juego juego;
    private VistaTablero vistaTablero;
    private VistaJuegoGeneral vistaJuego;
    private ContenedorPrincipalVistas contenedor;
    private VistaProgresoConstruccion vistaProgreso;

    private List<Arista> aristas = new ArrayList<>();

    public ProgresoConstruccionControlador (Juego juego, VistaTablero vistaTablero, VistaJuegoGeneral vistaJuego, ContenedorPrincipalVistas contenedor){
        this.juego = juego;
        this.vistaTablero = vistaTablero;

        this.vistaTablero.setControlador(this);

        this.vistaJuego = vistaJuego;
        this.contenedor = contenedor;
    }

    public void setVistaProgreso(VistaProgresoConstruccion vistaProgreso){
        this.vistaProgreso = vistaProgreso;
    }



    public void activarAristas(){
        this.vistaTablero.mostrarAristas(aristas);
        vistaProgreso.desactivarBoton();

    }

    @Override
    public void obtenerVertice(Vertice vertice) {
    }

    @Override
    public void obtenerArista(Arista arista){
        if (aristas.size() == 1) {
            if ( !aristas.contains(arista) ) {
                this.aristas.add(arista);
                vistaTablero.ocultarAristas();
                vistaProgreso.activarBotonEjecutar();
                return;
            }

        }
        this.aristas.add(arista);
    }

    @Override
    public void ejecutar(){
        Jugador jugador = juego.jugadorActual();
        Color color = jugador.getColor();

        Accion accion = new ProgresoConstruccion(juego, jugador, aristas);

        try {
            juego.ejecutarAccion(accion);

        } catch (ConstruccionInvalidaError | YaHayCarreteraError e) {
            PopUpError.mostrar(e.getMessage());
            resetear();
            return;
        }

        vistaTablero.dibujarCarreteraEn(aristas.get(0) , color);
        vistaTablero.dibujarCarreteraEn(aristas.get(1), color);

        contenedor.setContenido(vistaJuego);
    }

    private void resetear(){
        aristas.clear();
        activarAristas();
    }

}

