package edu.fiuba.algo3;
import edu.fiuba.algo3.modelo.tablero.Arista;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tableroFactory.TableroCatanFactory;
import org.junit.jupiter.api.Test;

public class testDebug {
    @Test
    public void imprimirAristasYVertices() {
        TableroCatanFactory factory = new TableroCatanFactory();
        Tablero tablero = factory.crearTablero();

        System.out.println("ARISTAS");

        for (Arista arista : tablero.getAristas().values()) {
            System.out.println(
                    "Arista " + arista.getId() +
                            " conecta Vertice " + arista.getVertice1().getId() +
                            " con Vertice " + arista.getVertice2().getId()
            );
        }
    }
}
