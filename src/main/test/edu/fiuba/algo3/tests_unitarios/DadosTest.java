package edu.fiuba.algo3.tests_unitarios;

import edu.fiuba.algo3.modelo.Dado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DadosTest {

    @Test
    public void test01LanzamientoGeneraNumeroEntre2y12() {
        // Arrange
        Dado dado = new Dado();

        // Act & Assert
        for (int i = 0; i < 100; i++) {
            int resultado = dado.lanzar();
            assertTrue(resultado >= 2 && resultado <= 12,
                    "El lanzamiento debe generar un número entre 2 y 12, pero salió " + resultado);
        }
    }
}