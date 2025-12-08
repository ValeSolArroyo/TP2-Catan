package edu.fiuba.algo3.tests_unitarios.tests_juego;

import edu.fiuba.algo3.modelo.juego.Dado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DadosTest {
    @Test
    public void test01LanzamientoGeneraNumeroEntre2y12() {
        // Arrange
        Dado dado = new Dado();

        // Act & Assert
        int resultado = dado.lanzarDados();
        assertTrue(resultado >= 2 && resultado <= 12,
                "El lanzamiento debe generar un número entre 2 y 12, pero salió " + resultado);
    }
}