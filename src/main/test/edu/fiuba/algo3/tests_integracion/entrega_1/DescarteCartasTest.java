package edu.fiuba.algo3.tests_integracion.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.recursos.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DescarteCartasTest {

    @Test
    public void test01JugadorDescartaMitadAlSacarSiete() {
        Jugador jugador = new Jugador(1, "P");
        for (int i = 0; i < 10; i++) {
            jugador.recibir(new Madera());
        }

        jugador.descartarMitadDeRecursos();

        assertEquals(5, jugador.obtenerCantidadTotalDeRecursos());
    }

    @Test
    public void test02JugadorNoDescartaSiTiene7oMenos() {
        Jugador jugador = new Jugador(1, "P");
        for (int i = 0; i < 7; i++) {
            jugador.recibir(new Lana());
        }

        int recursosAntes = jugador.obtenerCantidadTotalDeRecursos();

        jugador.descartarMitadDeRecursos();

        assertEquals(recursosAntes, jugador.obtenerCantidadTotalDeRecursos());
    }
}