package edu.fiuba.algo3.tests_unitarios.tests_juego;

import edu.fiuba.algo3.modelo.cartasDeDesarrollo.PuntoVictoria;
import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import edu.fiuba.algo3.modelo.tablero.Arista;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void test01InventarioIniciaVacio() {
        Jugador jugador = new Jugador(1, "Pepe", Color.BLUE);

        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Madera())));
    }

    @Test
    public void test02AgregarYQuitarRecursosFuncionaCorrectamente() {
        Jugador jugador = new Jugador(1, "Pepe", Color.BLUE);

        jugador.recibirRecurso(new Madera());

        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(new Madera())));
        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Madera())));
    }

    @Test
    public void test03EntregarRecursosLanzaExcepcionSiNoHaySuficiente() {
        Jugador jugador = new Jugador(1, "Pepe", Color.BLUE);
        jugador.recibirRecurso(new Madera());

        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Madera(), new Madera())));
    }

    @Test
    public void test04DescartarRecursosFuncionaCorrectamente() {
        Jugador jugador = new Jugador(1, "Pepe", Color.BLUE);
        for (int i = 0; i < 9; i++) jugador.recibirRecurso(new Lana());

        jugador.descartar();

        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Lana(), new Lana(), new Lana(), new Lana(), new Lana(), new Lana())));
    }

    @Test
    public void test05ConstruirPobladoConsumeLosRecursosCorrectos() {
        Jugador jugador = new Jugador(1, "Constructor", Color.GREEN);

        Vertice vInicial = new Vertice();
        jugador.construirPrimerosPoblados(new Poblado(jugador), vInicial);

        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();
        Vertice v4 = new Vertice();
        
        Arista arista1 = new Arista(vInicial, v2);
        Arista arista2 = new Arista(v2, v3);
        Arista arista3 = new Arista(v3, v4);

        jugador.construir(new Carretera(jugador), arista1);
        jugador.construir(new Carretera(jugador), arista2);
        jugador.construir(new Carretera(jugador), arista3);

        List<Recurso> costoPoblado = List.of(new Madera(), new Ladrillo(), new Lana(), new Grano());
        costoPoblado.forEach(jugador::recibirRecurso);

        assertDoesNotThrow(() -> jugador.construir(new Poblado(jugador), v4));
        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(costoPoblado));
    }

    @Test
    public void test06ValidarQueFuncionenBienPVCarta() {
        Jugador jugador = new Jugador(1, "Constructor", Color.BLUE);
        for (int i = 0; i < 10; i++) {
            jugador.guardarCartaDesarrollo(new PuntoVictoria(), List.of());
        }

        assertTrue(jugador.conseguirPuntosDeVictoriaTotales() == 10);
        assertTrue(jugador.getPuntosVictoriaCartas() == 10);
    }
}
