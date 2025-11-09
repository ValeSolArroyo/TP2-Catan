package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Vertice;
import edu.fiuba.algo3.excepciones.ReglaDeDistanciaError;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test02VerticeTest {
    @Test
    public void testReglaDeDistanciaYAsentamientoExistente() {
        Jugador jugador = new Jugador(1, "Jugador1");

        Vertice vertice1 = new Vertice(1);
        Vertice vertice2 = new Vertice(2);
        Vertice vertice3 = new Vertice(3);

        // Relaciones de vecindad: v1 <--> v2 <--> v3
        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);
        vertice2.agregarVerticeAdyacente(vertice3);
        vertice3.agregarVerticeAdyacente(vertice2);

        // Colocamos primer poblado
        vertice1.construirPoblado(jugador);
        assertTrue(vertice1.yaTieneConstruccion());

        // Intentamos construir en vértice adyacente --> debe lanzar ReglaDeDistanciaError
        assertThrows(ReglaDeDistanciaError.class, () -> vertice2.construirPoblado(jugador));

        // Ponemos una construcción válida a dos vértices de distancia
        vertice3.construirPoblado(jugador);
        assertTrue(vertice3.yaTieneConstruccion());
    }
}
