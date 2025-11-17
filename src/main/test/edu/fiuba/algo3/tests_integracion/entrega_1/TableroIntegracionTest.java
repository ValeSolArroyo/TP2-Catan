package edu.fiuba.algo3.tests_integracion.entrega_1;

import edu.fiuba.algo3.modelo.Ladron;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.patronHexagono.Hexagono;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TableroIntegracionTest {

    private Tablero tablero1;
    private Tablero tablero2;

    @BeforeEach
    public void setUp() {
        tablero1 = new Tablero();
        tablero2 = new Tablero();
    }

    @Test
    public void test01CantidadCorrectaDeHexagonosYFichas() {
        // Arrange
        int cantidadFichas = 0;

        // Act
        for (Hexagono hexagono : tablero1.obtenerHexagonos()) {
            if (hexagono.obtenerNumeroFicha() != null) {
                cantidadFichas++;
            }
        }

        // Assert
        assertEquals(19, tablero1.obtenerHexagonos().size(),
                "Debe haber 19 hexágonos en el tablero");

        assertEquals(18, cantidadFichas,
                "Debe haber 18 fichas con número (el desierto no tiene)");
    }

    @Test
    public void test02CombinacionAleatoriaDeTerrenosYFichas() {
        // Act
        List<String> combinacion1 = obtenerFormatoTablero(tablero1);
        List<String> combinacion2 = obtenerFormatoTablero(tablero2);

        // Assert
        assertNotEquals(combinacion1, combinacion2,
                "Las combinaciones no deberían ser iguales (el tablero debe ser aleatorio)");
    }

    @Test
    public void test03ValoresValidosDeTerrenosYNumeros() {
        // Arrange
        List<Integer> numerosValidos = List.of(2, 3, 4, 5, 6, 8, 9, 10, 11, 12);

        // Act y Assert
        for (Hexagono hexagono : tablero1.obtenerHexagonos()) {
            assertNotNull(hexagono.obtenerTipoTerreno(),
                    "Cada hexágono debe tener un tipo de terreno asignado");

            Integer numero = hexagono.obtenerNumeroFicha();

            if (numero != null) {
                assertTrue(numerosValidos.contains(numero),
                        "Número inválido encontrado en un hexágono");
            }
        }
    }

    @Test
    public void test04HexagonoNoProduceSiEstaElLadron() {
        // Arrange
        Hexagono hexagono = new Hexagono(1, "Bosque", 8);
        Ladron ladron = new Ladron();

        // Act
        ladron.moverA(hexagono);

        String recurso = hexagono.producirRecurso(ladron);

        // Assert
        assertNull(recurso, "El hexágono no debe producir si el ladrón está en él");
    }

    @Test
    public void test05HexagonoProduceRecursoCorrectoSiElLadronNoEsta() {
        // Arrange
        Hexagono hexagono = new Hexagono(2, "Bosque", 8);
        Hexagono otroHexagono = new Hexagono(3, "Campo", 4);
        Ladron ladron = new Ladron();

        // Act
        ladron.moverA(otroHexagono); // Está en otro hexágono

        String recurso = hexagono.producirRecurso(ladron);

        // Assert
        assertEquals("Madera", recurso,
                "El hexágono debe producir el recurso correspondiente si el ladrón no está presente");
    }

    private List<String> obtenerFormatoTablero(Tablero tablero) {
        List<String> listaTablero = new ArrayList<>();
        for (Hexagono hexagono : tablero.obtenerHexagonos()) {
            listaTablero.add(
                    hexagono.obtenerTipoTerreno() + "-" + hexagono.obtenerNumeroFicha()
            );
        }
        return listaTablero;
    }
}