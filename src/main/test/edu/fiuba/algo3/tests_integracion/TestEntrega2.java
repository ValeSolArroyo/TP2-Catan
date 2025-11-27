package edu.fiuba.algo3.tests_integracion;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import edu.fiuba.algo3.modelo.excepciones.*;
import org.junit.jupiter.api.BeforeEach;

import edu.fiuba.algo3.modelo.construcciones.Carretera;
import edu.fiuba.algo3.modelo.construcciones.Ciudad;
import edu.fiuba.algo3.modelo.construcciones.Poblado;
import edu.fiuba.algo3.modelo.jugador.*;
import edu.fiuba.algo3.modelo.recursos.*;
import edu.fiuba.algo3.modelo.tablero.*;

public class TestEntrega2 {
    private Jugador jugador1;
    private Vertice v1, v2, vLejano;
    private Poblado poblado1;
    @BeforeEach
    public void setUp() {
        jugador1 = new Jugador(1, "Pepe");
        v1 = new Vertice(); 
        v2 = new Vertice(); 
        vLejano = new Vertice();
        
        v1.agregarVecino(v2);
        v2.agregarVecino(v1);

        poblado1 = new Poblado(jugador1);
    }
    

    @Test
    public void test01VerificarConsumoRecursosYCorrectaColocacionCarreteraAdyacente() {
    }

    @Test
    public void test02VerificarConsumoRecursosYReglaDistanciaPoblado() {
        v2.asignarConstruccion(new Poblado(new Jugador(9, "Enemigo"))); 
        jugador1.recibirRecurso(new Madera());
        jugador1.recibirRecurso(new Ladrillo());
        jugador1.recibirRecurso(new Lana());
        jugador1.recibirRecurso(new Grano());

    
    
        assertThrows(ReglaDeDistanciaError.class,
            () -> jugador1.construir(poblado1, v1),
            "La construcción debe fallar porque v2 está ocupado.");

        try {
            jugador1.tieneRecursos(new Madera(), 1);
            jugador1.tieneRecursos(new Ladrillo(), 1);
            jugador1.tieneRecursos(new Lana(), 1);
            jugador1.tieneRecursos(new Grano(), 1);
        } catch (RecursosInsuficientesError e) {
            fail("Los recursos fueron gastados cuando la regla de distancia falló.");
        }
    
        jugador1.agregarConstruccion(new Carretera(jugador1)); 
        jugador1.agregarConstruccion(new Carretera(jugador1)); 
        jugador1.agregarConstruccion(new Carretera(jugador1));
        jugador1.agregarConstruccion(new Carretera(jugador1));

        jugador1.entregarRecursos(List.of(new Madera(), new Ladrillo(), new Lana()));

        assertThrows(RecursosInsuficientesError.class,
            () -> jugador1.construir(poblado1, vLejano),
            "Debe fallar porque faltan 3 recursos (M, L, La) y la validación individual falla.");
    
        try {
            jugador1.tieneRecursos(new Grano(), 1); 
            assertThrows(RecursosInsuficientesError.class,
                () -> jugador1.tieneRecursos(new Madera(), 1),
                "Madera no debería existir después del primer gasto.");
            
        } catch (RecursosInsuficientesError e) {
            fail("El recurso restante fue gastado cuando la validación de costo falló.");
        }
    }

    @Test
    public void test03VerificarConsumoRecursosAlMejorarACiudadYCambioPV() {
    }
    

    @Test
    public void test04ComercioMaritimoAplicaBienTasas() {
    }

    @Test
    public void test05JugadorIntercambiaConJugador() {
    }

    @Test
    public void test06ConsumoRecursosComprarCartaDesrrolloYMazoOculto() {
    }

    @Test
    public void test07CartaDesarrolloCompradaEnTurnoNoSePuedeJugar() {
    }
}
