package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Hexagono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AsignacionAleatoriaTableroTest {

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
        for (Hexagono h : tablero1.obtenerHexagonos()) {
            if (h.obtenerNumeroFicha() != null) {
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

        boolean sonTodosValidos = true;

        // Act
        for (Hexagono h : tablero1.obtenerHexagonos()) {
            assertNotNull(h.obtenerTipoTerreno(), "Cada hexágono debe tener un tipo de terreno asignado");

            Integer numero = h.obtenerNumeroFicha();
            if (numero != null && !numerosValidos.contains(numero)) {
                sonTodosValidos = false;
                break;
            }
        }

        // Assert
        assertTrue(sonTodosValidos, "Todos los números de ficha deben estar dentro de los valores válidos");
    }

    private List<String> obtenerFormatoTablero(Tablero tablero) {
        List<String> listaTablero = new ArrayList<>();
        for (Hexagono h : tablero.obtenerHexagonos()) {
            listaTablero.add(h.obtenerTipoTerreno() + "-" + h.obtenerNumeroFicha());
        } // Guarda cómo está conformado el tablero para compararlos después
        return listaTablero;
    }
}
