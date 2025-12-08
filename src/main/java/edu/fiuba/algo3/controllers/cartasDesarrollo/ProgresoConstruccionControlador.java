package edu.fiuba.algo3.controllers.cartasDesarrollo;

import edu.fiuba.algo3.controllers.fasesJuego.AccionesTableroControlador;
import edu.fiuba.algo3.modelo.cartasDeDesarrollo.ProgresoConstruccion;
import edu.fiuba.algo3.modelo.excepciones.ConstruccionInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.YaHayCarreteraError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juegoCommand.Accion;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.vistas.ContenedorPrincipalVistas;
import edu.fiuba.algo3.vistas.VistaJuegoGeneral;
import edu.fiuba.algo3.vistas.cartasDesarrollo.VistaProgresoConstruccion;
import edu.fiuba.algo3.vistas.componentes.VistaTablero;
import edu.fiuba.algo3.vistas.componentes.popups.PopUpError;
import javafx.scene.paint.Color;
import javafx.stage.Stage; // Importación necesaria para manejar el Stage

import java.util.ArrayList;
import java.util.List;

public class   ProgresoConstruccionControlador implements AccionesTableroControlador {
    private Juego juego;
    private VistaTablero vistaTablero;
    private VistaJuegoGeneral vistaJuego;
    private ContenedorPrincipalVistas contenedor;
    private VistaProgresoConstruccion vistaProgreso;
    private Stage stage;

    private List<Arista> aristas = new ArrayList<>();
    private int carreterasConstruidas = 0;

    public ProgresoConstruccionControlador (Juego juego, VistaTablero vistaTablero, VistaJuegoGeneral vistaJuego, ContenedorPrincipalVistas contenedor, Stage stage){
        this.juego = juego;
        this.vistaTablero = vistaTablero;

        this.vistaTablero.setControlador(this);

        this.vistaJuego = vistaJuego;
        this.contenedor = contenedor;
        this.stage = stage;
    }

    public void setVistaProgreso(VistaProgresoConstruccion vistaProgreso){
        this.vistaProgreso = vistaProgreso;
    }

    public void activarAristas(){
        this.aristas.clear();
        this.vistaTablero.mostrarTodasLasAristas();
        vistaProgreso.desactivarBotonElegir();
        vistaProgreso.desactivarBotonEjecutar();
    }

    @Override
    public void obtenerVertice(Vertice vertice) {
        // No hace nada
    }

    @Override
    public void obtenerArista(Arista arista){
        if (aristas.isEmpty()) {
            this.aristas.add(arista);
            vistaTablero.ocultarAristas();
            vistaProgreso.activarBotonEjecutar();
        }
    }

    @Override
    public void ejecutar(){
        if (aristas.isEmpty()) {
            PopUpError.mostrar("Debe seleccionar una carretera.");
            return;
        }

        Jugador jugador = juego.jugadorActual();
        Color color = jugador.getColor();
        Arista aristaAConstruir = aristas.get(0);

        Accion accion = new ProgresoConstruccion(juego, jugador, List.of(aristaAConstruir));

        try {
            juego.ejecutarAccion(accion);
            carreterasConstruidas++;

        } catch (ConstruccionInvalidaError | YaHayCarreteraError e) {
            PopUpError.mostrar(e.getMessage());
            resetearSeleccionActual();
            vistaProgreso.activarBotonElegir();
            return;
        }

        vistaTablero.dibujarCarreteraEn(aristaAConstruir , color);

        if (carreterasConstruidas == 2) {
            // Se completó la segunda carretera.
            vistaProgreso.desactivarBotonEjecutar();
            vistaProgreso.activarBotonFinalizar();

        } else {
            resetearSeleccionActual();
            vistaProgreso.activarBotonElegir();
        }
    }

    public void finalizarCarta() {
        VistaJuegoGeneral nuevaVistaJuego = new VistaJuegoGeneral(stage, contenedor, juego, vistaTablero);
        contenedor.setContenido(nuevaVistaJuego);
    }

    private void resetearSeleccionActual(){
        aristas.clear();
        vistaTablero.ocultarAristas();
    }
}

