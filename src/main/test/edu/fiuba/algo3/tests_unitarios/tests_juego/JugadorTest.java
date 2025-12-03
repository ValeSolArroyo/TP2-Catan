package edu.fiuba.algo3.tests_unitarios.tests_juego;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void test01InventarioIniciaVacio() {
        Jugador jugador = new Jugador(1, "Pepe", "Rojo");

        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Madera())));
    }

    @Test
    public void test02AgregarYQuitarRecursosFuncionaCorrectamente() {
        Jugador jugador = new Jugador(1, "Pepe", "Rojo");

        jugador.recibirRecurso(new Madera());

        assertDoesNotThrow(() -> jugador.entregarRecursos(List.of(new Madera())));
        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Madera())));
    }

    @Test
    public void test03EntregarRecursosLanzaExcepcionSiNoHaySuficiente() {
        Jugador jugador = new Jugador(1, "Pepe", "Rojo");
        jugador.recibirRecurso(new Madera());

        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Madera(), new Madera())));
    }

    @Test
    public void test04DescartarRecursosFuncionaCorrectamente() {
        Jugador jugador = new Jugador(1, "Pepe", "Rojo");
        for (int i = 0; i < 9; i++) jugador.recibirRecurso(new Lana());

        jugador.descartar();

        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(List.of(new Lana(), new Lana(), new Lana(), new Lana(), new Lana(), new Lana())));
    }

    @Test
    public void test05ConstruirPobladoConsumeLosRecursosCorrectos() {
        Jugador jugador = new Jugador(1, "Constructor", "Verde"); // (Sin color si tu constructor no lo pide)
        List<Recurso> costoPoblado = List.of(new Madera(), new Ladrillo(), new Lana(), new Grano());

        costoPoblado.forEach(jugador::recibirRecurso);

        for (int i = 0; i < 4; i++) {
             jugador.agregarConstruccion(new Carretera(jugador)); 
        }

        assertDoesNotThrow(() -> jugador.construir(new Poblado(jugador), new Vertice()));
        assertThrows(RecursosInsuficientesError.class, () -> jugador.entregarRecursos(costoPoblado));
    }
}
