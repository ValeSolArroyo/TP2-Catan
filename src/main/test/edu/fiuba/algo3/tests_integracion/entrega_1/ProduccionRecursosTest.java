package edu.fiuba.algo3.tests_integracion.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.terrenos.Bosque;
import edu.fiuba.algo3.modelo.terrenos.Pastizal;
import edu.fiuba.algo3.modelo.terrenos.Terreno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProduccionRecursosTest {
    private Jugador jugador1;
    private Vertice vertice1;
    private Hexagono hexagono1;
    private Hexagono hexagono2;

    @BeforeEach
    public void setUp() {
        jugador1 = new Jugador(1, "Lolo");
        vertice1 = new Vertice(1);

        Terreno bosque = new Bosque();
        Terreno pastizal = new Pastizal();

        hexagono1 = new Hexagono(1, bosque, 5);
        hexagono2 = new Hexagono(2, pastizal, 5);
    }

    @Test
    public void test01ProduccionCorrectaDeRecursosConPoblado() {
        hexagono1.agregarVertice(vertice1);
        hexagono2.agregarVertice(vertice1);
        jugador1.construirPobladoEn(vertice1);

        int recursosAntes = jugador1.obtenerCantidadTotalDeRecursos();

        hexagono1.producirRecursos(5);
        hexagono2.producirRecursos(5);

        int recursosDespues = jugador1.obtenerCantidadTotalDeRecursos();
        assertEquals(2, recursosDespues - recursosAntes);
    }

    @Test
    public void test02ProduccionCorrectaDeRecursosConCiudad() {
        hexagono1.agregarVertice(vertice1);
        hexagono2.agregarVertice(vertice1);
        jugador1.construirPobladoEn(vertice1);

        jugador1.recibir(new Mineral());
        jugador1.recibir(new Mineral());
        jugador1.recibir(new Mineral());
        jugador1.recibir(new Grano());
        jugador1.recibir(new Grano());

        jugador1.construirCiudad(vertice1);

        int recursosAntes = jugador1.obtenerCantidadTotalDeRecursos();

        hexagono1.producirRecursos(5);
        hexagono2.producirRecursos(5);

        int recursosDespues = jugador1.obtenerCantidadTotalDeRecursos();
        assertEquals(4, recursosDespues - recursosAntes);
    }
}