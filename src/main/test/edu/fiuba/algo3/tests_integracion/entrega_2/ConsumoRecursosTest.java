package edu.fiuba.algo3.tests_integracion.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.CaminoNoConectadoError;
import edu.fiuba.algo3.modelo.excepciones.RecursosInsuficientesError;
import edu.fiuba.algo3.modelo.recursos.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumoRecursosTest {

    @Test
    public void test01ConstruirPobladoConsumeRecursosCorrectos() {
        Jugador jugador = new Jugador(1, "J");
        Vertice vertice = new Vertice(1);

        // Recursos necesarios para poblado (1 madera, 1 ladrillo, 1 lana, 1 grano)
        jugador.recibir(new Madera());
        jugador.recibir(new Ladrillo());
        jugador.recibir(new Lana());
        jugador.recibir(new Grano());

        int recursosAntes = jugador.obtenerCantidadTotalDeRecursos();

        jugador.construirPoblado(vertice);

        int recursosDespues = jugador.obtenerCantidadTotalDeRecursos();
        assertEquals(0, recursosDespues);
        assertEquals(4, recursosAntes - recursosDespues);
    }

    @Test
    public void test02ConstruirCiudadConsumeRecursosCorrectos() {
        Jugador jugador = new Jugador(1, "M");
        Vertice vertice = new Vertice(1);

        // Construir poblado primero
        jugador.construirPobladoEn(vertice);

        // Recursos necesarios para ciudad (2 grano, 3 mineral)
        jugador.recibir(new Grano());
        jugador.recibir(new Grano());
        jugador.recibir(new Mineral());
        jugador.recibir(new Mineral());
        jugador.recibir(new Mineral());

        int recursosAntes = jugador.obtenerCantidadTotalDeRecursos();

        jugador.construirCiudad(vertice);

        int recursosDespues = jugador.obtenerCantidadTotalDeRecursos();
        assertEquals(0, recursosDespues);
        assertEquals(5, recursosAntes - recursosDespues);
    }

    @Test
    public void test03NoPuedeConstruirPobladoSinRecursosSuficientes() {
        Jugador jugador = new Jugador(1, "P");
        Vertice vertice = new Vertice(1);

        jugador.recibir(new Madera());
        jugador.recibir(new Ladrillo());
        jugador.recibir(new Lana());

        assertThrows(RecursosInsuficientesError.class,
                () -> jugador.construirPoblado(vertice),
                "No se debería poder construir un poblado sin recursos suficientes");
    }

    @Test
    public void test04NoPuedeConstruirCiudadSinRecursosSuficientes() {
        Jugador jugador = new Jugador(1, "Ana");
        Vertice vertice = new Vertice(1);

        jugador.construirPobladoEn(vertice);

        jugador.recibir(new Grano());
        jugador.recibir(new Grano());
        jugador.recibir(new Mineral());
        jugador.recibir(new Mineral());

        assertThrows(RecursosInsuficientesError.class,
                () -> jugador.construirCiudad(vertice),
                "No se debería poder construir una ciudad sin recursos suficientes");
    }

    @Test
    public void test05JugadorPuedeConstruirVariasConstruccionesConsecutivas() {
        // Arrange
        Jugador jugador = new Jugador(1, "Luis");
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);

        v1.agregarVecino(v2);
        v2.agregarVecino(v1);
        v2.agregarVecino(v3);
        v3.agregarVecino(v2);

        for (int i = 0; i < 2; i++) {
            jugador.recibir(new Madera());
            jugador.recibir(new Ladrillo());
            jugador.recibir(new Lana());
            jugador.recibir(new Grano());
        }

        assertEquals(8, jugador.obtenerCantidadTotalDeRecursos());

        jugador.construirPoblado(v1);
        jugador.construirPoblado(v3);

        assertEquals(0, jugador.obtenerCantidadTotalDeRecursos());
        assertEquals(2, jugador.obtenerCantidadDeConstrucciones());
    }

    @Test
    public void test06JugadorPuedeConstruirPobladosYCiudadConsecutivamente() {
        Jugador jugador = new Jugador(1, "L");
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);

        v1.agregarVecino(v2);
        v2.agregarVecino(v1);
        v2.agregarVecino(v3);
        v3.agregarVecino(v2);

        // Recursos para 2 poblados (8 recursos)
        for (int i = 0; i < 2; i++) {
            jugador.recibir(new Madera());
            jugador.recibir(new Ladrillo());
            jugador.recibir(new Lana());
            jugador.recibir(new Grano());
        }

        // Recursos para 1 ciudad (5 recursos)
        jugador.recibir(new Grano());
        jugador.recibir(new Grano());
        jugador.recibir(new Mineral());
        jugador.recibir(new Mineral());
        jugador.recibir(new Mineral());

        assertEquals(13, jugador.obtenerCantidadTotalDeRecursos());

        jugador.construirPoblado(v1);
        assertEquals(9, jugador.obtenerCantidadTotalDeRecursos());

        jugador.construirPoblado(v3);
        assertEquals(5, jugador.obtenerCantidadTotalDeRecursos());

        jugador.construirCiudad(v1);

        assertEquals(0, jugador.obtenerCantidadTotalDeRecursos());
        assertEquals(2, jugador.obtenerCantidadDeConstrucciones());
    }

    @Test
    public void test07RecursosNoSeConsumenEnConstruccionInicial() {
        Jugador jugador = new Jugador(1, "Sofia");
        Vertice vertice = new Vertice(1);

        int recursosAntes = jugador.obtenerCantidadTotalDeRecursos();

        jugador.construirPobladoEn(vertice);

        int recursosDespues = jugador.obtenerCantidadTotalDeRecursos();
        assertEquals(recursosAntes, recursosDespues);
        assertEquals(1, jugador.obtenerCantidadDeConstrucciones());
    }

    @Test
    public void test08ConstruirCarreteraConsumeRecursosCorrectos() {
        Jugador jugador = new Jugador(1, "J");
        Vertice vertice1 = new Vertice(1);
        Vertice vertice2 = new Vertice(2);
        Arista arista = new Arista(1, vertice1, vertice2);

        // Recursos necesarios para poblado (1 madera, 1 ladrillo, 1 lana, 1 grano)
        jugador.recibir(new Madera());
        jugador.recibir(new Ladrillo());
        jugador.recibir(new Lana());
        jugador.recibir(new Grano());

        jugador.construirPoblado(vertice1);

        //Recursos necesarios para construir Carretera
        jugador.recibir(new Madera());
        jugador.recibir(new Ladrillo());

        int recursosAntes = jugador.obtenerCantidadTotalDeRecursos();

        jugador.construirCarretera(arista);

        int recursosDespues = jugador.obtenerCantidadTotalDeRecursos();
        assertEquals(0, recursosDespues);
        assertEquals(2, recursosAntes - recursosDespues);
    }

    @Test
    public void test09NoPuedeConstruirCarreteraSinRecursosSuficientes() {
        Jugador jugador = new Jugador(1, "J");
        Vertice vertice1 = new Vertice(1);
        Vertice vertice2 = new Vertice(2);
        Arista arista = new Arista(1, vertice1, vertice2);

        // Recursos necesarios para poblado (1 madera, 1 ladrillo, 1 lana, 1 grano)
        jugador.recibir(new Madera());
        jugador.recibir(new Ladrillo());
        jugador.recibir(new Lana());
        jugador.recibir(new Grano());

        jugador.construirPoblado(vertice1);


        assertThrows(RecursosInsuficientesError.class,
                () -> jugador.construirCarretera(arista),
                "No se debería poder construir una carretera sin recursos suficientes");
    }

    @Test
    public void test10NoSePuedeConstruirCarreteraPorFaltaAdyacencia() {
        Jugador jugador = new Jugador(1, "J");
        Vertice vertice1 = new Vertice(1);
        Vertice vertice2 = new Vertice(2);
        Arista arista = new Arista(1, vertice1, vertice2);

        //Recursos necesarios para construir Carretera
        jugador.recibir(new Madera());
        jugador.recibir(new Ladrillo());

        assertThrows(CaminoNoConectadoError.class,
                () -> jugador.construirCarretera(arista),
                "No se debería poder construir una carreterra si no existe un asentamiento adyacente");

    }


}