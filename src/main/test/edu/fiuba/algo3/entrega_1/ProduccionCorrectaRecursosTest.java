package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProduccionCorrectaRecursosTest {

    private Jugador jugador1;
    private Vertice v1;
    private Hexagono hex1;
    private Hexagono hex2;

    @BeforeEach
    public void setUp() {
        jugador1 = new Jugador(1, "Lolo");
        v1 = new Vertice(1);
        hex1 = new Hexagono(1, "Madera", 5);
        hex2 = new Hexagono(2, "Lana", 5);
    }

    @Test
    public void test01ProduccionCorrectaDeRecursosConPoblado() {
        v1.agregarHexagono(hex1);
        v1.agregarHexagono(hex2);

        jugador1.construirPoblado(v1);

        int numeroLanzado = 5;
        for (Hexagono h : v1.obtenerHexagonosAdyacentes()) {
            if (h.obtenerNumeroFicha() == numeroLanzado && !h.esDesierto()) {
                Construccion construccion = v1.obtenerConstruccion();
                if (construccion != null) {
                    jugador1.agregarRecursos(h.obtenerTipoTerreno(), construccion.recursosProducidos());
                }
            }
        }

        assertEquals(1, jugador1.obtenerCantidadDeRecurso("Madera"),
                "Debe recibir 1 de madera por el poblado");
        assertEquals(1, jugador1.obtenerCantidadDeRecurso("Lana"),
                "Debe recibir 1 de lana por el poblado");
    }

    @Test
    public void test02ProduccionCorrectaDeRecursosConCiudad() {
        v1.agregarHexagono(hex1);
        v1.agregarHexagono(hex2);

        jugador1.construirPoblado(v1);
        jugador1.construirCiudad(v1);

        int numeroLanzado = 5;
        for (Hexagono h : v1.obtenerHexagonosAdyacentes()) {
            if (h.obtenerNumeroFicha() == numeroLanzado && !h.esDesierto()) {
                Construccion construccion = v1.obtenerConstruccion();
                if (construccion != null) {
                    jugador1.agregarRecursos(h.obtenerTipoTerreno(), construccion.recursosProducidos());
                }
            }
        }

        assertEquals(2, jugador1.obtenerCantidadDeRecurso("Madera"),
                "Debe recibir 2 de madera por la ciudad");
        assertEquals(2, jugador1.obtenerCantidadDeRecurso("Lana"),
                "Debe recibir 2 de lana por la ciudad");
    }
}
