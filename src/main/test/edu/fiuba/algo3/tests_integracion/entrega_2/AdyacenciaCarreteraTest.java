package edu.fiuba.algo3.tests_integracion.entrega_2;

import edu.fiuba.algo3.modelo.excepciones.CaminoNoConectadoError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Vertice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdyacenciaCarreteraTest {

    /*@Test
    public void test01NoSePuedeConstruirCarreteraPorFaltaAdyacencia() {
        // Arrange
        Jugador jugador = new Jugador(1, "J");
        Vertice vertice1 = new Vertice(1);
        Vertice vertice2 = new Vertice(2);
        Arista arista = new Arista(1, vertice1, vertice2);

        // Recursos necesarios para construir Carretera
        jugador.recibir(new Madera());
        jugador.recibir(new Ladrillo());

        // Act & Assert
        assertThrows(CaminoNoConectadoError.class,
                () -> jugador.construirCarretera(arista),
                "No se debería poder construir una carretera si no existe un asentamiento adyacente");
        assertEquals(0, jugador.obtenerPuntosDeVictoria());
    }

    @Test
    public void test02SePuedeConstruirCarreteraConPobladoAdyacente() {
        // Arrange
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

        jugador.recibir(new Madera());
        jugador.recibir(new Ladrillo());

        // Act
        jugador.construirCarretera(arista);

        // Assert
        assertEquals(2, jugador.obtenerCantidadDeConstrucciones());
    }

    @Test
    public void test03SePuedeConstruirCarreteraConCiudadAdyacente() {
        // Arrange
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

        jugador.recibir(new Grano());
        jugador.recibir(new Grano());
        jugador.recibir(new Mineral());
        jugador.recibir(new Mineral());
        jugador.recibir(new Mineral());

        jugador.construirCiudad(vertice1);

        // Recursos necesarios para construir Carretera
        jugador.recibir(new Madera());
        jugador.recibir(new Ladrillo());

        // Act
        jugador.construirCarretera(arista);

        // Assert
        assertEquals(2, jugador.obtenerCantidadDeConstrucciones());
    }

    
    @Test
    public void test04SePuedeConstruirCarreteraAdyacenteAOtraCarretera() {
        // Arrange
        Jugador jugador = new Jugador(1, "J");
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        Arista arista1 = new Arista(1, v1, v2);
        Arista arista2 = new Arista(2, v2, v3);

        v1.agregarVecino(v2); 
        v2.agregarVecino(v1);
        v2.agregarVecino(v3); 
        v3.agregarVecino(v2);

        // Recursos necesarios para poblado (1 madera, 1 ladrillo, 1 lana, 1 grano)
        jugador.recibir(new Madera());
        jugador.recibir(new Ladrillo());
        jugador.recibir(new Lana());
        jugador.recibir(new Grano());

        jugador.construirPoblado(v1);

        // Recursos para primera carretera
        jugador.recibir(new Madera());
        jugador.recibir(new Ladrillo());

        jugador.construirCarretera(arista1);

        // Recursos para segunda carretera
        jugador.recibir(new Madera());
        jugador.recibir(new Ladrillo());

        // Act
        jugador.construirCarretera(arista2);

        // Assert
        assertEquals(3, jugador.obtenerCantidadDeConstrucciones());
    }*/
}