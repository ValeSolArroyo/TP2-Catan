package edu.fiuba.algo3.tests_integracion.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.construcciones.Construccion;
import edu.fiuba.algo3.modelo.Hexagono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProduccionIntegracionTest {

    private Jugador jugador1;
    private Vertice vertice1;
    private Hexagono hexagono1;
    private Hexagono hexagono2;

    @BeforeEach
    public void setUp() {
        // Arrange
        jugador1 = new Jugador(1, "Lolo");
        vertice1 = new Vertice(1);
        hexagono1 = new Hexagono(1, "Madera", 5);
        hexagono2 = new Hexagono(2, "Lana", 5);
    }

    @Test
    public void test01ProduccionCorrectaDeRecursosConPoblado() {
        // Act
        vertice1.agregarHexagono(hexagono1);
        vertice1.agregarHexagono(hexagono2);

        jugador1.construirPoblado(vertice1);

        int numeroLanzado = 5;
        for (Hexagono hexagono : vertice1.obtenerHexagonosAdyacentes()) {
            if (hexagono.obtenerNumeroFicha() == numeroLanzado && !hexagono.esDesierto()) {
                Construccion construccion = vertice1.obtenerConstruccion();
                if (construccion != null) {
                    jugador1.agregarRecursos(hexagono.obtenerTipoTerreno(), construccion.recursosProducidos());
                }
            }
        }

        // Assert
        assertEquals(1, jugador1.obtenerCantidadDeRecurso("Madera"),
                "Debe recibir 1 de madera por el poblado");
        assertEquals(1, jugador1.obtenerCantidadDeRecurso("Lana"),
                "Debe recibir 1 de lana por el poblado");
    }

    @Test
    public void test02ProduccionCorrectaDeRecursosConCiudad() {
        // Act
        vertice1.agregarHexagono(hexagono1);
        vertice1.agregarHexagono(hexagono2);

        jugador1.construirPoblado(vertice1);
        jugador1.construirCiudad(vertice1);

        int numeroLanzado = 5;
        for (Hexagono hexagono : vertice1.obtenerHexagonosAdyacentes()) {
            if (hexagono.obtenerNumeroFicha() == numeroLanzado && !hexagono.esDesierto()) {
                Construccion construccion = vertice1.obtenerConstruccion();
                if (construccion != null) {
                    jugador1.agregarRecursos(hexagono.obtenerTipoTerreno(), construccion.recursosProducidos());
                }
            }
        }

        // Assert
        assertEquals(2, jugador1.obtenerCantidadDeRecurso("Madera"),
                "Debe recibir 2 de madera por la ciudad");
        assertEquals(2, jugador1.obtenerCantidadDeRecurso("Lana"),
                "Debe recibir 2 de lana por la ciudad");
    }
}
