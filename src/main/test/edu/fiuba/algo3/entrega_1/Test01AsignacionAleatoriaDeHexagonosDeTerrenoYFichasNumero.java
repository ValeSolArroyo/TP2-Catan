package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Hexagono;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

public class Test01AsignacionAleatoriaDeHexagonosDeTerrenoYFichasNumero {

    @Test
    public void TableroTest() {
        // Act
        Tablero tablero1 = new Tablero();
        Tablero tablero2 = new Tablero();

        List<String> combinacion1 = combinacionDe(tablero1);
        List<String> combinacion2 = combinacionDe(tablero2);

        // Assert: cantidad correcta de hexágonos
        assertEquals(19, tablero1.obtenerHexagonos().size(),
                "Debe haber 19 hexágonos en el tablero");

        // Contar fichas no nulas (debería haber 18)
        long fichasConNumero = tablero1.obtenerHexagonos().stream()
                .filter(h -> h.obtenerNumeroFicha() != null)
                .count();

        assertEquals(18, fichasConNumero,
                "Debe haber 18 fichas con número (1 desierto sin número)");

        // Verificamos que no sean exactamente iguales
        assertFalse(combinacion1.equals(combinacion2),
                "Las combinaciones no deberían ser iguales (debe ser aleatorio)");
    }
    private List<String> combinacionDe(Tablero tablero) {
        List<String> combinacion = new ArrayList<>();
        for (Hexagono h : tablero.obtenerHexagonos()) {
            combinacion.add(h.obtenerTipoTerreno() + "-" + h.obtenerNumeroFicha());
        }
        return combinacion;
    }
}
