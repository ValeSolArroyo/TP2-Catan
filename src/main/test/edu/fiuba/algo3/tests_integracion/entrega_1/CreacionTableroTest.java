package edu.fiuba.algo3.tests_integracion.entrega_1;

import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.tableroFactory.TableroCatanFactory;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Hexagono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class CreacionTableroTest {

    /*@Test
    public void test01VerificarTableroTiene19Hexagonos() {
        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();

        assertEquals(19, tablero.obtenerHexagonos().size());
    }

    @Test
    public void test02VerificarCantidadCorrectaDeCadaTipoDeTerreno() {
        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();

        // Arrange
        Map<Object, Long> conteoTerrenos = tablero.obtenerHexagonos().stream()
                .map(hexagono -> hexagono.obtenerTipoTerreno().getClass().getSimpleName())
                .collect(Collectors.groupingBy(terreno -> terreno, Collectors.counting()));

        // Assert
        assertEquals(4, conteoTerrenos.getOrDefault("Bosque", 0L), "Debe haber 4 Bosques (Madera).");
        assertEquals(4, conteoTerrenos.getOrDefault("Campo", 0L), "Debe haber 4 Campos (Grano).");
        assertEquals(4, conteoTerrenos.getOrDefault("Pastizal", 0L), "Debe haber 4 Pastizales (Lana).");
        assertEquals(3, conteoTerrenos.getOrDefault("Colina", 0L), "Debe haber 3 Colinas (Ladrillo).");
        assertEquals(3, conteoTerrenos.getOrDefault("Montaña", 0L), "Debe haber 3 Montañas (Mineral).");
        assertEquals(1, conteoTerrenos.getOrDefault("Desierto", 0L), "Debe haber 1 Desierto.");
    }


    @Test
    public void test03VerificarFichasNumericasValidasYCompletas() {
        // Arrange y Act
        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();
        List<Integer> numerosValidos = new ArrayList<>(List.of(
                2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12
        ));

        List<Integer> fichasAsignadas = tablero.obtenerHexagonos().stream()
                .map(Hexagono::obtenerNumeroFicha)
                .filter(num -> num != 0)
                .collect(Collectors.toList());

        // Assert
        assertEquals(18, fichasAsignadas.size(), "Debe haber exactamente 18 fichas numeradas.");

        assertFalse(fichasAsignadas.contains(7), "Ningún hexágono de recurso debe tener la ficha 7.");


        fichasAsignadas.sort(null);
        numerosValidos.sort(null);

        assertEquals(numerosValidos, fichasAsignadas,
                "El conjunto de fichas asignadas debe coincidir con el conjunto estándar de Catan.");
    }

    @Test
    public void test04VerificarAsignacionAleatoriaDeTerrenosYFichas() {
        // Arrange
        TableroCatanFactory factory = new TableroCatanFactory();

        Tablero tableroA = factory.crearTablero();
        Tablero tableroB = factory.crearTablero();

        // Act
        List<String> formatoTableroA = obtenerFormato(tableroA);
        List<String> formatoTableroB = obtenerFormato(tableroB);

        // Assert
        assertNotEquals(formatoTableroA, formatoTableroB,
                "Dos tableros creados independientemente no deben tener la misma combinación Terreno-Ficha.");
    }

    // --- Método auxiliar ---
    private List<String> obtenerFormato(Tablero tablero) {
        return tablero.obtenerHexagonos().stream()
                .map(hexagono -> hexagono.obtenerTipoTerreno().getClass().getSimpleName() + "-" + hexagono.obtenerNumeroFicha())
                .collect(Collectors.toList());
    }*/

}