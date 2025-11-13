package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LadronTest {
    @Test
    public void test01hexagonoNoProduceSiEstaElLadron() {
        Hexagono h = new Hexagono(1, "Bosque", 8);
        Ladron ladron = new Ladron();
        ladron.moverA(h);
        String recurso = h.producirRecurso(ladron);

        assertNull(recurso);
    }

    @Test
    public void test02hexagonoProduceRecursoCorrectoSiElLadronNoEsta() {
        Hexagono h = new Hexagono(2, "Bosque", 8);
        Hexagono otro = new Hexagono(3, "Campo", 4);
        Ladron ladron = new Ladron(); // Está en otro hexágono
        ladron.moverA(otro);
        String recurso = h.producirRecurso(ladron);

        assertEquals("Madera", recurso);
    }
}
